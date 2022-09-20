package se.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LoginTest {
    
    Login login;
    
    @BeforeEach
    void setUp (){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"anna, losen, true", "berit, 123456, true", "kalle, password, true"})
    public void usernameTest(String username, String password, boolean expected) {

        //When
        boolean result = login.validate(username, password);

        //Then
        Assertions.assertEquals(expected, result);

    }

    

}

