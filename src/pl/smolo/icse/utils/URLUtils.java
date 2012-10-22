package pl.smolo.icse.utils;

import pl.smolo.icse.utils.ApplicationUtils.OsType;

public class URLUtils {

	public static String EMPTY_IMAGE = "http://static.classistatic.de/git.b41400.master.51a4721/resources/images/dummies/dummy_ad_Car.gif";
	
	public static void openUrlInBrowser(String pmUrl)
	{
		OsType lvOS = ApplicationUtils.getOsType();

		Runtime lvRuntime = Runtime.getRuntime();

		try
		{
			switch (lvOS)
			{
			case WINDOWS:
				lvRuntime.exec("rundll32 url.dll,FileProtocolHandler " + pmUrl);
				break;
			case MAC:
				lvRuntime.exec("open " + pmUrl);
				break;
			case UNIX:
				lvRuntime.exec(new String[] { "sh", "-c", "firefox " + pmUrl });
				break;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
