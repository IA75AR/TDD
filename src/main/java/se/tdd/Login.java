package se.tdd;

import java.util.List;

public class Login {

    User user = new User("anna", "losen");
    User user1 = new User("berit", "123456");
    User user2 = new User("kalle", "password");

    List<User> allUsers = List.of(user, user1, user2);

    public boolean validate (String username, String password){

        return allUsers
                .stream()
                .filter(us -> us.getUsername().equals(username))
                .findFirst()
                .get()
                .getPassword()
                .equals(password);
    }

}
