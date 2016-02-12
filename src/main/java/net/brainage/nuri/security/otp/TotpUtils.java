/*
 * (#) net.brainage.nuri.security.otp.TotpUtils.java
 * Created on 2016-02-12
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
package net.brainage.nuri.security.otp;

import com.google.common.io.BaseEncoding;
import net.brainage.nuri.security.crypto.SecureRandomNumberGenerator;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
public class TotpUtils {

    private static final int SECRET_KEY_LENGTH = 10;

    private static final SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator(SECRET_KEY_LENGTH);

    private TotpUtils() {
    }

    public static String qrcodeUri(String otpAuthUri) {
        return qrcodeUri(250, otpAuthUri);
    }

    public static String qrcodeUri(int size, String otpAuthUri) {
        return String.format("https://www.google.com/chart?chs=%dx%d&cht=qr&chl=%s", size, size, otpAuthUri);
    }

    public static String getSecret() {
        return BaseEncoding.base32().encode(secureRandom.generate());
    }

}
