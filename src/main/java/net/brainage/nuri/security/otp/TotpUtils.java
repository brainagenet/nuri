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

    public static void main(String[] args) {
        String secret = TotpUtils.getSecret();
        System.out.println("TotpUtils.main : secret = " + secret);

        Totp totp = new Totp(secret);
        String currentOtp = totp.now();
        System.out.println("TotpUtils.main : current otp = " + currentOtp);

        String uri = totp.uri("brainage.net", "ms29.seo@brainage.net");
        System.out.println("TotpUtils.main : otp auth uri = " + uri);

        String qrcodeUrl = TotpUtils.qrcodeUri(uri);
        System.out.println("TotpUtils.main : qrcode uri = " + qrcodeUrl);

        qrcodeUrl = TotpUtils.qrcodeUri(300, uri);
        System.out.println("TotpUtils.main : qrcode uri = " + qrcodeUrl);
    }

}
