package org.hejin.app.exception;

import org.hejin.app.message.ResultMessage;
import org.hejin.app.message.ResultMessages;

public abstract class ResultMessagesNotificationException extends RuntimeException{

    private final ResultMessages resultMessages;

    protected ResultMessagesNotificationException(ResultMessages messages) {
        this(messages, null);
    }

    public ResultMessagesNotificationException(ResultMessages messages, Throwable cause) {
        super(cause);
        if (messages == null) {
            throw new IllegalArgumentException("mesages must not be null");
        }
        this.resultMessages = messages;
    }

    public String getMessage() {
        return resultMessages.toString();
    }

    public ResultMessages getResultMessages() {
        return resultMessages;
    }

}
