package pl.smolo.icse.utils;

/**
 * Metody pomocniczne dla klasy String. Wszystkie metody sa null-safe.
 * @author smolo
 */
public class StringUtils
{
	/**
	 * Usuwa poczatkowe i koncowe spacje.
	 * @param pmString
	 * @return
	 */
	public static String trim(String pmString)
	{
		if (pmString == null)
			return "";
		return pmString.trim();
	}

	/**
	 * Sprawdza, czy podany ciag znakow jest pusty
	 * @param pmString
	 * @return
	 */
	public static boolean isEmpty(String pmString)
	{
		return pmString == null || "".equals(pmString.trim());
	}

	/**
	 * Lamie podany ciag znakow do dlugosci 75 znakow, wstawiajac nowe linie
	 * @param pmString
	 * @return
	 */
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
