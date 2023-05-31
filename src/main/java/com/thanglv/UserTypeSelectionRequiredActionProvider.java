package com.thanglv;

import org.keycloak.Config;
import org.keycloak.authentication.*;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.UserModel;

import javax.ws.rs.core.Response;

public class UserTypeSelectionRequiredActionProvider implements RequiredActionProvider, RequiredActionFactory {
    public static final String PROVIDER_ID = "user-type-selection-action";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
        if (context.getUser().getFirstAttribute("CHOOSE_ACCOUNT_TYPE") == null) {
            context.getUser().addRequiredAction(PROVIDER_ID);
        }
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        Response challenge = context.form()
                .createForm("user-type-selection.ftl");
        context.challenge(challenge);
    }

    @Override
    public void processAction(RequiredActionContext context) {
        String userType = context.getHttpRequest().getDecodedFormParameters().getFirst("userType");

        if (userType == null || userType.isEmpty() || (!userType.equals("company") && !userType.equals("employee"))) {
            Response challenge = context.form()
                    .setError("Chưa chọn loại tài khoản, vui lòng thử lại!", "missingUserType")
                    .createForm("user-type-selection.ftl");
            context.challenge(challenge);
            return;
        }

        UserModel user = context.getUser();
        user.setSingleAttribute("CHOOSE_ACCOUNT_TYPE", "true");
        context.success();
    }

    @Override
    public String getDisplayText() {
        return "User Type Selection";
    }

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return this;
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}