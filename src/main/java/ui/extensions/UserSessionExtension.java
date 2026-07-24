package ui.extensions;

import models.CreateUserByAdminRequest;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import requests.skelethon.requesters.steps.AdminSteps;
import ui.annotations.UserSession;
import ui.ui_pages.BasePage;
import ui.storage.SessionStorage;

import java.util.LinkedList;
import java.util.List;

public class UserSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        // шаг 1: проверить, что у теста есть аннотация UserSession
        UserSession annotation = context.getRequiredTestMethod().getAnnotation(UserSession.class);
        if (annotation != null) {
            int userCount = annotation.value();
            SessionStorage.clear();

            List<CreateUserByAdminRequest> users = new LinkedList<>();

            for (int i = 0; i < userCount; i++) {
                CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
                users.add(user);
            }

            SessionStorage.addUsers(users);

            int authAsUser = annotation.auth();

            BasePage.authAsUser(SessionStorage.getUser(authAsUser));
        }
    }
}
