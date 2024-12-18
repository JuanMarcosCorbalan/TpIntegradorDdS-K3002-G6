package org.example;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class OAuthService {

    // Configuración para Google OAuth
    private static final String RESOURCE_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    // Método para obtener información del usuario
    static String getUserInfo(OAuth2AccessToken accessToken, OAuth20Service service) throws IOException, ExecutionException, InterruptedException {
        // Aquí puedes hacer una llamada a la API de Google o cualquier proveedor de OAuth 2.0
        OAuthRequest request = new OAuthRequest(Verb.GET, RESOURCE_URL);
        request.addHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        Response response = service.execute(request);

        return response.getBody(); // El cuerpo de la respuesta es la información del usuario
    }
}
