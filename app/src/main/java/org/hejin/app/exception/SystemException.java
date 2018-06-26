package org.hejin.app.exception;

public class SystemException extends RuntimeException implements ExceptionCodeProvider {

    private final String code;

    public SystemException(String code, String mesage, Throwable cause) {
        super(mesage, cause);
        this.code = code;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
