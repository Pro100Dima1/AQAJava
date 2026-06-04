package requests.skelethon.requesters.steps;

import models.AuthorizationRequest;
import models.CheckUserAccountsResponse;
import models.CreateUserAccountsResponse;
import models.DepositeRequest;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class DepositeSteps {

    public static CheckUserAccountsResponse makeDeposite(float balance, AuthorizationRequest authorizationRequestUser) {
        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse = new ValidatedCrudRequester<CreateUserAccountsResponse>(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusCreated(), Endpoint.ACCOUNTS)
                .post();
        //Депозит денег на аккаунт юзера
        int idAccount = createUserAccountsResponse.getId();

        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.balanceMatches(depositeRequest.getBalance()), Endpoint.ACCOUNTS_DEPOSITE)
                .post(depositeRequest);

//        List<CheckUserAccountsResponse> checkUserAccountsResponse = new GetInfoAccountsUserRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
//                .get()
//                .extract()
//                .jsonPath().getList("", CheckUserAccountsResponse.class);
//ВОЗМОЖНО ТУТ НАДО БУДЕТ ВЕРНУТЬ LIST КАК БЫЛО РАНЬШЕ
        CheckUserAccountsResponse checkUserAccountsResponse2 = new ValidatedCrudRequester<CheckUserAccountsResponse>(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.CUSTOMER_PROFILE)
                .get();

        return checkUserAccountsResponse2;
    }

    public static void failDeposite(float balance, String errorValue, AuthorizationRequest authorizationRequestUser) {
        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse = new ValidatedCrudRequester<CreateUserAccountsResponse>(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusCreated(), Endpoint.ACCOUNTS)
                .post();
        //Депозит денег на аккаунт юзера
        int idAccount = createUserAccountsResponse.getId();

        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.userCanNotChangeNameBadRequest(errorValue), Endpoint.ACCOUNTS_DEPOSITE)
                .post(depositeRequest);
    }
}
