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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we're not using HTML forms in our app
        //so disable CSRF (Cross Site Request Forgery)
        http.csrf().disable();

        // this configures Spring Security to allow
        //CORS related requests (such as preflight checks)
        http.cors();

        http.authorizeRequests()

                .antMatchers("/authenticate").permitAll()
                .antMatchers("/create_account").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/audition", "/api/theater/audition/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/person", "/api/theater/person/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/theater/auditionee", "/api/theater/auditionee/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/theater/audition").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/theater/person", "/api/theater/person/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/theater/auditionee").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/theater/audition", "/api/theater/audition/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/theater/person", "/api/theater/person/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/theater/auditionee", "/api/theater/auditionee/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/theater/audition/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/theater/person", "/api/theater/person/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/theater/auditionee/*").permitAll()
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
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Configure CORS globally versus
        // controller-by-controller.
        // Can be combined with @CrossOrigin.
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}