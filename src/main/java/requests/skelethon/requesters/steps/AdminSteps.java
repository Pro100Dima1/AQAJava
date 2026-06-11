package requests.skelethon.requesters.steps;

import generator.RandomData;
import generator.RandomModelGeneerator;
import models.AuthorizationRequest;
import models.CreateUserByAdminRequest;
import models.UserRole;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class AdminSteps {
       public static CreateUserByAdminRequest createUserByAdmin() {
        // Создание юзера админом
        CreateUserByAdminRequest createUserByAdminRequest = RandomModelGeneerator.generate(CreateUserByAdminRequest.class);

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
                ResponseSpecs.requestReturnStatusOK(), Endpoint.LOGIN)
                .post(authorizationRequestUser);

        return  authorizationRequestUser;
    }
}
