package pl.smolo.icse.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.DOMTextImpl;

import pl.smolo.icse.utils.StringUtils;
import pl.smolo.icse.utils.URLUtils;
import pl.smolo.icse.model.SamochodRow;

public class MobileEuParser extends AbstractResultsPageParser {

	private final String FORM_ELEMENT = "form";
	private final String FORM_ID = "parkAndCompareVehicle";

	@Override
	protected Element findMainElement(Document pmDocument)
	{
		// pobranie wszystkich elementow FORM
		NodeList forms = pmDocument.getElementsByTagName(FORM_ELEMENT);

		// w petli po formach wyszukanie po atrybucie ID wlasciwego forma
		for (int i = 0; i < forms.getLength(); i++)
			if (FORM_ID.equals(((Element) forms.item(i)).getAttribute("id")))
				return (Element) forms.item(i);

		return null;
	}
	
	@Override
	protected List<SamochodRow> parseElementToResults(Element pmElement) {
		List<SamochodRow> lvWynik = new ArrayList<SamochodRow>();

		Element table = (Element) pmElement.getFirstChild().getFirstChild()
		        .getFirstChild().getFirstChild();

		Element tbody = (Element) table.getChildNodes().item(1);

		NodeList trs = tbody.getChildNodes();
		for (int i = 0; i < trs.getLength(); i++)
		{
			if (!(trs.item(i) instanceof Element))
				continue;

			Element el = (Element) trs.item(i);

			if (el.getAttributes() != null && el.getAttributes().getLength() > 0)
				continue;

			SamochodRow samochod = new SamochodRow();

			NodeList tds = el.getChildNodes();
			for (int j = 0; j < tds.getLength(); j++)
			{
				if (!(tds.item(j) instanceof Element))
					continue;

				Element td = (Element) tds.item(j);
				String tdClass = td.getAttribute("class");
				if ("image".equals(tdClass))
				{
					try
					{
						samochod.setImageHref(((Element) ((Element) td).getElementsByTagName("img").item(0)).getAttribute("src"));
					}
					catch (Exception e)
					{
						samochod.setImageHref(URLUtils.EMPTY_IMAGE);
					}
				}
				else if ("description".equals(tdClass))
				{
					Node aNode = ((Element) td).getElementsByTagName("a").item(0);
					try
					{
						samochod.setCarName(StringUtils.trim(aNode.getFirstChild().getNodeValue()));
					}
					catch (Exception e)
					{
						samochod.setCarName("");
					}
						
					try
					{
						samochod.setCarHref(((Element) aNode).getAttribute("href"));
					}
					catch (Exception e)
					{
						samochod.setCarHref("");
					}
					String desc = "<html>";

					try
					{
						Node innerDiv = td.getFirstChild();
						NodeList nodes = innerDiv.getChildNodes();
						for (int k = 0; k < nodes.getLength(); k++)
							if (nodes.item(k) instanceof DOMTextImpl)
							{
								if (!StringUtils.isEmpty(nodes.item(k).getNodeValue()))
									desc += StringUtils.trim(nodes.item(k).getNodeValue()) + "<br>";
							}
					}
					catch (Exception e)
					{
					}
					desc += "</html>";

					samochod.setShortDesc(desc);

				}
				else if ("first-registration".equals(tdClass))
				{
					try
					{
						samochod.setYear(StringUtils.trim(td.getFirstChild().getNodeValue()));
					}
					catch (Exception e)
					{
						samochod.setYear("");
					}
				}
				else if ("mileage".equals(tdClass))
				{
					try
					{
						samochod.setMileage(StringUtils.trim(td.getFirstChild().getNodeValue()));
					}
					catch (Exception e)
					{
						samochod.setMileage("");
					}
				}
				else if ("price".equals(tdClass))
				{
					try
					{
						String price = StringUtils.trim(((Element) td).getElementsByTagName("span").item(0).getFirstChild().getNodeValue());
						samochod.setPrice(price.toUpperCase().replaceAll("BRUTTO ", "").replaceAll("NETTO ", ""));
					}
					catch (Exception e)
					{
						samochod.setPrice("");
					}
				}
			}
			
			samochod.setSource("mobile.eu");

			lvWynik.add(samochod);
		}

		return lvWynik;
	}
}
