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

package com.github.sdtool.statelesscaptcha.boot.config;

import com.github.sdtool.statelesscaptcha.boot.properties.CaptchaProperties;
import com.github.sdtool.statelesscaptcha.boot.token.CaptchaTokenManager;
import com.github.sdtool.statelesscaptcha.core.audio.AudioCaptcha;
import com.github.sdtool.statelesscaptcha.core.text.Captcha;
import com.github.sdtool.statelesscaptcha.token.Creator;
import com.github.sdtool.statelesscaptcha.token.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configuration for stateless captcha
 *
 * @author <a href="mailto:subhajitdas298@gmail.com">Subhajit Das</a>
 */
@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
public class StatelessCaptchaAutoConfiguration {

    /**
     * The captcha properties
     */
    @Autowired
    private CaptchaProperties props;

    /**
     * The captcha creator
     *
     * @return the captcha creator
     */
    @ConditionalOnMissingBean
    @Bean
    public Creator creator() {
        return new Creator(props.getToken());
    }

    /**
     * The captcha verifier
     *
     * @return the captcha verifier
     */
    @ConditionalOnMissingBean
    @Bean
    public Verifier verifier() {
        return new Verifier(props.getToken());
    }

    /**
     * The textual Captcha.Builder
     *
     * @return the captcha builder
     */
    @ConditionalOnMissingBean
    @Bean
    public Captcha.Builder captchaBuilder() {
        return new Captcha.Builder(props.getWidth(), props.getHeight())
                .addBackground()
                .addNoise()
                .addBorder();
    }

    /**
     * The audio AudioCaptcha.Builder
     *
     * @return the audio captcha builder
     */
    @ConditionalOnMissingBean
    @Bean
    public AudioCaptcha.Builder audioCaptchaBuilder() {
        return new AudioCaptcha.Builder()
                .addVoice()
                .addNoise();
    }

    /**
     * The captcha token manager
     *
     * @param creator the captcha creator
     * @param verifier the captcha verifier
     * @param captchaBuilder the captcha builder
     * @param audioCaptchaBuilder the audio captcha builder
     * @return the captchaTokenManager
     */
    @ConditionalOnMissingBean
    @Bean
    @Autowired
    public CaptchaTokenManager captchaTokenManager(
            Creator creator,
            Verifier verifier,
            Captcha.Builder captchaBuilder,
            AudioCaptcha.Builder audioCaptchaBuilder) {
        return new CaptchaTokenManager(creator, verifier, captchaBuilder, audioCaptchaBuilder);
    }

}
