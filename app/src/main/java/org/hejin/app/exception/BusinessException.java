package org.hejin.app.exception;

import org.hejin.app.message.ResultMessage;
import org.hejin.app.message.ResultMessages;

public class BusinessException extends ResultMessagesNotificationException {

    public BusinessException(String message) {
        super(ResultMessages.error().add(ResultMessage.fromText(message)));
    }
    protected BusinessException(ResultMessages messages) {
        super(messages);
    }

    public BusinessException(ResultMessages messages, Throwable cause) {
        super(messages, cause);
    }
}
