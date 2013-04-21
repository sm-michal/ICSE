package pl.smolo.icse.core;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import pl.smolo.icse.dom.AutotraderPlParser;
import pl.smolo.icse.dom.MobileEuParser;
import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.model.Ustawienia;
import pl.smolo.icse.web.AutotraderPlParams;
import pl.smolo.icse.web.MobileEuParams;

public class SearchExecutor
{
	private Ustawienia ustawienia = Ustawienia.getInstance();

	public List<SamochodRow> search() throws Exception
	{
		List<SimpleSearchEngine> engines = new ArrayList<SimpleSearchEngine>();

		if (ustawienia.isAutotraderActive())
			engines.add(new SimpleSearchEngine(new AutotraderPlParser(), new AutotraderPlParams()));
		if (ustawienia.isMobileEuActive())
			engines.add(new SimpleSearchEngine(new MobileEuParser(), new MobileEuParams()));

		List<SamochodRow> allResults = new ArrayList<SamochodRow>();

		for (SimpleSearchEngine engine : engines)
		{
			try
			{
				URL url = new URL(engine.getParamsGenerator().getFullSearchUrl());
				URLConnection conn = url.openConnection();
				List<SamochodRow> results = engine.getResults(conn.getInputStream());
				if (results != null)
					allResults.addAll(results);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return allResults;
	}
}
