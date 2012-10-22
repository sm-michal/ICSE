package pl.smolo.icse.core;

import java.io.InputStream;
import java.util.List;

import pl.smolo.icse.dom.AbstractResultsPageParser;
import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.web.ParamsGenerator;

public class SimpleSearchEngine
{
	private AbstractResultsPageParser parser;
	
	private ParamsGenerator paramsGenerator;
	
	public SimpleSearchEngine(AbstractResultsPageParser pmParser, ParamsGenerator generator)
	{
		this.parser = pmParser;
		this.paramsGenerator = generator;
	}

	public AbstractResultsPageParser getParser()
	{
		return parser;
	}

	public void setParser(AbstractResultsPageParser parser)
	{
		this.parser = parser;
	}

	public ParamsGenerator getParamsGenerator()
	{
		return paramsGenerator;
	}

	public void setParamsGenerator(ParamsGenerator paramsGenerator)
	{
		this.paramsGenerator = paramsGenerator;
	}
	
	public List<SamochodRow> getResults(InputStream input)
	{
		return parser.getResults(input);
	}
}
