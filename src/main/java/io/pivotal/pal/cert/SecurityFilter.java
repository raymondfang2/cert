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
@Order(1)
public class SecurityFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        logger.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        chain.doFilter(request, response);
        logger.info(
                "Logging Response :{}",
                res.getContentType());
    }


}