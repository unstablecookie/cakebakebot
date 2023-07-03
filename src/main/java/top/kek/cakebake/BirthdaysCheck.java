package top.kek.cakebake;

import java.lang.Thread;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.lang.InterruptedException;
import java.util.Random;
import java.time.LocalDateTime;

public class BirthdaysCheck extends Thread {
	
	public CakebakeBot cakebakeBot;
	
	public BirthdaysCheck(CakebakeBot cakebakeBot) {
		this.cakebakeBot = cakebakeBot;
	}
	
	public void run()  {
		while(true) {
			LocalDate currentDate = LocalDate.now();
			for(Map.Entry<String, LocalDate> entry: cakebakeBot.map.entrySet()) {
				LocalDate entryDate = entry.getValue();
				if((currentDate.getDayOfMonth()==(entryDate.getDayOfMonth()))&(currentDate.getMonth().equals(entryDate.getMonth()))&((LocalDateTime.now().getHour()>10)&(LocalDateTime.now().getHour()<13))) {
					
					Random random = new Random();
					int max = cakebakeBot.wishes.size();
					if(max<3) {
						cakebakeBot.sendIt("С днем рождения "+entry.getKey()+"! и желаю добавить еще желаний!");
					}else {
						cakebakeBot.sendIt("С днем рождения "+entry.getKey()+"! и желаю тебе :"+cakebakeBot.wishes.get(random.nextInt(max))+" ,"+cakebakeBot.wishes.get(random.nextInt(max))+" и "+cakebakeBot.wishes.get(random.nextInt(max)));
					}
				}
			}
			try{
				Thread.sleep(7200000);
				//Thread.sleep(7200);
			}catch(InterruptedException e) {
				System.out.println("interrupted : "+e.toString());
			}
			
		}
		
	}
	
}
