package com.kanue.interviews.santander.accountrestapi;

import com.kanue.interviews.santander.accountrestapi.resource.AccountResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AccountResource.class);
    }
}
