package com.twentyfive.authorizationflow.keycloak_config;

import io.micrometer.common.lang.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakJwtTokenConverter implements  Converter<Jwt, Collection<GrantedAuthority>>{
    private static final String RESOURCE_ACCESS = "resource_access";
    private static final String REALM_ACCESS = "realm_access";
    private static final String MY_PERMISSION = "my-permission";
    private static final String ROLES = "roles";
    private static final String ROLE_PREFIX = "ROLE_";
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConverterProperties properties;

    public KeycloakJwtTokenConverter(
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
            TokenConverterProperties properties){
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = properties;

    }

    @Override
    public Collection<GrantedAuthority> convert(@NonNull Jwt jwt) {
        LinkedList result = new LinkedList<>();
        try {
            try {
                Map<String, Object> resourceAccess = jwt.getClaimAsMap(RESOURCE_ACCESS);
                Map<String, Object> clientIdMap = (Map<String, Object>) resourceAccess.get(properties.getResourceId());
                List<String> roles = (List<String>) clientIdMap.get(ROLES);
                Collection<GrantedAuthority> resourceRoles = roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)).collect(Collectors.toList());
                result.addAll(resourceRoles);
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                Map<String, Object> realmAccess = jwt.getClaimAsMap(REALM_ACCESS);
                List<String> realmRoles = (List<String>) realmAccess.get(ROLES);
                Collection<GrantedAuthority> realmAuthorities = realmRoles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)).collect(Collectors.toList());
                result.addAll(realmAuthorities);
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return result;
        }
    }
}
