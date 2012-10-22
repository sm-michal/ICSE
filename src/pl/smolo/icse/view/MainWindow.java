package pl.smolo.icse.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
	
	/**
	 * Wypisane wszystkie opcje wyszukiwania, kazda moze byc osobno usunieta
	 * Stronicowanie
	 * sortowanie po nazwie, cenie
	 * lista z wynikami
	 */
	private JPanel resultsPanel;
	
	public MainWindow()
	{
		super();
		initComponents();
	}
	
	private void initComponents()
	{
		this.setSize(1200, 750);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
}
