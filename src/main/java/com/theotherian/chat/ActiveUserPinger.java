package com.theotherian.chat;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Set;

@Component
public class ActiveUserPinger {

    private SimpMessagingTemplate template;
    private ActiveUserService activeUserService;

    @Inject
    public ActiveUserPinger(SimpMessagingTemplate template, ActiveUserService activeUserService) {
        this.template = template;
        this.activeUserService = activeUserService;
    }

    @Scheduled(fixedDelay = 2000)
    public void pingUsers() {
        Set<String> activeUsers = activeUserService.getActiveUsers();
        template.convertAndSend("/topic/active", activeUsers);
    }

}
