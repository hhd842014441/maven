package com.hanhuide.core.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhuide.core.model.CustomResponseBody;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    private ObjectMapper objectMapper = new ObjectMapper();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
//        CustomResponseBody body = new CustomResponseBody();
//        body.setStatus(0000);
//        body.setMsg("您已在其他地方登录，请检查，时间为{" + event.getSessionInformation().getLastRequest() + "}");
//        event.getResponse().getWriter().write(JSON.toJSONString(body));
        redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), "/login");
    }
}
