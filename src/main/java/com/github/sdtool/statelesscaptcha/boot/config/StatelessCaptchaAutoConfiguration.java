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

    @Autowired
    private CaptchaProperties props;

    @ConditionalOnMissingBean
    @Bean
    public Creator creator() {
        return new Creator(props.getToken());
    }

    @ConditionalOnMissingBean
    @Bean
    public Verifier verifier() {
        return new Verifier(props.getToken());
    }

    @ConditionalOnMissingBean
    @Bean
    public Captcha.Builder captchaBuilder() {
        return new Captcha.Builder(props.getWidth(), props.getHeight())
                .addBackground()
                .addNoise()
                .addBorder();
    }

    @ConditionalOnMissingBean
    @Bean
    public AudioCaptcha.Builder audioCaptchaBuilder() {
        return new AudioCaptcha.Builder()
                .addVoice()
                .addNoise();
    }

    @ConditionalOnMissingBean
    @Bean
    @Autowired
    public CaptchaTokenManager captchaCreatorVerifier(
            Creator creator,
            Verifier verifier,
            Captcha.Builder captchaBuilder,
            AudioCaptcha.Builder audioCaptchaBuilder) {
        return new CaptchaTokenManager(creator, verifier, captchaBuilder, audioCaptchaBuilder);
    }

}
