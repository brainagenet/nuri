/*
 * (#) net.brainage.nuri.security.crypto.CryptoException.java
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

import net.brainage.nuri.NuriException;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
public class CryptoException extends NuriException {

    private static final long serialVersionUID = -5708580738985690505L;

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

}
