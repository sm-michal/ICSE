package pl.smolo.icse.test;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pl.smolo.icse.core.SimpleSearchEngine;
import pl.smolo.icse.dom.AutotraderPlParser;
import pl.smolo.icse.dom.MobileEuParser;
import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.view.SamochodView;
import pl.smolo.icse.web.AutotraderPlParams;
import pl.smolo.icse.web.MobileEuParams;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			List<SimpleSearchEngine> engines = new ArrayList<SimpleSearchEngine>();
			String answer;
			Scanner scanner = new Scanner(System.in);
			System.out.println("Czy uzyc mobile.eu? (t/n)");
			answer = scanner.next();
			if (answer.equalsIgnoreCase("t"))
				engines.add(new SimpleSearchEngine(new MobileEuParser(), new MobileEuParams()));
			
			System.out.println("Czy uzyc autotrader.pl? (t/n)");
			answer = scanner.next();
			if (answer.equalsIgnoreCase("t"))
				engines.add(new SimpleSearchEngine(new AutotraderPlParser(), new AutotraderPlParams()));
			
			scanner.close();
			
			List<SamochodRow> allRows = new ArrayList<SamochodRow>();
			
			for (int i = 0; i < engines.size(); i++)
			{
				SimpleSearchEngine sse = engines.get(i);

				URL url = new URL(sse.getParamsGenerator().getSearchURL());
				URLConnection conn = url.openConnection();
				List<SamochodRow> results = sse.getResults(conn.getInputStream());
				if (results != null)
					allRows.addAll(results);
			}
			
			JFrame mainFrame = new JFrame("wynik");
			mainFrame.setSize(920, 810);

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
			for (SamochodRow samochod : allRows)
				mainPanel.add(new SamochodView(samochod));

			JScrollPane scroll = new JScrollPane(mainPanel);

			mainFrame.add(scroll);
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// http://szukaj.pl.mobile.eu/samochod/search.html?scopeId=C&isSearchRequest=true&sortOption.sortBy=creationTime&sortOption.sortOrder=DESCENDING
		// &lang=pl&categories=SmallCar&categories=EstateCar&ambitCountry=&crossBorderSearchEnabled=true
	}

}
