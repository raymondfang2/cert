package io.pivotal.pal.cert;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/*
   1. Get email from header
   2. check against DB to fetch the role and derive link allowed and push into session
   //3. DB access to be replaced by cache when app starts? how to refresh Cache?
   //4. SecurityConfig to be removed since we do not need this.
   5. make sure the header will always have the identity, otherwise we need to use session
 */

@Component
@Order(2)
public class SecurityFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        logger.info("===>SecurityFilter Starting...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String)session.getAttribute("PALCERT-ROLE");
        if (role==null) {

            String userIdentity = req.getHeader("User-Identity");
            /*
            if (userIdentity==null) {
                res.sendError(403);
                return;
            }
            */
            logger.info("=====>User-Identity=" + userIdentity);
            ObjectMapper objectMapper = new ObjectMapper();
            if (userIdentity != null) {
                HashMap idendityHeaderMap = objectMapper.readValue(userIdentity, HashMap.class);
                logger.info("=====>Domain " + idendityHeaderMap.get("domain"));
                logger.info("=====>Email " + idendityHeaderMap.get("email"));
                session.setAttribute("PALCERT-ROLE","role existing...");
            }
        }

        logger.info("=====>role="+role);
        //security checking should start here
        String path = req.getServletPath();
        logger.info("=====>path:"+path);

        chain.doFilter(request, response);

    }


}