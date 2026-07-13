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
import ui_pages.BankAlert;
import ui_pages.EditProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangingNameTest extends BaseUiTest {

    @Test
    @DisplayName("Happy path test")
    public void userCanChangeName() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        String randomName = RandomData.getName();
        authAsUser(user);

        new EditProfilePage().open()
                .waitLoadingEditProfilePage(user.getUsername())
                .setUsername(randomName)
                .clickOnSaveChangeButton()
                .checkAlertMessageAndAccept(BankAlert.NAME_UPDATE_SUCCESSFULY.getMessage());

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        assertEquals(randomName, userInfo.getName());
    }

    @Test
    @DisplayName("Negative test")
    public void userCanNotChangeName() {
        CreateUserByAdminRequest badUser = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(badUser);
        String randomName = RandomData.getInvalidName();
        GetUserInfoResponse userInfoBeforeChange = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();
        authAsUser(badUser);

        new EditProfilePage().open()
                .waitLoadingEditProfilePage(badUser.getUsername())
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
