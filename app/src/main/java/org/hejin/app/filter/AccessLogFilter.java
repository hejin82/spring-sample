package org.hejin.app.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessLogFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String logMessage = getLogMessage(request);
        LOGGER.info("ACCESS START {}", logMessage);
        filterChain.doFilter(request, response);
        LOGGER.info("ACCESS FINSH {}", logMessage);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        if (uri.startsWith(request.getContextPath() + "/resources/")) {
            return true;
        }
        return false;
    }

    private String getLogMessage(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();

        sb.append("[RequestURL:").append(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (queryString != null) {
            sb.append("?").append(queryString);
        }
        sb.append("], ");

        HttpSession session = request.getSession(false);
        if (session != null) {
            sb.append("[SessionID:").append(session.getId()).append("], ");
        }

        sb.append("[RemoteAddress:").append(request.getRemoteAddr()).append("], ");
        sb.append("[RemoteHost:").append(request.getRemoteHost()).append("] ");

        return sb.toString();
    }
}
