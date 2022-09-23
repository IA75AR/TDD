package se.tdd;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class Login {

    User user = new User("anna", "losen", "student");
    User user1 = new User("berit", "123456", "teacher");
    User user2 = new User("kalle", "password", "admin");

    List<User> allUsers = List.of(user, user1, user2);

    public String validate (String username, String password){

        if (allUsers
                .stream()
                .filter(us -> us.getUsername().equals(username))
                .findFirst()
                .get()
                .getPassword()
                .equals(password)) {

            byte[] usernameAsBytes = username.getBytes();
            byte[] usernameAsBase64 = Base64.getEncoder().encode(usernameAsBytes);

            return new String (usernameAsBase64);
        }

        throw new InvalidException ("Wrong password, try again!");
    }

    public boolean valToken(String token) {

        byte[] backAsBytesBase64 = token.getBytes();
        byte[] backAsBytes = Base64.getDecoder().decode(backAsBytesBase64);

        String original = new String(backAsBytes);

        return allUsers
                .stream()
                .anyMatch(us -> us.getUsername().equals(original));
    }

    public String createToken(String username) {

       String role = "teacher";

       Key key = Keys.hmacShaKeyFor("Ensaligröramedmassaolikateckenharvihär".getBytes());

       return Jwts.builder()
               .setSubject(username)
               .addClaims(Map.of("Role", role))
               .signWith(key)
               .compact();

    }

    public String getRole(String username) {

        String token = createToken(username);
        Key key = Keys.hmacShaKeyFor("Ensaligröramedmassaolikateckenharvihär".getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("Role", String.class);
    }

}