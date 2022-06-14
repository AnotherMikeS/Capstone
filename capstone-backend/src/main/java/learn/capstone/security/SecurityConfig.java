package learn.capstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    // This method allows configuring web based security for specific http requests.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we're not using HTML forms in our app
        //so disable CSRF (Cross Site Request Forgery)
        http.csrf().disable();

        // this configures Spring Security to allow
        //CORS related requests (such as preflight checks)
        http.cors();

        // the order of the antMatchers() method calls is important
        // as they're evaluated in the order that they're added

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/audition", "/api/theater/audition/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/auditionee", "/api/theater/auditionee/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/theater/audition").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/theater/audition", "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/theater/auditionee", "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/theater/auditionee/*").hasRole("ADMIN")
                .antMatchers("/**").denyAll()
                // if we get to this point, let's deny all requests
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Autowired
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userBuilder = User.withUsername("user")
                .password("user").passwordEncoder(password -> getEncoder().encode(password))
                .roles("USER");

        var adminBuilder = User.withUsername("admin")
                .password("admin").passwordEncoder(password -> getEncoder().encode(password))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser(userBuilder)
                .withUser(adminBuilder);
    }


}
