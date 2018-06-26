package org.hejin.app.exception;

public interface ExceptionCodeResolver {

    public String resolveExceptionCode(Exception ex);
}
