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
        log("request: " + req.getMethod() + " " + resourceRequest);

        Boolean ignoreFilter = NO_FILTERED_RESOURCES.get(resourceRequest);

        boolean isOptionsRequest = req.getMethod().equals("OPTIONS");

        if (!isOptionsRequest && (ignoreFilter == null || !ignoreFilter)) {
            try {

                if (unauthorize(req.getHeader("authorization"))) {
                    if (unauthorize(req.getHeader("Authorization"))) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                }

            } catch (SignatureException e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean unauthorize(String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return true;
        }

        String token = authorization.substring(7).trim();

        if (token.isEmpty()) {
            return true;
        }

        log("credentials: " + token);

        Jwts.parser().setSigningKey(AUTHORIZATION_KEY).parseClaimsJws(token).getBody();

        return false;
    }

    @Override
    public void init(FilterConfig config) {
    }

    private void log(String msg) {
        Logger.getLogger(AuthFilter.class).info(msg);
    }

}
