package com.twentyfive.authorizationflow.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
public class JwtUtils {
    public static String getNameFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            System.out.println("SONO NEL JWT "+ decoded);

            String username = decoded.getClaim("preferred_username").asString();
            System.out.println("SONO NEL JWTNAME "+ username);

            return username;

        }catch(Exception e){
            return "";

        }
    }
}
