package com.fyle.bankservice.resource.helper;

import com.fyle.bankservice.auth.Secrets;
import com.fyle.bankservice.auth.jwt.UserRoles;
import io.dropwizard.auth.PrincipalImpl;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.keys.HmacKey;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

public class LoginHelper {

    public static JsonWebSignature buildToken(PrincipalImpl user) {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("1");
        claims.setStringClaim("roles", UserRoles.ROLE_ONE);
        claims.setStringClaim("user", user.getName());
        claims.setIssuedAtToNow();
        claims.setExpirationTime(offsetFromNow(7200));//5 days
        claims.setGeneratedJwtId();

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(Secrets.JWT_SECRET_KEY));
        return jws;
    }

    private static NumericDate offsetFromNow(int offsetMinutes) {
        NumericDate	nd = NumericDate.now();
        nd.addSeconds( 60*offsetMinutes);
        return nd;
    }
}

