package top.kek.cakebake;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Chat;
public class CakebakeBotTest {
	
	public static Path birthdaysPath ;
	public static Path wishesPath;
	
	
	@BeforeAll
	public static void buildTestEntities() {
		birthdaysPath = Paths.get("birthdays");
		wishesPath = Paths.get("wishes");
	}
	
	@Test
	public void cakebakeBotTestConstructor() {
		try {
			CakebakeBot cakebakeBot = new CakebakeBot();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void checkFilesExist() {
		assertTrue(Files.exists(birthdaysPath));
		assertTrue(Files.exists(wishesPath));
	}
	
	@Test
	public void setchatIdTest() {
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
	
	@Test
	public void sendMessageTest() {
		
	}

}
