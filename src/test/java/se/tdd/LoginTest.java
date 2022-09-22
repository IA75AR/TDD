package se.tdd;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;


public class LoginTest {
    
    Login login;
    
    @BeforeEach
    void setUp (){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"anna, losen, YW5uYQ==", "berit, 123456, YmVyaXQ=", "kalle, pass, a2FsbGU="})
    public void usernameTest(String username, String password, String expected) {

        //When
        String result = login.validate(username, password);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @ParameterizedTest                                                                                           
    @CsvSource(value = {"YW5uYQ==, true", "YmVyaXQ=, true", "a2FsbGU=, false"})
    public void testToken (String token, boolean expected) {
                                                                                                                     
        //When
        String result = login.valToken(token);
                                                                                                                     
        //Then
        Assertions.assertEquals(expected, result);

    }


    @Test
    public void throwException(){
        //Given
        String username = "anna";
        String password = "losen";

        //When
        ArithmeticException err = Assertions.assertThrows(ArithmeticException.class, () -> login.validate(username, password));

        //Then
        Assertions.assertEquals("Wrong password, try again!", err.getMessage());
      }

    @Test
    public void jwtToken(){

        String username = "anna";
        Key key = Keys.hmacShaKeyFor("Ensaligröramedmassaolikateckenharvihär".getBytes());

        String token = Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("anna", "losen"))
                .addClaims(Map.of("anna", "admin"))
                .signWith(key)
                .compact();

        System.out.println(token);
        
    }

    @Test
    public void parseJwtToken(){
        
    }

}

