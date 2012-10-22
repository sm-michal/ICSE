package pl.smolo.icse.dom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

import pl.smolo.icse.model.SamochodRow;

/**
 * Klasa z metodami niezbednymi do wyciagniecia poszczegolnych rekordow ze
 * strony html
 * 
 * @author smolo
 * 
 */
public abstract class AbstractResultsPageParser {

	/**
	 * 
	 * @param input
	 */
	public List<SamochodRow> getResults(InputStream input)
	{
		List<SamochodRow> lvWynik = new ArrayList<SamochodRow>();
		
		Document lvParsedDoc = convertFromHtmlStream(input);

		Element lvElement = findMainElement(lvParsedDoc);
		
		lvWynik = parseElementToResults(lvElement);

		return lvWynik;
	}
	

	/**
	 * Przekszalca strone html na Document
	 * 
	 * @param input
	 * @return
	 */
	private Document convertFromHtmlStream(InputStream input)
	{
		Tidy tidy = new Tidy();

		Document doc = null;

		File lvTmpFile = null;
		FileOutputStream lvFOS = null;
		try
		{
			lvTmpFile = new File("test.txt");
			if (!lvTmpFile.exists())
				lvTmpFile.createNewFile();

			lvFOS = new FileOutputStream(lvTmpFile);

			Properties properties = new Properties();
			properties.setProperty("tidy-mark", "false");
			properties.setProperty("output-xml", "no");
			properties.setProperty("numeric-entities", "yes");
			properties.setProperty("indent-spaces", "2");
			properties.setProperty("indent-attributes", "no");
			properties.setProperty("markup", "yes");
			properties.setProperty("wrap", "2000");
			properties.setProperty("uppercase-tags", "no");
			properties.setProperty("uppercase-attributes", "no");
			properties.setProperty("quiet", "no");
			properties.setProperty("clean", "yes");
			properties.setProperty("show-warnings", "no");
			properties.setProperty("break-before-br", "yes");
			properties.setProperty("hide-comments", "yes");
			properties.setProperty("output-html", "yes");
			properties.setProperty("input-encoding", "UTF8");
			properties.setProperty("quote-ampersand", "yes");

			tidy.getConfiguration().addProps(properties);

			doc = tidy.parseDOM(input, lvFOS);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				lvFOS.close();
			} 
			catch (Exception e) {
			}
		}

		return doc;
	}
	
	protected abstract Element findMainElement(Document pmDocument);
	
	protected abstract List<SamochodRow> parseElementToResults(Element pmElement);
}
