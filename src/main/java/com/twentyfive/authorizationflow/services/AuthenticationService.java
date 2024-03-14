package com.twentyfive.authorizationflow.services;

import com.twentyfive.authorizationflow.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    public String getUsername(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization").split(" ")[1];
            String result = JwtUtils.getNameFromJwt(token);
            return result;
        }catch(Exception e){
            return "Guest";
        }
    }
    public String getId(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization").split(" ")[1];
            String result = JwtUtils.getUserIdFromJwt(token);
            return result;
        } catch (Exception e){
            return "NO_ID";
        }
    }
    public Map getRealmRoles(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization").split(" ")[1];
            Map result = JwtUtils.getAllRealmRolesFromJwt(token);
            return result;
        } catch (Exception e){
            return new HashMap();
        }
    }
    public Map getClientRoles(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization").split(" ")[1];
            Map result = JwtUtils.getAllClientRolesFromJwt(token);
            return result;
        } catch (Exception e){
            return new HashMap();
        }
    }
}
