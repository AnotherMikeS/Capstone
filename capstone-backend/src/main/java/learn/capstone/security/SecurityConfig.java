package learn.capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();

        http.authorizeRequests()

                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/audition", "/api/theater/audition/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/appUser", "/api/theater/appUser/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/auditionee", "/api/theater/auditionee/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/theater/audition").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/theater/appUser", "/api/theater/appUser/*").permitAll()
                .antMatchers(HttpMethod.POST, "api/theater/auditionee").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/theater/audition", "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/theater/appUser", "/api/theater/appUser/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/theater/auditionee", "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/theater/appUser", "/api/theater/appUser/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/theater/auditionee/*").hasRole("ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}