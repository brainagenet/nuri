/*
 * (#) net.brainage.nuri.security.crypto.SecureRandomNumberGenerator.java
 * Created on 2016. 2. 3.
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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
public class SecureRandomNumberGenerator implements RandomNumberGenerator {

    /**
     * The default algorithm to be used for secure random number
     * generation: set to SHA1PRNG.
     */
    public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    private static final int DEFAULT_KEY_LENGTH = 32;

    private final SecureRandom secureRandom;

    private final int keyLength;

    public SecureRandomNumberGenerator() {
        this(DEFAULT_KEY_LENGTH);
    }

    public SecureRandomNumberGenerator(int keyLength) {
        try {
            this.secureRandom = SecureRandom.getInstance(DEFAULT_SECURE_RANDOM_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownAlgorithmException("Not support SRNG Algorithms.", e);
        }
        this.keyLength = keyLength;
    }

    @Override
    public int getKeyLength() {
        return this.keyLength;
    }

    @Override
    public byte[] generate() {
        byte[] bytes = new byte[this.keyLength];
        this.secureRandom.nextBytes(bytes);
        return bytes;
    }

}
