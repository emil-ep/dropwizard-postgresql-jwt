package com.fyle.bankservice.resource;


import com.fyle.bankservice.auth.jwt.User;
import com.fyle.bankservice.auth.jwt.UserRoles;
import com.fyle.bankservice.models.APIResponse;
import com.fyle.bankservice.resource.helper.BankResourceHelper;
import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("bank")
public class BankResource {


    private static final String TABLE_NAME = "bank";
    private static Logger logger = LoggerFactory.getLogger(BankResource.class);

    @GET
    @RolesAllowed({UserRoles.ROLE_ONE})
    @Path("search/{ifsc}")
    @Produces(APPLICATION_JSON)
    public APIResponse fetchBankByIfsc(@PathParam("ifsc") String ifsc, @QueryParam("limit") int limit,
                                       @QueryParam("offset") int offset, @Auth User user) {

        APIResponse response = new APIResponse();
        try {
            response.setSuccess(BankResourceHelper.fetchBankByIfsc(TABLE_NAME, ifsc, limit, offset));
        } catch (Exception ex) {
            response.setError("Unknown error ");
            logger.error("Unknown error : " + ex);
        }
        return response;
    }

    @GET
    @RolesAllowed({UserRoles.ROLE_ONE})
    @Path("search/{bank-name}/{city}")
    @Produces(APPLICATION_JSON)
    public APIResponse fetchByBankNameAndCity(@PathParam("bank-name") String bankName, @PathParam("city") String city,
                                              @QueryParam("limit") int limit, @QueryParam("offset") int offset, @Auth User user) {
        APIResponse response = new APIResponse();
        try {
            response.setSuccess(BankResourceHelper.fetchByBankNameAndCity(TABLE_NAME, bankName, city, limit, offset));
        } catch (Exception ex) {
            response.setError("Unknown error ");
            logger.error("Unknown error : " + ex);
        }
        return response;
    }
}
