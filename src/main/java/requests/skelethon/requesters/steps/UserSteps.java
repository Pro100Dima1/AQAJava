package requests.skelethon.requesters.steps;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CreateUserAccountsResponse;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;

public class UserSteps {
    private String username;
    private String password;

    public UserSteps(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<CreateUserAccountsResponse> getAllAccounts() {
        return new ValidatedCrudRequester<CreateUserAccountsResponse>(
                RequestSpecs.autharizationByUser(username, password),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.CUSTOMER_ACCOUNTS).getAll(CreateUserAccountsResponse[].class);
    }

}
