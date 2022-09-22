package se.tdd;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Login {

    User user = new User("anna", "losen");
    User user1 = new User("berit", "123456");
    User user2 = new User("kalle", "password");

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
}
