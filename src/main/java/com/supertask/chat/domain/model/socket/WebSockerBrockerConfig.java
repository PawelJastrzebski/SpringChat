//package com.supertask.chat.domain.model.socket;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSockerBrockerConfig extends AbstractWebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//
//        stompEndpointRegistry.addEndpoint("/talk").withSockJS();
//    }
//
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        super.configureMessageBroker(registry);
//    }
//}