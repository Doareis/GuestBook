package br.com.unit.encriptation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytationTest {

    @Test
    public void testEncryptation(){
        String password = "A";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        Assert.assertTrue(encodedPassword != null);
        Assert.assertTrue(encoder.matches(password, encodedPassword));

    }
}
