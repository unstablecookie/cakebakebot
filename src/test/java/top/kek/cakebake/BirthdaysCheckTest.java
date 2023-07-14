package top.kek.cakebake;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;


public class BirthdaysCheckTest {
	
	CakebakeBot mockedBot = mock(CakebakeBot.class);
	
	@BeforeAll
	public static void BuildTestEntities() {
	}
	
	@Test
	public void BirthdaysCheckTestConstructor() {
		try {
			BirthdaysCheck birthdaysCheck = new BirthdaysCheck(mockedBot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
