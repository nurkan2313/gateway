package kg.cbk.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFluxSecurity
public class WebConfig {

    @Bean
    WebClient client() {
        return WebClient.builder()
                .build();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http.csrf()
                .disable()
                .authorizeExchange()
                .pathMatchers("/oauth/**", "/api/**")
                .permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
