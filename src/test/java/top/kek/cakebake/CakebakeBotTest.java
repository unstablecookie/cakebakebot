package top.kek.cakebake;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Chat;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CakebakeBotTest {
	
	public static Path birthdaysPath ;
	public static Path wishesPath;
	
	
	/*@BeforeAll
	public static void buildEntitiesTest() {
		birthdaysPath = Paths.get("birthdays");
		wishesPath = Paths.get("wishes");
	}*/
	@AfterAll
	public static void deleteFiles() {
		try {
			Files.delete(Paths.get("birthdays"));
			Files.delete(Paths.get("wishes"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException  e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void cakebakeBotConstructorTest() {
		try {
			CakebakeBot cakebakeBot = new CakebakeBot();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cakebakeBotConstructorWithArgumentsTest() {
		try {
			CakebakeBot cakebakeBot = new CakebakeBot("0000","000000");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkFilesExistTest() {
		CakebakeBot cakebakeBot = new CakebakeBot();
		assertTrue(Files.exists(Paths.get("birthdays")));
		assertTrue(Files.exists(Paths.get("wishes")));
	}
	
	@Test
	public void setChatIdTest() {
		CakebakeBot cakebakeBot = new CakebakeBot();
		
		Chat chat = new Chat();
		chat.setId(11111l);
		
		Message message = new Message();
		message.setText("test message");
		message.setChat(chat);
		
		Update update = new Update();
		update.setMessage(message);
		
		cakebakeBot.onUpdateReceived(update);
		assertEquals(cakebakeBot.getChatId(),"11111");
		
		//Update update1 = mock(Update.class);
		//when(update1.getMessage()).thenReturn(message);
		//update.getMessage().getChatId().toString()
	}
	
	//test file read\write
	@Test
	public void writeToBirthdaysFileTest() {
		CakebakeBot cakebakeBot = new CakebakeBot();
		File file = new File("birthdays");
		assertTrue(file.isFile());
		Map<String,LocalDate> map = new HashMap<>();
		map.put("now", LocalDate.now());
		try (FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(out)){
				oos.writeObject(map);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException  e) {
			e.printStackTrace();
		}
	}
	@Test
	public void readFromBirthdaysFileTest() {
		File file = new File("birthdays");
		assertTrue(file.isFile());
		String key ;
		LocalDate localDate;
		Map<String,LocalDate> map;
		try(FileInputStream in = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(in)){
			map = (HashMap<String,LocalDate>)ois.readObject();
			assertTrue(map.keySet().contains("now"));
			assertTrue(map.get("now").getClass().isInstance(LocalDate.now()));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException  e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void writeToWishesFileTest() {
		CakebakeBot cakebakeBot = new CakebakeBot();
		File file = new File("wishes");
		assertTrue(file.isFile());
		List<String> wishes = new ArrayList<>(Arrays.asList("wish"));
		try(FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(out)){
			oos.writeObject(wishes);
			oos.flush();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException  e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readFromWishesFileTest() {
		File file = new File("wishes");
		assertTrue(file.isFile());
		List<String> wishes;
		try(FileInputStream in = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(in)){
			wishes = (ArrayList<String>)ois.readObject();
			assertTrue(wishes.get(0).getClass().isInstance(new String("")));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException  e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	
	@Test
	public void sendMessageTest() {
		
	}

}
