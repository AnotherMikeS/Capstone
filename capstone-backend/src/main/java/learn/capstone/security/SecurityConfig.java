<<<<<<< HEAD
//package learn.capstone.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtConverter converter;
//
//    public SecurityConfig(JwtConverter converter) {
//        this.converter = converter;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors();
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/audition", "/api/audition/*").permitAll()
//                // Only Admins should be able to see a list of all Auditionees
//                .antMatchers(HttpMethod.GET, "/api/auditionee").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
//                .antMatchers(HttpMethod.POST, "/refresh").authenticated()
//                // Admins and Auditionees can create an audition
//                .antMatchers(HttpMethod.POST, "/api/audition", "api/audition/").hasAnyAuthority("USER", "ADMIN")
//                // Only Admins can update an existing audition
//                //      Otherwise, any auditionee could update
//                //          any other auditionee's audition...
//                .antMatchers(HttpMethod.PUT, "/api/audition/*").hasAuthority("ADMIN")
//                // Same issue for deleting an audition.
//                .antMatchers(HttpMethod.DELETE, "/api/audition/*").hasAuthority("ADMIN")
//                .antMatchers("/**").denyAll()
//                .and()
//                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    public AuthenticationManager getAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//}
=======
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
>>>>>>> 5b194b3853da02d8aa08f34fbd2f8f5cee216c67
