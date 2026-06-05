package requests.skelethon.requesters.steps;

import models.CreateUserAccountsResponse;
import models.CreateUserByAdminRequest;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class CreateAccountsSteps {

    public static CreateUserAccountsResponse createAccounts(CreateUserByAdminRequest createUserByAdminRequest) {
        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse = new ValidatedCrudRequester<CreateUserAccountsResponse>(RequestSpecs.autharizationByUser(createUserByAdminRequest.getUsername(), createUserByAdminRequest.getPassword()),
                ResponseSpecs.requestReturnStatusCreated(), Endpoint.ACCOUNTS)
                .post();
        return createUserAccountsResponse;
    }
}
