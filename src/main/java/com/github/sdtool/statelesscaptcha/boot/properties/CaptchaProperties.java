package com.github.sdtool.statelesscaptcha.boot.properties;

import com.github.sdtool.statelesscaptcha.token.TokenProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Captcha configutaion properties
 *
 * @author <a href="mailto:subhajitdas298@gmail.com">Subhajit Das</a>
 */
@ConfigurationProperties(prefix = "captcha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaProperties {

    /**
     * Textual captcha width
     */
    private Integer width = 200;

    /**
     * Textual captcha height
     */
    private Integer height = 100;

    /**
     * Captcha token properties
     */
    private TokenProperties token = new TokenProperties();

}
