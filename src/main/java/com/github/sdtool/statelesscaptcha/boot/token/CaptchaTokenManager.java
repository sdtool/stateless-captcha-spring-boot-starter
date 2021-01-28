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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

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

    private Creator creator;

    private Verifier verifier;

    private Captcha.Builder captchaBuilder;

    private AudioCaptcha.Builder audioCaptchaBuilder;

    public CaptchaToken createText() {
        return creator.create(captchaBuilder.addText().build());
    }

    public CaptchaToken createAudio() {
        return creator.create(audioCaptchaBuilder.addAnswer().build());
    }

    public boolean verify(CaptchaVerificationToken verificationToken) {
        try {
            verifier.verify(verificationToken);
            return true;
        } catch (VerificationException ve) {
            return false;
        }
    }

}
