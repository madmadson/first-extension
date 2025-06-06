package de.madson.keycloak.spi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.http.HttpRequest;
import org.keycloak.models.UserModel;

import jakarta.ws.rs.core.MultivaluedMap;

import static org.mockito.Mockito.*;

class SimpleConfirmRequiredActionTest {

    private SimpleConfirmRequiredAction action;
    private RequiredActionContext context;
    private HttpRequest httpRequest;
    @SuppressWarnings("rawtypes") // Mockito handles raw types for MultivaluedMap well
    private MultivaluedMap decodedFormParameters;

    @BeforeEach
    void setup() {
        action = new SimpleConfirmRequiredAction();
        context = mock(RequiredActionContext.class);
        httpRequest = mock(HttpRequest.class);
        // Use raw type for mock if specific generic types aren't strictly needed for stubbing
        // or use mock(MultivaluedMap.class) and cast if preferred.
        decodedFormParameters = mock(MultivaluedMap.class);

        // Default Mocks
        when(context.getUser()).thenReturn(mock(UserModel.class));
        when(context.getHttpRequest()).thenReturn(httpRequest);
        when(httpRequest.getDecodedFormParameters()).thenReturn(decodedFormParameters);
    }

    @Test
    @SuppressWarnings("unchecked") // For decodedFormParameters.getFirst()
    void testProcessAction_successfulConfirm() {
        // given
        when(decodedFormParameters.getFirst("confirm")).thenReturn("on");

        // when
        action.processAction(context);

        // then
        verify(context).success();
    }
}
