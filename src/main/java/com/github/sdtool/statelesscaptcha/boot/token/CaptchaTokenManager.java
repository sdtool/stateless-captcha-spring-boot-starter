/*
 *    Copyright 2021 Subhajit Das
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.sdtool.statelesscaptcha.boot.token;

import com.github.sdtool.statelesscaptcha.core.audio.AudioCaptcha;
import com.github.sdtool.statelesscaptcha.core.text.Captcha;
import com.github.sdtool.statelesscaptcha.exception.VerificationException;
import com.github.sdtool.statelesscaptcha.token.CaptchaToken;
import com.github.sdtool.statelesscaptcha.token.CaptchaVerificationToken;
import com.github.sdtool.statelesscaptcha.token.Creator;
import com.github.sdtool.statelesscaptcha.token.Verifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Captcha creator and verifier abstraction
 *
 * @author <a href="mailto:subhajitdas298@gmail.com">Subhajit Das</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaTokenManager {

    /**
     * The captcha creator
     */
    private Creator creator;

    /**
     * The captcha verifier
     */
    private Verifier verifier;

    /**
     * The captcha builder
     */
    private Captcha.Builder captchaBuilder;

    /**
     * The audio captcha builder
     */
    private AudioCaptcha.Builder audioCaptchaBuilder;

    /**
     * Creates textual captcha
     *
     * @return the CaptchaToken
     */
    public CaptchaToken createText() {
        return creator.create(captchaBuilder.addText().build());
    }

    /**
     * Creates audio captcha
     *
     * @return the CaptchaToken
     */
    public CaptchaToken createAudio() {
        return creator.create(audioCaptchaBuilder.addAnswer().build());
    }

    /**
     * Verifies the captcha
     *
     * @param verificationToken the verification token
     * @return if the captcha is correct
     */
    public boolean verify(CaptchaVerificationToken verificationToken) {
        try {
            verifier.verify(verificationToken);
            return true;
        } catch (VerificationException ve) {
            return false;
        }
    }

}
