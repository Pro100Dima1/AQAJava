package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import models.AuthorizationRequest;
import models.CheckUserAccountsResponse;
import models.GetUserInfoResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import requests.GetInfoAccountsUserRequester;
import requests.GetInfoUserRequester;

import java.util.List;

public class ResponseSpecs {
    private ResponseSpecs() {}

    private static ResponseSpecBuilder defaultResponseSpec() {
        return new ResponseSpecBuilder();
    }

    public static ResponseSpecification requestReturnStatusOK(){
        return defaultResponseSpec()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification requestReturnStatusCreated(){
        return defaultResponseSpec()
                .expectStatusCode(HttpStatus.SC_CREATED)
                .build();
    }

    public static ResponseSpecification nameMathesOk(String name){
        return defaultResponseSpec()
                .expectBody("name",Matchers.equalTo(name))
                .build();
    }

    public static ResponseSpecification userCanNotChangeNameBadRequest(String errorValue){
        return defaultResponseSpec()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody(Matchers.equalTo(errorValue))
                .build();
    }

    public static ResponseSpecification nameNotMatches(String nameUser){
        return defaultResponseSpec()
                .expectBody("name", Matchers.not(Matchers.equalTo(nameUser)))
                .build();
    }

    public static ResponseSpecification balanceMatches(float balance){
        return defaultResponseSpec()
                .expectBody("balance", Matchers.equalTo(balance))
                .build();
    }

    public static ResponseSpecification balanceMatches(float balance, AuthorizationRequest req){
        List<CheckUserAccountsResponse> checkUserAccountsResponse = new GetInfoAccountsUserRequester(RequestSpecs.autharizationByUser(req.getUsername(), req.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .jsonPath().getList("", CheckUserAccountsResponse.class);
        return defaultResponseSpec()
                .expectBody("[0].balance", Matchers.equalTo(balance))
                .build();
    }
}
