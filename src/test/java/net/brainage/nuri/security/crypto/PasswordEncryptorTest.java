/*
 * (#) net.brainage.nuri.security.crypto.PasswordEncryptorTest.java
 * Created on 2016-02-03
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.brainage.nuri.security.crypto;

import com.google.common.io.BaseEncoding;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PasswordEncryptorTest {

    @Configuration
    public static class AppConfig {
        @Bean
        public SaltGenerator saltGenerator() {
            return new SecureRandomSaltGenerator();
        }

        @Bean
        public PasswordEncryptor passwordEncryptor() {
            return new PBKDF2PasswordEncryptor();
        }
    }

    @Autowired
    SaltGenerator saltGenerator;

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @Test
    @Repeat(100)
    public void encryptTest() {
        byte[] salt = saltGenerator.generateSalt();
        String inputPassword = "password";
        String encryptedPassword = passwordEncryptor.encrypt(inputPassword, salt);
        byte[] hash = BaseEncoding.base64().decode(encryptedPassword);
        System.out.println("(" + hash.length + ") " + encryptedPassword);
        Assert.assertEquals(32, hash.length);
    }

    @Test
    public void matchsTest() {
        for (int i = 0, l = 100; i < l; i++) {
            byte[] salt = saltGenerator.generateSalt();
            String password = "password_" + i;
            String hash = passwordEncryptor.encrypt(password, salt);
            System.out.println("[" + i + "] " + password + " vs " + hash + " w/ " + BaseEncoding.base64().encode(salt));
            Assert.assertTrue(passwordEncryptor.matchs(password, hash, salt));
        }
    }

}
