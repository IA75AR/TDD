package se.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTest {
    
    Login login;
    
    @BeforeEach
    void setUp (){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"anna, losen, YW5uYQ==", "berit, 123456, YmVyaXQ=", "kalle, password, a2FsbGU="})
    public void usernameTest(String username, String password, String expected) {

        //When
        String result = login.validate(username, password);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @ParameterizedTest                                                                                           
    @CsvSource(value = {"YW5uYQ==, true", "YmVyaXQ=, true", "a2FsbGf=, false"})
    public void testToken (String token, boolean expected) {
                                                                                                                     
        //When
        boolean result = login.valToken(token);
                                                                                                                     
        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void throwException() {
        //Given
        String username = "anna";
        String password = "lose";

        //When
        InvalidException err = Assertions.assertThrows(InvalidException.class, () -> login.validate(username, password));

        //Then
        Assertions.assertEquals("Wrong password, try again!", err.getMessage());

    }

}

