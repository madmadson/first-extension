package de.madson.keycloak.spi;

import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.services.validation.Validation;

import jakarta.ws.rs.core.Response;

public class SimpleConfirmRequiredAction implements RequiredActionProvider, RequiredActionFactory {
    
    public static final String PROVIDER_ID = "SimpleConfirmRequiredAction";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
        // Optional → manuell triggern (User → RequiredAction setzen)
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        Response challenge = context.form()
                .setAttribute("user", context.getUser())
                .createForm("simple-confirm.ftl");
        context.challenge(challenge);
    }

    @Override
    public void processAction(RequiredActionContext context) {
        String confirm = context.getHttpRequest().getDecodedFormParameters().getFirst("confirm");

        // Check if the confirmation checkbox is blank or not "on"
        if (Validation.isBlank(confirm) || !"on".equals(confirm)) {
            context.challenge(context.form()
                    .setError("confirmMissing", "Bitte bestätigen Sie die Checkbox.")
                    .createForm("simple-confirm.ftl"));
            return;
        }
        context.success();
    }

    @Override
    public String getDisplayText() {
        return "Confirm Something Action";
    }

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return this;
    }

    @Override
    public void init(Config.Scope config) {
        // Initialization logic, if any, based on provider configuration
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Logic to be executed after all provider factories have been initialized
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
    
    @Override
    public void close() {
        // Cleanup resources, if any
    }
}
