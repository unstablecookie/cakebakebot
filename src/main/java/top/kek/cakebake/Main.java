package top.kek.cakebake;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		CakebakeBot cakebakeBot ;
		if(args.length==2) cakebakeBot = new CakebakeBot(args[0],args[1]);
		else cakebakeBot = new CakebakeBot();
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(cakebakeBot);
			}catch(TelegramApiException e) {
	            e.printStackTrace();
	        }
		BirthdaysCheck birthdaysCheck = new BirthdaysCheck(cakebakeBot);
		birthdaysCheck.setDaemon(true);
		birthdaysCheck.start();
		
	}
}
