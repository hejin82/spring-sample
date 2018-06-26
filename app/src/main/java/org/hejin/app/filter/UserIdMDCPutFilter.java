package org.hejin.app.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <init-param>
 *     <param-name>attributeName</param-name>
 *     <param-value>XXXX</param-value>
 * </init-param>
 * in web.xml
 */
public class UserIdMDCPutFilter extends AbstractMDCPutFilter {

    private String attributeName = "USER";

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    protected String getMDCKey(HttpServletRequest request, HttpServletResponse response) {
        return attributeName;
    }

    @Override
    protected String getMDCValue(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return  ((UserDetails) principal).getUsername();
            }
            return principal.toString();
        }
        return null;
    }
}
