package requests.skelethon.requesters.steps;

import models.AuthorizationRequest;
import models.DepositeRequest;
import models.DepositeResponse;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class DepositeSteps {

    public static DepositeResponse makeDeposite(float balance, AuthorizationRequest authorizationRequestUser, int idAccount) {
        //Депозит денег на аккаунт с конкретным id
        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        DepositeResponse depositeResponse = new ValidatedCrudRequester<DepositeResponse>(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.balanceMatches(), Endpoint.ACCOUNTS_DEPOSITE)
                .post(depositeRequest);
        return depositeResponse;
    }

    public static void failDeposite(float balance, String errorValue, AuthorizationRequest authorizationRequestUser, int idAccount) {
        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.userCanNotChangeNameBadRequest(errorValue), Endpoint.ACCOUNTS_DEPOSITE)
                .post(depositeRequest);
    }
}
