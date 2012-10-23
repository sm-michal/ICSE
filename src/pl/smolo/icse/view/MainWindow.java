package pl.smolo.icse.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 102085096118702079L;

	/**
	 * Panel z opcjami wysazukiwania - jak na autotrader.pl
	 * Przycisk SZUKAJ powinien byc zawsze widoczny
	 * Przycisk Wyczysc zeruje wszystkie selekcje
	 * Marka - combo z multiselekcja
	 * Model - enable dopiero po wybraniu marki, combo z multiselekcja, w combo podzial na marki
	 * Cena - od - do
	 * Rocznik - od - do
	 * Przebieg - od - do
	 * Moc - od - do
	 * Pojemnosc - od - do
	 * 
	 * Dodatkowe opcje - wyswietlane po kliknieciu "Wiecej opcji"
	 * Rodzaj paliwa
	 * Nadwozie
	 * Opcje wyposażenia
	 */
	private JPanel searchOptionsPanel;
	
	/**
	 * menu1
	 * 	nowe wyszukiwanie
	 *  poprzednie wyszukiwanie - rozwijane z wszystkimi poprzednimi
	 *  zakoncz
	 * menu2 - portale
	 *  lista portali z checkboxami, zaznaczenie zmienia ustawienia, powinno zmieniac aktualne wyszukiwanie
	 * menu3
	 *  help - krotka instrukcja
	 *  o programie - info
	 *  
	 */
	private JMenuBar menuBar;
	
	private JMenu startMenu;
	private JMenuItem newSearchMenuItem;
	private JMenuItem historyMenuItem;
	private JMenuItem quitMenuItem;
	
	private JMenu sitesMenu;
	private JCheckBoxMenuItem mobileEuMenuItem;
	private JCheckBoxMenuItem autotraderPlMenuItem;
	
	private JMenu helpMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;
	
	/**
	 * Wypisane wszystkie opcje wyszukiwania, kazda moze byc osobno usunieta
	 * Stronicowanie
	 * sortowanie po nazwie, cenie
	 * lista z wynikami
	 */
	private JPanel resultsPanel;
	
	private MainWindowActionListener actionListener;
	
	public MainWindow()
	{
		super();
		initComponents();
	}
	
	private void initComponents()
	{
		actionListener = new MainWindowActionListener();
		
		this.setSize(1200, 750);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		
		startMenu = new JMenu("Start");
		newSearchMenuItem = new JMenuItem("Nowe wyszukiwanie");
		newSearchMenuItem.addActionListener(actionListener);
		
		historyMenuItem = new JMenuItem("Poprzednie wyszukiwania");
		historyMenuItem.addActionListener(actionListener);
		
		quitMenuItem = new JMenuItem("Wyjście");
		quitMenuItem.addActionListener(actionListener);
		
		startMenu.add(newSearchMenuItem);
		startMenu.add(historyMenuItem);
		startMenu.addSeparator();
		startMenu.add(quitMenuItem);
		
		sitesMenu = new JMenu("Portale");
		mobileEuMenuItem = new JCheckBoxMenuItem("mobile.eu");
		mobileEuMenuItem.addActionListener(actionListener);
		
		autotraderPlMenuItem = new JCheckBoxMenuItem("autotrader.pl");
		autotraderPlMenuItem.addActionListener(actionListener);
		
		sitesMenu.add(mobileEuMenuItem);
		sitesMenu.add(autotraderPlMenuItem);
		
		helpMenu = new JMenu("Pomoc");
		helpMenuItem = new JMenuItem("Pomoc");
		helpMenuItem.addActionListener(actionListener);
		
		aboutMenuItem = new JMenuItem("O programie");
		aboutMenuItem.addActionListener(actionListener);
		
		helpMenu.add(helpMenuItem);
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(startMenu);
		menuBar.add(sitesMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
		
		this.setVisible(true);
	}
	
	private class MainWindowActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent evt)
		{
			if (quitMenuItem.equals(evt.getSource()))
				System.exit(0);
		}
	}
}
