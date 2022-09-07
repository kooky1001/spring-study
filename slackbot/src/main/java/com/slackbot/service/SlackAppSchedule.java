package com.slackbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SlackAppSchedule {

	@Autowired
	public SlackApp slackApp;
	
	@Scheduled(cron = "0/1 * * * * *")
	public void send() {
		try {
			slackApp.postSlackMessage("테스트인데 잘 보내지나 보자");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
