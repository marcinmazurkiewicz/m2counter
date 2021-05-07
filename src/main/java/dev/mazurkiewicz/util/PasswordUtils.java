package dev.mazurkiewicz.util;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtils {
    
    public static String hashPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }

    public static boolean verifyPassword(String passToCheck, String encryptedPass) {
        boolean result = false;
        try {
            Password rawPassword = ModularCrypt.decode(encryptedPass);
            PasswordFactory factory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT);
            BCryptPassword restored = (BCryptPassword) factory.translate(rawPassword);
            result = factory.verify(restored, passToCheck.toCharArray());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }
}
