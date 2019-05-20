package org.conselhotutelar.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.jboss.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.LinkedHashMap;
import java.util.Map;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/api.conselho/*"})
public class AuthFilter implements Filter {

    public static final Key AUTHORIZATION_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final String LOGIN_PATH = "/api.conselho/logar";
    private static final Map<String, Boolean> NO_FILTERED_RESOURCES;

    static {
        NO_FILTERED_RESOURCES = new LinkedHashMap<>();
        NO_FILTERED_RESOURCES.put(LOGIN_PATH, true);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String resourceRequest = req.getRequestURI();
        log("path requested: " + resourceRequest);

        Boolean ignoreFilter = NO_FILTERED_RESOURCES.get(resourceRequest);

        if (ignoreFilter == null || !ignoreFilter) {
            try {

                if (!authorize(req.getHeader("Authorization"))) {
                    res.sendRedirect(LOGIN_PATH);
                    return;
                }

            } catch (SignatureException e) {
                res.sendRedirect(LOGIN_PATH);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean authorize(String authorization) throws ServletException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return false;
        }

        String token = authorization.substring(7).trim();

        if (token.isEmpty()) {
            return false;
        }

        Jwts.parser().setSigningKey(AUTHORIZATION_KEY).parseClaimsJws(token).getBody();

        log("Request authorization: " + token);
        return true;
    }

    @Override
    public void init(FilterConfig config) {
    }

    private void log(String msg) {
        Logger.getLogger(AuthFilter.class).info(msg);
    }

}
