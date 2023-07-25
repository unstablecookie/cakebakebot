package top.kek.cakebake;

import java.lang.Thread;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.lang.InterruptedException;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class BirthdaysCheck extends Thread {
	
	public CakebakeBot cakebakeBot;
	private int launguageId = 0;// use it as an array pointer with your language . 0 for Russian , 1 for English etc.
	private final AtomicBoolean running = new AtomicBoolean(true);
	private Thread worker;
	
	public BirthdaysCheck(CakebakeBot cakebakeBot) {
		this.cakebakeBot = cakebakeBot;
	}
	
	public void run()  {
		while(running.get()) {
			try{
				LocalDate currentDate = LocalDate.now();
				Map<String,LocalDate> map = Collections.synchronizedMap(cakebakeBot.map);
				
				synchronized(map) {
				for(Map.Entry<String, LocalDate> entry: cakebakeBot.map.entrySet()) {
					LocalDate entryDate = entry.getValue();
					if((currentDate.getDayOfMonth()==(entryDate.getDayOfMonth()))&(currentDate.getMonth().equals(entryDate.getMonth()))&((LocalDateTime.now().getHour()>10)&(LocalDateTime.now().getHour()<13))) {
						List<String> list = cakebakeBot.wishes;
						synchronized(list) {
						Random random = new Random();
						int max = cakebakeBot.wishes.size();
						if(max<3) {
							cakebakeBot.sendIt(LanguageHelper.happyBirthday[launguageId]+entry.getKey()+LanguageHelper.birthdayAddWish[launguageId]);
						}else {
							cakebakeBot.sendIt(LanguageHelper.happyBirthday[launguageId]+entry.getKey()+LanguageHelper.birthdayWish[launguageId]+
									cakebakeBot.wishes.get(random.nextInt(max))+
									" ,"+cakebakeBot.wishes.get(random.nextInt(max))+
									LanguageHelper.and[launguageId]+cakebakeBot.wishes.get(random.nextInt(max)));
						}
						}
					}
				}
				}
				Thread.sleep(7200000);
			}catch(InterruptedException e) {
				running.set(false);
				e.printStackTrace();
			}
			
		}
		
	}
	
}
