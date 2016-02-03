/*
 * (#) net.brainage.nuri.security.crypto.PBKDF2PasswordEncryptor.java
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
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
public class PBKDF2PasswordEncryptor implements PasswordEncryptor {

    private static final String ALGORITHM_NAME = "PBKDF2WithHmacSHA256";
    private static final int DEFAULT_ITERATIONS = 20000;
    private static final int DEFAULT_KEY_LENGTH = 32; // 32 byte == 256 bit

    private final int iterations;
    private final int keyLength;

    public PBKDF2PasswordEncryptor() {
        this(DEFAULT_KEY_LENGTH, DEFAULT_ITERATIONS);
    }

    public PBKDF2PasswordEncryptor(int keyLength) {
        this(keyLength, DEFAULT_ITERATIONS);
    }

    public PBKDF2PasswordEncryptor(int keyLength, int iterations) {
        this.keyLength = keyLength;
        this.iterations = iterations;
    }

    @Override
    public String encrypt(String plainTextPassword, byte[] salt) {
        return BaseEncoding.base64().encode(encrypt(plainTextPassword.toCharArray(), salt));
    }

    protected byte[] encrypt(char[] plainTextPassword, byte[] salt) {
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM_NAME);
            KeySpec keySpec = new PBEKeySpec(plainTextPassword, salt, iterations, keyLength * 8);
            SecretKey key = f.generateSecret(keySpec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            String msg = "Not support '" + ALGORITHM_NAME + "' algorithm on the current JVM.";
            throw new UnknownAlgorithmException(msg, e);
        } catch (InvalidKeySpecException e) {
            throw new CryptoException("Invalid key spec", e);
        }
    }

    @Override
    public boolean matchs(String rawPassword, String encryptedPassword, byte[] salt) {
        byte[] encryptedPasswordHash = BaseEncoding.base64().decode(encryptedPassword);
        byte[] testPasswordHash = encrypt(rawPassword.toCharArray(), salt);
        return slowEquals(encryptedPasswordHash, testPasswordHash);
    }

    private boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for ( int i = 0 ; i < a.length && i < b.length ; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

}
