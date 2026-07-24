package ui;

import generator.RandomData;
import models.AuthorizationRequest;
import models.CreateUserByAdminRequest;
import models.GetUserInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import ui.annotations.UserSession;
import ui.storage.SessionStorage;
import ui.ui_pages.BankAlert;
import ui.ui_pages.EditProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangingNameTest extends BaseUiTest {

    @Test
    @UserSession
    @DisplayName("Happy path test")
    public void userCanChangeName() {
         AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        String randomName = RandomData.getName();

        new EditProfilePage().open()
                .waitLoadingEditProfilePage(SessionStorage.getUser(1).getUsername())
                .setUsername(randomName)
                .clickOnSaveChangeButton()
                .checkAlertMessageAndAccept(BankAlert.NAME_UPDATE_SUCCESSFULY.getMessage());

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(SessionStorage.getUser(1).getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        assertEquals(randomName, userInfo.getName());
    }

    @Test
    @UserSession
    @DisplayName("Negative test")
    public void userCanNotChangeName() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        String randomName = RandomData.getInvalidName();
        GetUserInfoResponse userInfoBeforeChange = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        new EditProfilePage().open()
                .waitLoadingEditProfilePage(SessionStorage.getUser(1).getUsername())
                .setUsername(randomName)
                .clickOnSaveChangeButton()
                .checkAlertMessageAndAccept(BankAlert.NAME_UPDATE_FAILED.getMessage());

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        assertEquals(userInfoBeforeChange.getName(), userInfo.getName());
    }
}
