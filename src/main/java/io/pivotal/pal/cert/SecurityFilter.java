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
import io.pivotal.pal.cert.exam.CertExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/*
   1. Get email from header
   2. check against DB to fetch the role and derive link allowed and push into session
   3. store it to user session
 */

@Component
@Order(2)
public class SecurityFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private CertExamService examService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        logger.info("===>SecurityFilter Starting...");
        //logger.info("===>"+examService);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String)session.getAttribute("PALCERT-ROLE");
        if (role==null) { //first time accessing, fetch user from http header

            String userIdentity = req.getHeader("User-Identity");

            //This is the information from sso route service: User-Identity={"email":"rfang@pivotal.io","domain":"pivotal.io"}
            logger.info("=====>User-Identity=" + userIdentity);
            if (userIdentity != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap idendityHeaderMap = objectMapper.readValue(userIdentity, HashMap.class);
                String domain = (String)idendityHeaderMap.get("domain") ;
                String email = (String)idendityHeaderMap.get("email");
                logger.info("=====>Domain " + domain);
                logger.info("=====>Email " + email);

                if ("test.test".equals(domain)) {
                    //This header is added by SpoofFilter local testing
                    role = email.split("@")[0]; //ADMIN@test.test or USER@test.test
                }
                else {
                    role = examService.getRoleByEmail(email);
                }
            }

            if ((role==null)||(role.length()==0))  {
                res.sendError(403,"Permission Denied!");
                return; //No role defined
            }
            else {
                session.setAttribute("PALCERT-ROLE", role);
            }
        }

        logger.info("=====>role="+role);
        String path = req.getServletPath();
        logger.info("=====>path:"+path);

        //check USER role is not able to accesss /admin/*
        if (("USER".equals(role))
           &&(path.startsWith("/admin"))) {
            res.sendError(403, "Permission Denied!");
            return;
        }


        chain.doFilter(request, response);


    }


}