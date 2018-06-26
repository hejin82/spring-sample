package org.hejin.app.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.Assert;

import java.util.Locale;

public final class ResultMessageUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResultMessageUtils.class);

    private ResultMessageUtils() {

    }

    public static String resolvMessage(ResultMessage message, MessageSource messageSource, Locale locale) {
        Assert.notNull(message, "message must not be null");
        Assert.notNull(messageSource, "messageSource must not be null");
        Assert.notNull(locale, "locale must not be null");

        String msg;
        String code = message.getCode();
        if (code != null) {
            try {
                msg = messageSource.getMessage(code, message.getArgs(), locale);
            } catch (NoSuchMessageException e) {
                String text = message.getText();
                if (text != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("message is not found under code '" + code + "' for '" + locale + ". use '" + text + "' instead", e);
                    }
                    msg = text;
                } else {
                    throw e;
                }
            }
        } else {
            msg = message.getText();
        }
        return msg;
    }

    public static String resolveMessage(ResultMessage message, MessageSource messageSource) {
        return resolvMessage(message, messageSource, Locale.getDefault());
    }
}
