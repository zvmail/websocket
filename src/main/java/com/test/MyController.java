package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * Created by Vincent on 2017/5/30 0030.
 */
@Controller
public class MyController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage helloMessage) throws Exception {
        Thread.sleep(1);
        return new Greeting("Hello, " + helloMessage.getName() + "!");
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //利用接收信息中的name，定向发送消息
    @MessageMapping("/message1")
    public void handleMessage1(HelloMessage helloMessage) {
        System.out.println("this is the @SubscribeMapping('/" + helloMessage.getName() + "')");
        Greeting g = new Greeting("I am a msg from SubscribeMapping('/macro').");
        messagingTemplate.convertAndSendToUser(helloMessage.getName(), "/message1", g);
    }

    //利用sessionId，user注解
    //客户端监听在user/(sessionId)/message下
    @MessageMapping("/message")
    @SendToUser("/message")
    public Greeting handleMessage(HelloMessage helloMessage ) throws Exception {
        return new Greeting("Ahh Hello, " + helloMessage.getName() + "!");
    }
}
