package org.hejin.app.message;

public enum DefaultResultMessageType implements ResultMessageType {
    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    ERROR("error"),
    DANGER("danger");

    private final String type;

    private DefaultResultMessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}
