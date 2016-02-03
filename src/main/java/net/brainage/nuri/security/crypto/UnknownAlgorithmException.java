/*
 * (#) net.brainage.nuri.security.crypto.UnknownAlgorithmException.java
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

/**
 * Created by ms29.seo on 2016-02-03.
 */
public class UnknownAlgorithmException extends CryptoException {

    private static final long serialVersionUID = -3075899297427042107L;

    public UnknownAlgorithmException(String message) {
        super(message);
    }

    public UnknownAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}
