package com.example.UserService.config.interCepter;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;
import org.slf4j.Logger;


//It is an interceptor for RestTemplate,It runs before every HTTP request made using RestTemplate
public class RestTemplateIntercepter implements ClientHttpRequestInterceptor {

    //Getting access token,Managing token lifecycle
    private OAuth2AuthorizedClientManager manager;
    private Logger logger = LoggerFactory.getLogger(RestTemplateIntercepter.class);




    //Injects the manager into interceptor
    public RestTemplateIntercepter(OAuth2AuthorizedClientManager manager){
        this.manager = manager;
    }

    //This method is called every time RestTemplate makes a request
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        //✔ Calls Okta
        //✔ Gets JWT token
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();

        logger.info("RestTemplet intercepter:{}",token);

        request.getHeaders().add("Authorization","Bearer "+token);

        //Sends request to target service
        return execution.execute(request,body);
    }
}
