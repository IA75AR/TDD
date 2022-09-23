package se.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtTokenTest {

    @Mock
    Database database;

    Login login;

    @BeforeEach
    void setUp (){
        login = new Login();
    }

    @Test
    public void jwtToken(){

        //Given
        Mockito.lenient().when(database.role("anna")).thenReturn("student");

        String username = "anna";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm5hIiwiUm9sZSI6InRlYWNoZXIifQ.5MQhspQ9uJh8s_gdUvwlzOoymiNGRwzfmk8laLuFitc";

        //When
        String result = login.createToken(username);

        //Then
        Assertions.assertEquals(token, result);

    }

    @Test
    public void getRoleBerit(){

        //Given
        Mockito.lenient().when(database.role("berit")).thenReturn("teacher");

        String username = "berit";
        String expected = "teacher";

        //When
        String result = login.getRole(username);

        //Then
        Assertions.assertEquals(expected, result);
    }

}
