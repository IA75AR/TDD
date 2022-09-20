package se.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    
    Login login;
    
    @BeforeEach
    void setUp (){
        login = new Login();
    }
    
    
    @Test
    public void usernameTest() {
        //Given
        User ett = new User("anna", "losen");

        //When
        boolean result = login.validate(ett.getUsername(), ett.getPassword());

        //Then
        Assertions.assertEquals(true, result);

    }

}

