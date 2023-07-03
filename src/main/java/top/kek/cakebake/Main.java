package top.kek.cakebake;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
	public static void main(String[] args) {
		CakebakeBot cakebakeBot = new CakebakeBot();;
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(cakebakeBot);
			}catch(TelegramApiException e) {
	            e.printStackTrace();
	        }
		BirthdaysCheck birthdaysCheck = new BirthdaysCheck(cakebakeBot);
		birthdaysCheck.start();
	}
}
