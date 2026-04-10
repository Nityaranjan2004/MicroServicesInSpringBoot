package com.example.UserService.config.interCepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
//It plugs into Feign lifecycle Runs before every HTTP call
public class FeignClientInterCepter implements RequestInterceptor {



    //Brain of authentication Responsible for: Getting token Refreshing token Managing OAuth2 flow
    @Autowired
    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {

//        What happens here:
//        It asks OAuth2AuthorizedClientManager
//        Manager talks to Okta
//        Okta returns JWT access token

        //my-internal-client = Which client config to use (from application.yml),Use client_credentials flow
        //principal("internal") = Dummy identity (system-to-system call),No real user involved
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();


        //Represents outgoing HTTP request,You modify it before sending
        template.header("Authorization", "Bearer " + token);


    }
}
