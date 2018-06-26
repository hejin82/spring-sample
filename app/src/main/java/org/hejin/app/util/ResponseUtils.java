package org.hejin.app.util;

import javax.servlet.http.HttpServletResponse;

public final class ResponseUtils {
    private static final String HEADER_PRAGMA = "Pragma";
    private static final String HEADER_EXPIRES = "Expires";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private ResponseUtils() {

    }
    public static void setPreventionCachingHeaders(
            HttpServletResponse response) {
        if (response != null) {
            response.setHeader(HEADER_PRAGMA, "no-cache");
            response.setDateHeader(HEADER_EXPIRES, 1L);
            response.setHeader(HEADER_CACHE_CONTROL,
                    "private,no-store,no-cache,must-revalidate");
        }
    }
}
