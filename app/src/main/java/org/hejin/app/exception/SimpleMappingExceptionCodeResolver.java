package org.hejin.app.exception;

import javassist.bytecode.BadBytecode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleMappingExceptionCodeResolver implements ExceptionCodeResolver {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMappingExceptionCodeResolver.class);
    private LinkedHashMap<String, String> exceptionMappings;
    private String defaultExceptionCode;

    public void setExceptionMappings(LinkedHashMap<String, String> exceptionMappings) {
        this.exceptionMappings = exceptionMappings;
    }

    public void setDefaultExceptionCode(String defaultExceptionCode) {
        this.defaultExceptionCode = defaultExceptionCode;
    }

    @Override
    public String resolveExceptionCode(Exception ex) {

        if (ex == null) {
            logger.warn("target exception is null.return defaultExceptionCode.");
            return defaultExceptionCode;
        }

        if (ex instanceof ExceptionCodeProvider) {
            String code = ((ExceptionCodeProvider) ex).getCode();
            if (code != null) {
                return code;
            }
        }

        if (exceptionMappings == null || exceptionMappings.isEmpty()) {
            return defaultExceptionCode;
        }

        for (Map.Entry<String, String> entry : exceptionMappings.entrySet()) {
            String targetException = entry.getKey();
            Class<?> exceptionClass = ex.getClass();
            while (exceptionClass != Object.class) {
                if (exceptionClass.getName().contains(targetException)) {
                    return entry.getKey();
                }
                exceptionClass = exceptionClass.getSuperclass();
            }
        }
        return defaultExceptionCode;
    }
}
