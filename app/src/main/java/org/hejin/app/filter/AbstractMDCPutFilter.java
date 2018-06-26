package org.hejin.app.filter;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractMDCPutFilter extends OncePerRequestFilter {

    private boolean removeValue = false;
    private int maxMDCValueLength = 32;

    public void setRemoveValue(boolean removeValue) {
        this.removeValue = removeValue;
    }

    public void setMaxMDCValueLength(int maxMDCValueLength) {
        this.maxMDCValueLength = maxMDCValueLength;
    }

    protected String cutValue(String value) {
        if (value != null && maxMDCValueLength >= 0 && value.length() > maxMDCValueLength) {
            return value.substring(0, maxMDCValueLength);
        } else {
            return value;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = getMDCKey(request, response);
        String value = getMDCValue(request, response);
        MDC.put(key, value);
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (removeValue) {
                MDC.remove(key);
            }
        }
    }

    protected abstract String getMDCKey(HttpServletRequest request, HttpServletResponse response);

    protected abstract String getMDCValue(HttpServletRequest request, HttpServletResponse response);

}
