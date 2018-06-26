package org.hejin.app.message;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ResultMessages implements Serializable, Iterable<ResultMessage> {

    private final ResultMessageType type;
    private final List<ResultMessage> list = new ArrayList<>();
    public static final String DEFAULT_MESSAGES_ATTRIBUTE_NAME = StringUtils.uncapitalize(ResultMessage.class.getSimpleName());

    public ResultMessages(ResultMessageType type) {
        this(type, (ResultMessage[])null);
    }

    public ResultMessages(ResultMessageType type, ResultMessage... messages) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        this.type = type;
        if (messages != null) {
            addAll(messages);
        }
    }

    public ResultMessageType getType() {
        return type;
    }

    public List<ResultMessage> getList() {
        return list;
    }

    public ResultMessages add(ResultMessage message) {

        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }

        this.list.add(message);
        return this;
    }

    public ResultMessages add(String code) {

        if (code == null) {
            throw new IllegalArgumentException("code must not be null");
        }

        this.add(ResultMessage.fromCode(code));
        return this;
    }

    public ResultMessages add(String code, Object... args) {

        if (code == null) {
            throw new IllegalArgumentException("code must not be null");
        }

        this.add(ResultMessage.fromCode(code, args));
        return this;
    }

    public ResultMessages addAll(ResultMessage... messages) {

        if (messages == null) {
            throw new IllegalArgumentException("messages must not be null");
        }

        for (ResultMessage message: messages) {
            add(message);
        }
        return this;
    }

    public ResultMessages addAll(Collection<ResultMessage> messages) {

        if (messages == null) {
            throw new IllegalArgumentException("messages must not be null");
        }

        for (ResultMessage message : messages) {
            add(message);
        }
        return this;
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    public static ResultMessages success() {
        return new ResultMessages(DefaultResultMessageType.SUCCESS);
    }

    public static ResultMessages info() {
        return new ResultMessages(DefaultResultMessageType.INFO);
    }

    public static ResultMessages warning() {
        return new ResultMessages(DefaultResultMessageType.WARNING);
    }

    public static ResultMessages error() {
        return new ResultMessages(DefaultResultMessageType.ERROR);
    }

    public static ResultMessages danger() {
        return new ResultMessages(DefaultResultMessageType.DANGER);
    }

    public Iterator<ResultMessage> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return "ResultMessages{" +
                "type=" + type +
                ", list=" + list +
                '}';
    }
}
