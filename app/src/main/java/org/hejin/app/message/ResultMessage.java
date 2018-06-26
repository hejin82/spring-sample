package org.hejin.app.message;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class ResultMessage implements Serializable {

    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final String code;
    private final Object[] args;
    private final String text;

    public ResultMessage(String code, Object[] args, String text) {
        this.code = code;
        this.args = (args == null ? EMPTY_ARRAY : args);
        this.text = text;
    }

    public static ResultMessage fromCode(String code, Object... args) {
        Assert.notNull(code, "code must not be null");
        return new ResultMessage(code, args, null);
    }

    public static ResultMessage fromText(String text) {
        Assert.notNull(text, "text must not be null");
        return new ResultMessage(null, EMPTY_ARRAY, text);
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultMessage that = (ResultMessage) o;
        return Objects.equals(code, that.code) &&
                Arrays.equals(args, that.args) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(code, text);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "code='" + code + '\'' +
                ", args=" + Arrays.toString(args) +
                ", text='" + text + '\'' +
                '}';
    }
}
