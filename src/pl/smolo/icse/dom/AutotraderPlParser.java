package pl.smolo.icse.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.utils.StringUtils;
import pl.smolo.icse.utils.URLUtils;

public class AutotraderPlParser extends AbstractResultsPageParser {

	private static final String MAIN_DIV_CLASS = "ListaWynikow";
	
	private static final String MAIN_PAGE = "http://www.autotrader.pl";
	
	@Override
	protected Element findMainElement(Document pmDocument)
	{
		NodeList divs = pmDocument.getElementsByTagName("div");
		for (int i = 0; i < divs.getLength(); i++)
		{
			if (MAIN_DIV_CLASS.equals(((Element)divs.item(i)).getAttribute("class")))
					return (Element)divs.item(i);
		}
		
		return null;
	}
	
	@Override
	public List<SamochodRow> parseElementToResults(Element pmElement) {
		
		List<SamochodRow> lvWynik = new ArrayList<SamochodRow>();
		
		NodeList singleDivs = pmElement.getChildNodes();
		for (int i = 0; i < singleDivs.getLength(); i++)
		{
			if (!(singleDivs.item(i) instanceof Element))
				continue;
			
			Element carDiv = (Element) singleDivs.item(i);
			if (!"div".equals(carDiv.getNodeName()))
				continue;
			
			SamochodRow samochod = new SamochodRow();
			
			NodeList spanList = carDiv.getElementsByTagName("span");
			if (spanList.getLength() > 1)
			{
				samochod.setImageHref(URLUtils.EMPTY_IMAGE);
				Element aTitleEl = (Element)carDiv.getElementsByTagName("a").item(0);
				samochod.setCarHref(MAIN_PAGE + aTitleEl.getAttribute("href"));
			}
			else
			{
				Element imgEl = (Element)carDiv.getElementsByTagName("img").item(0);
				samochod.setImageHref(imgEl.getAttribute("src"));
				
				Element aTitleEl = (Element)carDiv.getElementsByTagName("a").item(1);
				samochod.setCarHref(MAIN_PAGE + aTitleEl.getAttribute("href"));
			}
			
			NodeList strongs = carDiv.getElementsByTagName("strong");
			
			samochod.setCarName(strongs.item(0).getFirstChild().getNodeValue());
			samochod.setPrice(strongs.item(1).getFirstChild().getNodeValue() + " PLN");
			
			samochod.setSource("autotrader.pl");
			
			NodeList innerDivs = carDiv.getElementsByTagName("div");
			for (int j = 0; j < innerDivs.getLength(); j++)
			{
				Element div = (Element) innerDivs.item(j);
				String lvClass = div.getAttribute("class");
				if (!StringUtils.isEmpty(lvClass) && lvClass.toLowerCase().contains("opisoferty"))
				{
					samochod.setShortDesc("<html>" + StringUtils.insertBreakLines(div.getFirstChild().getNodeValue()) + "</html>");
					break;
				}
			}
			
			lvWynik.add(samochod);
		}
		
		return lvWynik;
	}
}
