package iteration2JunApiTests;

import generator.RandomData;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import requests.AdminCreateUserRequester;
import requests.AutharizationRequester;
import requests.ChangeNameRequester;
import requests.GetInfoUserRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class ChangingUserName {

    @CsvSource({
            "Dima Orlow",
            "DDDD ORRLOOVV"
    })
    @ParameterizedTest
    @DisplayName("Happy path test")
    public void changingNameTwoWordsWithSpaceTest(String name) {
        // Авторизация Админа
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequest);

        CreateUserByAdminRequest createUserByAdminRequest = CreateUserByAdminRequest.builder()
                .username(RandomData.getName())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();
        // Создание юзера админом
        new AdminCreateUserRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusCreated())
                .post(createUserByAdminRequest);

        //Получение токена юзера при логине :
        AuthorizationRequest authorizationRequestUser = AuthorizationRequest.builder()
                .username(createUserByAdminRequest.getUsername())
                .password(createUserByAdminRequest.getPassword())
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequestUser);

        //Изменение имени :
        ChangeNameByUserRequest changeNameByUserRequest = ChangeNameByUserRequest.builder()
                .name(name)
                .build();

        new ChangeNameRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .put(changeNameByUserRequest);

        //Получение Имени юзера из тела ответа :
        GetUserInfoResponse nameUser = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .as(GetUserInfoResponse.class);

        //Проверка, что создался юзер с этим именем
        new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.nameMathesOk(nameUser.getUsername()))
                .get();
    }

    public static Stream<Arguments> invalidNameUserTests() {
        return Stream.of(
                Arguments.of("Dima  Orlov", "Name must contain two words with letters only"),
                Arguments.of("DimaOrlov", "Name must contain two words with letters only"),
                Arguments.of("DimaOrlov ", "Name must contain two words with letters only"),
                Arguments.of(" ", "Name must contain two words with letters only"),
                Arguments.of("!@!@!@ !@!@!", "Name must contain two words with letters only"),
                Arguments.of("12345 6789", "Name must contain two words with letters only"),
                Arguments.of("123!!!45 67@@@89", "Name must contain two words with letters only"),
                Arguments.of("Dima Orlov Kerpich", "Name must contain two words with letters only"),
                Arguments.of("Dima 934435", "Name must contain two words with letters only"),
                Arguments.of("Дима Орлов", "Name must contain two words with letters only")
        );
    }

    @MethodSource("invalidNameUserTests")
    @DisplayName("Negative test")
    @ParameterizedTest
    public void negativeTestsChangingName(String name, String errorValue) {
        // Авторизация Админа
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequest);

        CreateUserByAdminRequest createUserByAdminRequest = CreateUserByAdminRequest.builder()
                .username(RandomData.getName())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();
        // Создание юзера админом
        new AdminCreateUserRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusCreated())
                .post(createUserByAdminRequest);

        //Получение токена юзера при логине :
        AuthorizationRequest authorizationRequestUser = AuthorizationRequest.builder()
                .username(createUserByAdminRequest.getUsername())
                .password(createUserByAdminRequest.getPassword())
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequestUser);
        //Изменение имени на НЕ валидное :
        ChangeNameByUserRequest changeNameByUserRequest = ChangeNameByUserRequest.builder()
                .name(name)
                .build();

        new ChangeNameRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.userCanNotChangeNameBadRequest(errorValue))
                .put(changeNameByUserRequest);

        //Получение Имени юзера из тела ответа :
        GetUserInfoResponse nameUser = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .as(GetUserInfoResponse.class);

        //Проверка, что создался юзер с этим именем
        new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.nameNotMatches(nameUser.getUsername()))
                .get();
    }
}

