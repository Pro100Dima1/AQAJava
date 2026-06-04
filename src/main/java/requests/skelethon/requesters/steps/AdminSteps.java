package requests.skelethon.requesters.steps;

import generator.RandomData;
import models.AuthorizationRequest;
import models.CreateUserByAdminRequest;
import models.UserRole;
import requests.AdminCreateUserRequester;
import requests.AutharizationRequester;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class AdminSteps {
   // private static final String ADMIN_CREDETIONALS = "admin";

//    public static AuthorizationRequest authorizationAdmin() {
//        // Авторизация Админа
//        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
//                .username(ADMIN_CREDETIONALS)
//                .password(ADMIN_CREDETIONALS)
//                .build();
//
//        new AutharizationRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK())
//                .post(authorizationRequest);
//
//        return authorizationRequest;
//    }

    public static CreateUserByAdminRequest createUserByAdmin() {
        // Создание юзера админом
        CreateUserByAdminRequest createUserByAdminRequest = CreateUserByAdminRequest.builder()
                .username(RandomData.getName())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        new CrudRequester(RequestSpecs.autharizationByAdmin(),
                ResponseSpecs.requestReturnStatusCreated(), Endpoint.ADMIN_USER)
                .post(createUserByAdminRequest);

        return createUserByAdminRequest;
    }

    public static AuthorizationRequest authorizationUser( CreateUserByAdminRequest createUserByAdminRequest) {
        //Получение токена юзера при логине :
        AuthorizationRequest authorizationRequestUser = AuthorizationRequest.builder()
                .username(createUserByAdminRequest.getUsername())
                .password(createUserByAdminRequest.getPassword())
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.ADMIN_USER)
                .post(authorizationRequestUser);

        return  authorizationRequestUser;
    }
}
