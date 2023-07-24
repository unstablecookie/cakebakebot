package top.kek.cakebake;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.lang.ClassNotFoundException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class CakebakeBot extends TelegramLongPollingBot {
	
public Map<String,LocalDate> map = new HashMap<>();
private File file = new File("birthdays");
private File fileWish = new File("wishes");
private String chatId = new String("00000000");// your group id. it will get updated  with the first message
private int launguageId = 0;// use it as an array pointer with your language . 0 for Russian , 1 for English etc.
private String botToken = "";
public List<String> wishes = new ArrayList<>();
	public CakebakeBot() {
		if(file.isFile()) {
			readBirthdays();
		}else {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(fileWish.isFile()) {
			readWishes();
		}else {
			try{
				fileWish.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public CakebakeBot(String chatid,String token) {
		this.chatId = chatid;
		this.botToken = token;
		if(file.isFile()) {
			readBirthdays();
		}else {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(fileWish.isFile()) {
			readWishes();
		}else {
			try{
				fileWish.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void onUpdateReceived(Update update) {
		chatId = update.getMessage().getChatId().toString();
		if(update.hasMessage()&&update.getMessage().isCommand()) {
			String command = update.getMessage().getText();
			String[] arr = command.split(" ");
			
			if(arr[0].equals("/birthdays")) {
				readBirthdays();
				if(map.size()==0) return;
				StringBuilder strbld = new StringBuilder();
				synchronized(map) {
				for(Map.Entry<String,LocalDate> entry:map.entrySet()) {
					strbld.append(entry.getKey()).append("-").append(entry.getValue().getMonth().toString()).append(" ").append(entry.getValue().getDayOfMonth()).append("\n");
				}
				}
				sendIt(update,strbld.toString());
				return;
			}else if(arr[0].equals("/addbirthday")){
				if(arr.length<3) {
					sendIt(update,LanguageHelper.updateErrorBday[launguageId]);
				}else {
					String name = arr[1];
					String[] strDate = arr[2].split("-");
					LocalDate date = LocalDate.of(2023, Integer.valueOf(strDate[0]), Integer.valueOf(strDate[1]));
					synchronized(map) {
					map.putIfAbsent(name, date);
					updateBirthdays();}
					sendIt(update,LanguageHelper.updated[launguageId]);
				}
				return;
			}else if(arr[0].equals("/wishes")){
				readWishes();
				StringBuilder strbld = new StringBuilder();
				if(wishes.size()==0) return;
				strbld.append(LanguageHelper.wishes[launguageId]);
				synchronized(wishes) {
				for(String s: wishes) {
					strbld.append(s).append(", ");
				}
				}
				sendIt(update,strbld.toString());
				return;
			}else if(arr[0].equals("/addwish")){
				if(arr.length<2) {
					sendIt(update,LanguageHelper.updateErrorWish[launguageId]);
				}else {
					StringBuilder strbld = new StringBuilder();
					for(int i=1;i<arr.length;i++) {
						strbld.append(arr[i]).append(" ");
					}
					synchronized(wishes) {
					wishes.add(strbld.toString().trim());
					updateWishes();
					}
					sendIt(update,LanguageHelper.updated[launguageId]);
				}
				return;
			}else if(arr[0].equals("/start")) {
				sendIt(update,LanguageHelper.startInfo[launguageId]);
			}else if(arr[0].equals("/deletebday")) {
				if(arr.length<2) sendIt(update,LanguageHelper.deleteBday[launguageId]);
				synchronized(map) {
				map.remove(arr[1]);
				updateBirthdays();}
				sendIt(update,LanguageHelper.updated[launguageId]);
			}else if(arr[0].equals("/deletewish")) {
				if(arr.length<2) sendIt(update,LanguageHelper.deleteWish[launguageId]);
				StringBuilder strbld = new StringBuilder();
				for(int i=1;i<arr.length;i++) {
					strbld.append(arr[i]).append(" ");
				}
				String delWish = strbld.toString().trim();
				synchronized(wishes) {
				for(int i=0;i<wishes.size();i++) {
					if(wishes.get(i).equals(delWish)) {
						wishes.remove(i);
						updateWishes();
						sendIt(update,LanguageHelper.updated[launguageId]);
						return;
					}
				}
				}
			}
			
		}
	}
	
	public void updateBirthdays() {
		try(FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(out)){
			oos.writeObject(map);
			oos.flush();
		}catch(FileNotFoundException e) {
			System.out.println("failed to write data : "+e.toString());
		}catch(IOException  e) {
			System.out.println("failed to write data : "+e.toString());
		}
	}
	
	public void updateWishes() {
		try(FileOutputStream out = new FileOutputStream(fileWish);
				ObjectOutputStream oos = new ObjectOutputStream(out)){
			oos.writeObject(wishes);
			oos.flush();
		}catch(FileNotFoundException e) {
			System.out.println("failed to write data : "+e.toString());
		}catch(IOException  e) {
			System.out.println("failed to write data : "+e.toString());
		}
	}
	
	public void readBirthdays() {
		try(FileInputStream in = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(in)){
			System.out.println("reading file...");
			synchronized(map) {
			map = (HashMap<String,LocalDate>)ois.readObject();
			}
		}catch(EOFException e) {
			sendIt("no birthdays!");
		}catch(FileNotFoundException e) {
			System.out.println("failed to read data : "+e.toString());
		}catch(IOException  e) {
			System.out.println("failed to read data : "+e.toString());
		}catch(ClassNotFoundException e) {
			System.out.println("failed to read data : "+e.toString());
		}
	}
	
	public void readWishes() {
		try(FileInputStream in = new FileInputStream(fileWish);
				ObjectInputStream ois = new ObjectInputStream(in)){
			synchronized(wishes) {
			wishes = (ArrayList<String>)ois.readObject();
			}
		}catch(EOFException e) {
			sendIt("no whishes!");
		}catch(FileNotFoundException e) {
			System.out.println("failed to read data : "+e.toString());
		}catch(IOException  e) {
			System.out.println("failed to read data : "+e.toString());
		}catch(ClassNotFoundException e) {
			System.out.println("failed to read data : "+e.toString());
		}
	}
	
	public void sendIt(Update update,String str) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText(str);
		try {
			 execute(message);
		}catch(TelegramApiException e) {
	            e.printStackTrace();
	    }
	}
	public void sendIt(String str){
		if(chatId.equals("0")) return;
		SendMessage message = new SendMessage();
		message.setChatId(chatId);
		message.setText(str);
		try {
			 execute(message);
		}catch(TelegramApiException e) {
	            e.printStackTrace();
	    }
	}
	
	public String getBotUsername() {
		 return "cakebake_bot";
	 }
	
	@Override
	 public String getBotToken() {
		 return this.botToken;//your bot token!
	 }
	
	public String getChatId() {
		return this.chatId;
	}

}
