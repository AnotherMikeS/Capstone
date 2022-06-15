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

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    @Bean
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