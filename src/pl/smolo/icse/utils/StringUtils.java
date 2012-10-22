package pl.smolo.icse.utils;

public class StringUtils
{

	public static String trim(String pmString)
	{
		if (pmString == null)
			return "";
		return pmString.trim();
	}

	public static boolean isEmpty(String pmString)
	{
		return pmString == null || "".equals(pmString.trim());
	}

	public static String insertBreakLines(String pmString)
	{
		StringBuilder lvResult = new StringBuilder("");

		int lvLength = pmString.length();

		while (lvLength > 75)
		{
			String lvNewLine = pmString.substring(0, 75);
			int lvLastComma = lvNewLine.lastIndexOf(',');
			int lvLastSpace = lvNewLine.lastIndexOf(' ');
			
			int lvLineEnd = lvLastComma > lvLastSpace ? lvLastComma : lvLastSpace;
			
			lvResult.append(pmString.substring(0, lvLineEnd)).append("<br>");
			
			pmString = pmString.substring(lvLineEnd);
			
			lvLength = pmString.length();
		}
		
		lvResult.append(pmString);
		
		return lvResult.toString();
	}
}
