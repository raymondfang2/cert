package io.pivotal.pal.cert;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
Used in Local Testing, Since there is no SSO route service in local, this SpoofFilter will be
triggered and insert proper http header based on http parameter in the query string
?role=ADMIN
?role=USER
 */
@Component
@Order(1)
public class SpoofFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(SpoofFilter.class);

    //This is for testing whether it is running in PCF
    @Value("${CF_INSTANCE_INDEX:NOT SET}")
    String cfInstanceIndex;
    String testAdmin = "{\"domain\":\"test.test\",\"email\":\"admin@test.test\"}";
    String testUser = "{\"domain\":\"test.test\",\"email\":\"user@test.test\"}";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        logger.info("===>SpoofFilter starting...");

        if ("NOT SET".equals(cfInstanceIndex)) { //Only not in PCF --> local environment
            logger.info("===>SpoofFilter adding security info in local testing...");
            HttpServletRequest req = (HttpServletRequest) request;
            String path = req.getServletPath();
            logger.info("=====>path:"+path);
            String role = req.getParameter("role");
            MutableHttpServletRequest custReq = new MutableHttpServletRequest(req);
            if ("ADMIN".equals(role)) {
                custReq.putHeader("User-Identity",testAdmin);
            }
            else if ("USER".equals(role)) {
                custReq.putHeader("User-Identity",testUser);
            }
            chain.doFilter(custReq, response);
        }
        else {
            chain.doFilter(request, response);
        }
    }


}