package iteration2JunApiTests;

import models.AuthorizationRequest;
import models.ChangeNameByUserRequest;
import models.CreateUserByAdminRequest;
import models.GetUserInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.stream.Stream;

public class ChangingUserName {

    @CsvSource({
            "Dima Orloww",
            "DDDD ORRLOOVVV"
    })
    @ParameterizedTest
    @DisplayName("Happy path test")
    public void changingNameTwoWordsWithSpaceTest(String name) {
        //Создание юзера
        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);
        //Изменение имени :
        ChangeNameByUserRequest changeNameByUserRequest = ChangeNameByUserRequest.builder()
                .name(name)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.CUSTOMER_PROFILE)
                .put(changeNameByUserRequest);
        //Получение Имени юзера из тела ответа :
        GetUserInfoResponse nameUser = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        //Проверка, что создался юзер с этим именем
        new CrudRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.nameMathesOk(nameUser.getUsername()), Endpoint.CUSTOMER_PROFILE)
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
        //Создание юзера
        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);
        //Изменение имени на НЕ валидное :
        ChangeNameByUserRequest changeNameByUserRequest = ChangeNameByUserRequest.builder()
                .name(name)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.CUSTOMER_PROFILE)
                .put(changeNameByUserRequest);
        //Получение Имени юзера из тела ответа :
        GetUserInfoResponse nameUser = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.CUSTOMER_PROFILE)
                .get();
        //Проверка, что создался юзер с этим именем
        new CrudRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.nameNotMatches(nameUser.getUsername()), Endpoint.CUSTOMER_PROFILE)
                .get();
    }
}

