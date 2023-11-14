package com.twentyfive.authorizationflow.services;

import com.twentyfive.authorizationflow.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthenticationService {
    public String getUsername(){
        try {
            System.out.println("sono nel try");
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization").split(" ")[1];
            String result = JwtUtils.getNameFromJwt(token);
            return result;
        }catch(Exception e){
            System.out.println("sono nel catch");
            return "Guest";
        }
    }
}
