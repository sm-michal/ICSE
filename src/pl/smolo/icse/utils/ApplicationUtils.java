package pl.smolo.icse.utils;

public class ApplicationUtils {

	public static OsType getOsType()
	{
		String lvOS = System.getProperty("os.name").toLowerCase();
		if (lvOS.indexOf("win") > -1)
			return OsType.WINDOWS;
		else if (lvOS.indexOf("mac") > -1)
			return OsType.MAC;
		else
			return OsType.UNIX;
	}

	public enum OsType
	{
		WINDOWS,
		UNIX,
		MAC
	}
}
