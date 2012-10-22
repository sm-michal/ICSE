package pl.smolo.icse.web;

public interface ParamsGenerator {

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
}
