package io.autor.i18n;

import io.autor.util.MomentsUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Stephan Grundner
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        Date date = MomentsUtils.autoParseDateFormat(source);
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
