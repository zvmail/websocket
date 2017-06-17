package com.test;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import javax.servlet.ServletContext;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示如何从connect中得到sessionId
 * Created by Vincent on 2017/6/16 0016.
 */
public class MySessionConnectedEvent implements ApplicationListener<SessionConnectedEvent> {

    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {

        StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage()); //获取消息头

        System.out.println(headers);

        String name = (String)headers.getHeader("simpSessionId");

//        WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();
//        ServletContext sc = wc.getServletContext(); //SpringBeanUtil的作用就是获取ServletContext
//
//        Object obj = sc.getAttribute("users");
//        Map<String, Principal> users = (Map<String, Principal>) (obj == null ? new HashMap<String,Principal>() : obj);
//        users.put(name, headers.getUser());
//
//        sc.setAttribute("users", users); //将用户信息已map格式放存放起来

    }

}
