package pl.smolo.icse.web;


public class AutotraderPlParams extends ParamsGenerator
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
		return "http://autotrader.pl/samochody_osobowe?";
	}

	@Override
	public String getSortPriceDesc()
	{
		return "so=1&sd=1";
	}

	@Override
	public String getSortPriceAsc()
	{
		return "so=1";
	}
	
	@Override
	public String getPriceMin()
	{
		return "pf=";
	}

	@Override
	public String getPriceMax()
	{
		return "pt=";
	}

	@Override
	public String getYearMin()
	{
		return "yf=";
	}

	@Override
	public String getYearMax()
	{
		return "yt=";
	}

	@Override
	public String getMileageMin()
	{
		return "mf=";
	}

	@Override
	public String getMileageMax()
	{
		return "mt=";
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
