<<<<<<< HEAD
//package learn.capstone.security;
//
//import learn.capstone.models.AppUser;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtConverter {
//
//    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    private final String ISSUER = "capstone-backend";
//    private final int EXPIRATION_MINUTES = 15;
//    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;
//
//    public String getTokenFromUser(AppUser user) {
//        String authorities = user.getAuthorities().stream()
//                .map(i -> i.getAuthority())
//                .collect(Collectors.joining(","));
//
//        return Jwts.builder()
//                .setIssuer(ISSUER)
//                .setSubject(user.getUsername())
//                .claim("authorities", authorities)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
//                .signWith(key)
//                .compact();
//    }
//
//    public AppUser getUserFromToken(String token) {
//        if (token == null || !token.startsWith("Bearer ")) {
//            return null;
//        }
//
//        try {
//            Jws<Claims> jws = Jwts.parserBuilder()
//                    .requireIssuer(ISSUER)
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token.substring(7));
//
//            String username = jws.getBody().getSubject();
//            String authStr = (String) jws.getBody().get("authorities");
//            List<String> authorities = Arrays.stream(authStr.split(","))
//                    .collect(Collectors.toList());
//
//            AppUser user = new AppUser();
//            user.setUsername(username);
//            user.setAuthorities(authorities);
//
//            return user;
//        } catch (JwtException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//}
=======
package learn.capstone.security;

import learn.capstone.models.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    // 1. Signing key
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 2. "Configurable" constants
    private final String ISSUER = "capstone-backend";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(User user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        // 3. Use JJWT classes to build a token.
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public User getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            // 4. Use JJWT classes to read a token.
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            String authStr = (String) jws.getBody().get("authorities");
            List<GrantedAuthority> authorities = Arrays.stream(authStr.split(","))
                    .map(i -> new SimpleGrantedAuthority(i))
                    .collect(Collectors.toList());

            return new User(username, username, authorities);

        } catch (JwtException e) {
            // 5. JWT failures are modeled as exceptions.
            System.out.println(e);
        }

        return null;
    }
}
>>>>>>> 5b194b3853da02d8aa08f34fbd2f8f5cee216c67
