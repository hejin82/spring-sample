package org.hejin.app.exception;

public interface ExceptionLevelResolver {

    public ExceptionLevel resolveExceptionLevel(Exception ex);
}
