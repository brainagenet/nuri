/*
 * (#) net.brainage.nuri.security.otp.api.Digits.java
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
package net.brainage.nuri.security.otp.api;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
public enum Digits {

    SIX(1000000), SEVEN(10000000), EIGHT(100000000);

    private int digits;

    Digits(int digits) {
        this.digits = digits;
    }

    public int getValue() {
        return digits;
    }

}
