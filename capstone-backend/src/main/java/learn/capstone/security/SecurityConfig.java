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
                .antMatchers(HttpMethod.GET, "/api/audition", "/api/audition/*").permitAll()
                // Only Admins should be able to see a list of all Auditionees
                .antMatchers(HttpMethod.GET, "/api/auditionee").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh").authenticated()
                // Admins and Auditionees can create an audition
                .antMatchers(HttpMethod.POST, "/api/audition", "api/audition/").hasAnyAuthority("USER", "ADMIN")
                // Only Admins can update an existing audition
                //      Otherwise, any auditionee could update
                //          any other auditionee's audition...
                .antMatchers(HttpMethod.PUT, "/api/audition/*").hasAuthority("ADMIN")
                // Same issue for deleting an audition.
                .antMatchers(HttpMethod.DELETE, "/api/audition/*").hasAuthority("ADMIN")
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