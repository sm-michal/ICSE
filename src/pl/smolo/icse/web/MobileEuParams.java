package pl.smolo.icse.web;

public class MobileEuParams extends ParamsGenerator
{

	@Override
	public String getTotalResultsCountURL()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSearchURL()
	{
		return "http://szukaj.pl.mobile.eu/samochod/search.html?lang=pl&isSearchRequest=true&scopeId=C";
	}

	@Override
	public String getSortPriceDesc()
	{
		return "useCase=ChangeSortOrder&sortPath=price.consumerGrossEuro&defaultOrder=DESCENDING";
	}

	@Override
	public String getSortPriceAsc()
	{
		return "useCase=ChangeSortOrder&sortPath=price.consumerGrossEuro&defaultOrder=ASCENDING";
	}

	@Override
	public String getPriceMin()
	{
		return "minPrice=";
	}

	@Override
	public String getPriceMax()
	{
		return "maxPrice";
	}

	@Override
	public String getYearMin()
	{
		return "minConstructionYear=";
	}

	@Override
	public String getYearMax()
	{
		return "maxConstructionYear=";
	}

	@Override
	public String getMileageMin()
	{
		return "minMileage=";
	}

	@Override
	public String getMileageMax()
	{
		return "maxMileage=";
	}

	@Override
	public String getMakeValue(String pmMake)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelValue(String pmModel)
	{
		// TODO Auto-generated method stub
		return null;
	}

	

}
