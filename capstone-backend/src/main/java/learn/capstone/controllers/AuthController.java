package learn.capstone.controllers;

import learn.capstone.models.AppUser;
import learn.capstone.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtConverter converter;

    public AuthController(AuthenticationManager manager, JwtConverter converter) {
        this.manager = manager;
        this.converter = converter;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody Map<String, String> values) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(values.get("username"), values.get("password"));

        try {
            Authentication result = manager.authenticate(token);
            if (result.isAuthenticated()) {
                String jwt = converter.getTokenFromUser((AppUser) result.getPrincipal());
                HashMap<String, String> payload = new HashMap<>();
                payload.put("jwt_token", jwt);
                return new ResponseEntity<>(payload, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            System.out.println("Forbidden!");
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@AuthenticationPrincipal AppUser user) {
        HashMap<String, String> payload = new HashMap<>();
        payload.put("jwt_token", converter.getTokenFromUser(user));
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
