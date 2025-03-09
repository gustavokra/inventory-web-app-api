package com.kraemer.entities;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.enums.EnumRole;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class UserBOTest {

        @Test
        void testValidate() {
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> new UserBO(0L,null, "test", "test", "test", 1l, Set.of(EnumRole.parseByKey("admin"))));

                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> new UserBO(0L, "test", null, "test", "test", 1l, Set.of(EnumRole.parseByKey("admin"))));
 
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> new UserBO(0L, "test", "test", null, "test", 1l, Set.of(EnumRole.parseByKey("admin"))));

                Assertions.assertDoesNotThrow(
                                () -> new UserBO(0L, "test", "test", "test", null, 1l, Set.of(EnumRole.parseByKey("admin"))));

                Assertions.assertDoesNotThrow(
                                () -> new UserBO(0L, "test", "test", "test", "test", 1l, Set.of(EnumRole.parseByKey("admin"))));
        }

        @Test
        void testValidValidateEmail() {
                UserBO userValidEmail =new UserBO(0L, "test", "valid@email.com", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertDoesNotThrow(
                                () -> userValidEmail.validateEmail());
        }

        @Test
        void testInvalidValidateEmail() {
                UserBO userInvalidEmail =new UserBO(0L, "test", "invalidEmail", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> userInvalidEmail.validateEmail());

                UserBO userInvalidEmail2 =new UserBO(0L, "test", "invalid@email", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> userInvalidEmail2.validateEmail());

                UserBO userInvalidEmail3 =new UserBO(0L, "test", "invalid.com", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> userInvalidEmail3.validateEmail());

                UserBO userInvalidEmail4 =new UserBO(0L, "test", "@email.com", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(
                                InventoryAppException.class,
                                () -> userInvalidEmail4.validateEmail());
        }

        @Test
        void testValidValidatePassword() {
                UserBO userValidPassword =new UserBO(0L, "test", "test", "test", "test", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertDoesNotThrow(() -> userValidPassword);
        }

        @Test
        void testInvalidValidatePassword() {
                UserBO userInvalidPassword =new UserBO(0L, "test", "test", "test", null, 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(InventoryAppException.class,
                                () -> userInvalidPassword.validatePassword());

                UserBO userInvalidPassword2 =new UserBO(0L, "test", "test", "test", "invalid", 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                Assertions.assertThrows(InventoryAppException.class,
                                () -> userInvalidPassword2.validatePassword());
        }

        @Test
        void testEncriptPassword() {
                var passwordBeforeEncript = "beforeEncript";
                UserBO user =new UserBO(0L, "test", "test", passwordBeforeEncript, passwordBeforeEncript, 1l,
                                Set.of(EnumRole.parseByKey("admin")));
                user.encriptPassword();
                Assertions.assertEquals(
                                "30ca9286a6b92fa9a7951f0e63145b4959da935803dde8503a15519ee0ea1a6e",
                                user.getPassword());
        }

}
