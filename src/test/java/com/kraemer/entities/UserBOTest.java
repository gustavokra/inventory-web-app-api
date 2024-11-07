package com.kraemer.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class UserBOTest {

    @Test
    void testValidate() {
        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> new UserBO(null, "test", "test", "test"));

        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> new UserBO("test", null, "test", "test"));

        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> new UserBO("test", "test", "null", "test"));

        Assertions.assertDoesNotThrow(
                () -> new UserBO("test", "test", "test", null));

        Assertions.assertDoesNotThrow(
                () -> new UserBO("test", "test", "test", "test"));
    }

    @Test
    void testValidValidateEmail() {
        UserBO userValidEmail = new UserBO("test", "valid@email.com", "test", "test");
        Assertions.assertDoesNotThrow(
                () -> userValidEmail.validateEmail());
    }

    @Test
    void testInvalidValidateEmail() {
        UserBO userInvalidEmail = new UserBO("test", "invalidEmail", "test", "test");
        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> userInvalidEmail.validateEmail());

        UserBO userInvalidEmail2 = new UserBO("test", "invalid@email", "test", "test");
        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> userInvalidEmail2.validateEmail());

        UserBO userInvalidEmail3 = new UserBO("test", "invalid.com", "test", "test");
        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> userInvalidEmail3.validateEmail());

        UserBO userInvalidEmail4 = new UserBO("test", "@email.com", "test", "test");
        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> userInvalidEmail4.validateEmail());
    }

    @Test
    void testValidValidatePassword() {
        UserBO userValidPassword = new UserBO("test", "test", "test", "test");
        Assertions.assertDoesNotThrow(() -> userValidPassword);
    }

    @Test
    void testInvalidValidatePassword() {
        UserBO userInvalidPassword = new UserBO("test", "test", "test", null);
        Assertions.assertThrows(InvetoryAppException.class,
                () -> userInvalidPassword.validatePassword());

        UserBO userInvalidPassword2 = new UserBO("test", "test", "test", "invalid");
        Assertions.assertThrows(InvetoryAppException.class,
                () -> userInvalidPassword2.validatePassword());
    }

    @Test
    void testEncriptPassword() {
        var passwordBeforeEncript = "beforeEncript";
        UserBO user = new UserBO("test", "test", passwordBeforeEncript, passwordBeforeEncript);
        user.encriptPassword();
        Assertions.assertEquals(
                "30ca9286a6b92fa9a7951f0e63145b4959da935803dde8503a15519ee0ea1a6e",
                user.getPassword());
    }

}
