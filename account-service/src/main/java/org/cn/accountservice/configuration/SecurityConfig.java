package org.cn.accountservice.configuration;

import lombok.AllArgsConstructor;
import org.cn.accountservice.filter.JWTAuthorizationFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.ws.rs.HttpMethod;

@Configuration
@EnableConfigurationProperties
@AllArgsConstructor
public class SecurityConfig {

    private final RSAConfig rsaConfig;


    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        return new ProviderManager(daoAuthenticationProvider);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(jwtDecoder());
        return http
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(c -> c.disable())
                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.POST, "/v1/api/accounts/**").hasAuthority("ADMIN"))
                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.PUT, "/v1/api/accounts/**").hasAuthority("ADMIN"))
                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.DELETE, "/v1/api/accounts/**").hasAuthority("ADMIN"))
                //.authorizeRequests(auth -> auth.requestMatchers( "/v1/api/accounts/**").hasAuthority("USER"))
                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.GET, "/v1/api/accounts").hasAuthority("ADMIN"))
                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.GET, "/v1/api/accounts/{id}").hasAnyAuthority("USER", "ADMIN"))
                .authorizeRequests(auth -> auth.antMatchers( "/v1/api/accounts/credit").hasAnyAuthority("USER"))
                .authorizeRequests(auth -> auth.antMatchers( "/v1/api/accounts/debit").hasAnyAuthority("USER"))
                .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaConfig.publicKey()).build();
    }



}
