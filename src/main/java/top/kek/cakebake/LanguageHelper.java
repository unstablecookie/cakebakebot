package top.kek.cakebake;

public class LanguageHelper {
	public static String[] updated = new String[] {"обновлено!","updated!"};
	
	public static String[] wishes = new String[] {"текущие пожелания : ","wishes:"};
	
	public static String[] updateErrorBday = new String[] {
			"неверно указан формат команды. Пожалуйста укажите  '/addbirthday ИМЯ М-Д'",
			"wrong attributes , please use '/addbirthday NAME M-D'"};
	
	public static String[] updateErrorWish = new String[] {
			"неверно указан формат команды. Пожалуйста укажите  '/addwish ЖЕЛАНИЕ'",
			"wrong attributes , please use '/addwish WISH'"};
	
	public static String[] deleteBday = new String[] {
			"неверно указан параметр, укажите /deletebday ИМЯ",
			"wrong attributes , please use '/deletebday NAME'"};
	
	public static String[] deleteWish = new String[] {
			"неверно указан параметр, укажите /deletewish ПОЖЕЛАНИЕ",
			"wrong attributes , please use '/deletewish WISH'"};
	
	public static String[] startInfo = new String[] {
			"я умею поздравлять с днем рождения \n"+
			"и поддерживаю следующие команды: \n"+
			"/birthdays - показать текущий список дней рождений\n"+
			"/addbirthday - добавить именинника\n"+
			"формат команды: /addbirthday ИМЯ МЕСЯЦ-ДЕНЬ \n"+
			"месяц и день указывать цифрами. \n"+
			"/wishes - показать текущий список пожеланий \n"+
			"/addwish - добавить пожелание . формат команды: \n"+
			"/addwish ПОЖЕЛАНИЕ \n"+
			"/deletebday - удалить именинника \n"+
			"формат команды: '/deletebday ИМЯ' \n"+
			"/deletewish - удалить пожелание \n"+
			"формат команды: '/deletewish ПОЖЕЛАНИЕ'",
			
			"this bot is about happy birthday greetings! \n"+
			"list of the available commands: \n"+
			"/birthdays - show current user list \n"+
			"/addbirthday - add user to the list \n"+
			"usage: '/addbirthday NAME M-D' \n"+
			"please input M-month and D-date as numbers \n"+
			"/wishes - show current list of wishes \n"+
			"/addwish - add a wish \n"+
			"usage: '/addwish WISH' \n"+
			"/deletebday - delete user from the list \n"+
			"usage: '/deletebday NAME' \n"+
			"/deletewish - delete wish from list of the wishes \n"+
			"usage: '/deletewish WISH'"
			
					
	};
}
