package tests;

import Entities.CommonEmployee;
import Entities.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntitiesTest {

    /*Password being tested here is notValid since it is less than 5 characters and does not contain at least one upperCase letter*/
    @Test
    void passwordTestNotValid() {
        Employee user = new CommonEmployee(12,"kaur", "samreen","Toronto", "0924192", "abc");

        assertFalse(user.passwordIsValid());
    }

    /*Password being tested here is valid since it is more than 5 characters and contains at least one upperCase letter*/
    @Test
    void passwordTestValid() {
        Employee user = new CommonEmployee(12,"kaur", "samreen","Toronto", "0924192", "abcedfgH");

        assertTrue(user.passwordIsValid());
    }
}
