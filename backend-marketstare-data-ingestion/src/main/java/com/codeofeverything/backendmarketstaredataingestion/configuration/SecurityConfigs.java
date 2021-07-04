package com.codeofeverything.backendmarketstaredataingestion.configuration;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EqualsAndHashCode(callSuper = false)
@EnableWebSecurity
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    private Set<String> allowedOrigins;

    private String username;

    private String password;

    private String role;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication()
                .withUser(username)
                .password(password)
                .roles(role);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request->new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**", "/actuator/health").permitAll()
                .antMatchers("/**").hasRole(role)
                .and()
                .formLogin();
    }
}
