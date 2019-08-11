package com.fyle.bankservice.resource;

import com.fyle.bankservice.models.APIResponse;
import com.fyle.bankservice.resource.helper.LoginHelper;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/login")
public class LoginResource {


    private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

    @GET
    @Produces(APPLICATION_JSON)
    @CacheControl(noCache = true, noStore = true, mustRevalidate = true, maxAge = 0)
    public APIResponse login(@Auth PrincipalImpl user) {
        APIResponse response = new APIResponse();
        try {
            response.setSuccess(LoginHelper.buildToken(user).getCompactSerialization());
        } catch (Exception ex) {
            logger.error("Login error : " + ex);
            response.setError("Unknown error ");
        }
        return response;
    }
}
