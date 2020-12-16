package lt.sebpra.demoblog.configurations;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class WebMvcConfing implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var cookieLocalResolver = new CookieLocaleResolver();
        cookieLocalResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocalResolver;
    }


}
