package com.slackbot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;

@Service
public class SlackApp {

	@Value(value = "${slack.token}")
	String token;
	@Value(value = "${slack.channel}")
	String channel;
	
	public void postSlackMessage(String message){
        try{
            MethodsClient methods = Slack.getInstance().methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .text(message)
                    .build();

            methods.chatPostMessage(request);
           
            //log.info("보냄");
        } catch (SlackApiException | IOException e) {
        	e.printStackTrace();
            //log.error(e.getMessage());
        }
    }
}
