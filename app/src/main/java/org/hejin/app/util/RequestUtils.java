package org.hejin.app.util;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {

    private RequestUtils() {

    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }
}
