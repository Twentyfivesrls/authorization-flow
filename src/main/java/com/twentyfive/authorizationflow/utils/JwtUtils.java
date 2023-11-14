package com.twentyfive.authorizationflow.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
public class JwtUtils {
    public static String getNameFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            String username = decoded.getClaim("preferred_username").asString();
            return username;

        }catch(Exception e){
            return "";

        }
    }
}
