package com.twentyfive.authorizationflow.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String getNameFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            String username = decoded.getClaim("preferred_username").asString();
            return username;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static String getUserIdFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            String userId = decoded.getClaim("sub").asString();
            return userId;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static Map getAllRealmRolesFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            Map realm_access = decoded.getClaim("realm_access").asMap();
            return realm_access;
        }catch(Exception e){
            e.printStackTrace();
            return new HashMap();
        }
    }

    public static Map getAllClientRolesFromJwt(String token){
        try{
            DecodedJWT decoded = JWT.decode(token);
            Map resource_access = decoded.getClaim("resource_access").asMap();
            return resource_access;
        }catch(Exception e){
            e.printStackTrace();
            return new HashMap();
        }
    }

}
