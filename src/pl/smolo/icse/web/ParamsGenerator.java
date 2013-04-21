package pl.smolo.icse.web;

import pl.smolo.icse.model.Ustawienia;
import pl.smolo.icse.utils.StringUtils;

public abstract class ParamsGenerator {
	
	public abstract String getTotalResultsCountURL();

	public abstract String getSearchURL();
	
	public abstract String getSortPriceDesc();
	
	public abstract String getSortPriceAsc();
	
	public abstract String getPriceMin();
	
	public abstract String getPriceMax();
	
	public abstract String getYearMin();
	
	public abstract String getYearMax();
	
	public abstract String getMileageMin();
	
	public abstract String getMileageMax();
	
	public abstract String getMakeValue(String pmMake);
	
	public abstract String getModelValue(String pmModel);
	
	public String getFullSearchUrl()
	{
		Ustawienia ustawienia = Ustawienia.getInstance();
		String lvURL = getSearchURL();
		if (!lvURL.endsWith("?"))
			lvURL += "&";
		
		if (!StringUtils.isEmpty(ustawienia.getCenaOd()))
			lvURL += getPriceMin() + ustawienia.getCenaOd() + "&";
		if (!StringUtils.isEmpty(ustawienia.getCenaDo()))
			lvURL += getPriceMax() + ustawienia.getCenaDo() + "&";
		if (!StringUtils.isEmpty(ustawienia.getRocznikOd()))
			lvURL += getYearMin() + ustawienia.getRocznikOd() + "&";
		if (!StringUtils.isEmpty(ustawienia.getRocznikDo()))
			lvURL += getYearMax() + ustawienia.getRocznikDo() + "&";
		if (!StringUtils.isEmpty(ustawienia.getPrzebiegOd()))
			lvURL += getMileageMin() + ustawienia.getPrzebiegOd() + "&";
		if (!StringUtils.isEmpty(ustawienia.getPrzebiegDo()))
			lvURL += getMileageMax() + ustawienia.getPrzebiegDo() + "&";
			
		return lvURL;
	}
}
