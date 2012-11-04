package pl.smolo.icse.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	 * Opcje wyposazenia
	 */
	private JPanel searchOptionsPanel;
	
	private JButton clearAllButton;
	private JButton searchButton;
	private JScrollPane optionsScroll;
	private JPanel optionsInnerPanel;
	private JButton markButton;
	private JList<String> markList;
	private JButton modelButton;
	private JList<String> modelList;
	private JLabel priceLabel;
	private JTextField priceFromField;
	private JTextField priceToField;
	private JLabel yearLabel;
	private JTextField yearFromField;
	private JTextField yearToField;
	private JLabel mileageLabel;
	private JTextField mileageFromField;
	private JTextField mileageToField;
	private JLabel horsepowerLabel;
	private JTextField horsepowerFromField;
	private JTextField horsepowerToField;
	private JLabel capacityLabel;
	private JTextField capacityFromField;
	private JTextField capacityToField;
	
	private JButton moreOptionsButton;
	
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
		this.setLayout(null);
		this.setResizable(false);
		
		menuBar = new JMenuBar();
		
		startMenu = new JMenu("Start");
		newSearchMenuItem = new JMenuItem("Nowe wyszukiwanie");
		newSearchMenuItem.addActionListener(actionListener);
		
		historyMenuItem = new JMenuItem("Poprzednie wyszukiwania");
		historyMenuItem.addActionListener(actionListener);
		
		quitMenuItem = new JMenuItem("Wyjœcie");
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
		
		searchOptionsPanel = new JPanel();
		searchOptionsPanel.setLayout(new GridBagLayout());
		
		markButton = new JButton("Marka >>");
		markButton.setFocusPainted(false);
		markButton.setBorderPainted(false);
		markButton.setContentAreaFilled(false);
		markButton.addActionListener(actionListener);
		markButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		markList = new JList<String>(new String[]{"Opel","BMW"});
		markList.setSelectionModel(new MultiSelectListSelectionModel());
		markList.setCellRenderer(new CustomComboBoxRenderer());
		markList.setVisible(false);
		
		modelButton = new JButton("Model >>");
		modelButton.setFocusPainted(false);
		modelButton.setBorderPainted(false);
		modelButton.setContentAreaFilled(false);
		modelButton.addActionListener(actionListener);
		modelButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		modelList = new JList<String>(new String[]{"Astra","Vectra"});
		modelList.setSelectionModel(new MultiSelectListSelectionModel());
		modelList.setCellRenderer(new CustomComboBoxRenderer());
		modelList.setVisible(false);
		
		priceLabel = new JLabel("Cena");
		
		priceFromField = new JTextField(8);
		priceFromField.setSize(80, 20);
		
		priceToField = new JTextField(8);
		priceToField.setSize(80, 20);
		
		yearLabel = new JLabel("Rocznik");
		
		yearFromField = new JTextField(4);
		yearFromField.setSize(80, 20);
		
		yearToField = new JTextField(4);
		yearToField.setSize(80, 20);
		
		mileageLabel = new JLabel("Przebieg");
		
		mileageFromField = new JTextField(7);
		mileageFromField.setSize(80, 20);
		
		mileageToField = new JTextField(7);
		mileageToField.setSize(80, 20);
		
		horsepowerLabel = new JLabel("Moc");
		
		horsepowerFromField = new JTextField(4);
		horsepowerFromField.setSize(80, 20);
		
		horsepowerToField = new JTextField(4);
		horsepowerToField.setSize(80, 20);
		
		capacityLabel = new JLabel("Pojemnosc");
		
		capacityFromField = new JTextField(5);
		capacityFromField.setSize(80, 20);
		
		capacityToField = new JTextField(5);
		capacityToField.setSize(80, 20);
		
		searchOptionsPanel.add(markButton, 
				new GridBagConstraints(
						0, 
						0, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0),
						0,
						0)
		);
		searchOptionsPanel.add(markList,
				new GridBagConstraints(
						0, 
						1, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(modelButton, 
				new GridBagConstraints(
						0, 
						2, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0),
						0,
						0)
		);
		searchOptionsPanel.add(modelList,
				new GridBagConstraints(
						0, 
						3, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(priceLabel,
				new GridBagConstraints(
						0, 
						4, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("od"),
				new GridBagConstraints(
						0, 
						5, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(priceFromField,
				new GridBagConstraints(
						1, 
						5, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("do"),
				new GridBagConstraints(
						2, 
						5, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(priceToField,
				new GridBagConstraints(
						3, 
						5, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(yearLabel,
				new GridBagConstraints(
						0, 
						6, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("od"),
				new GridBagConstraints(
						0, 
						7, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(yearFromField,
				new GridBagConstraints(
						1, 
						7, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("do"),
				new GridBagConstraints(
						2, 
						7, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(yearToField,
				new GridBagConstraints(
						3, 
						7, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(mileageLabel,
				new GridBagConstraints(
						0, 
						8, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("od"),
				new GridBagConstraints(
						0, 
						9, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(mileageFromField,
				new GridBagConstraints(
						1, 
						9, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("do"),
				new GridBagConstraints(
						2, 
						9, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(mileageToField,
				new GridBagConstraints(
						3, 
						9, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(horsepowerLabel,
				new GridBagConstraints(
						0, 
						10, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("od"),
				new GridBagConstraints(
						0, 
						11, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(horsepowerFromField,
				new GridBagConstraints(
						1, 
						11, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("do"),
				new GridBagConstraints(
						2, 
						11, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(horsepowerToField,
				new GridBagConstraints(
						3, 
						11, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(capacityLabel,
				new GridBagConstraints(
						0, 
						12, 
						4, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("od"),
				new GridBagConstraints(
						0, 
						13, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(capacityFromField,
				new GridBagConstraints(
						1, 
						13, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(new JLabel("do"),
				new GridBagConstraints(
						2, 
						13, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		searchOptionsPanel.add(capacityToField,
				new GridBagConstraints(
						3, 
						13, 
						1, 
						1, 
						0.5, 
						0.5, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(0, 0, 0, 0), 
						0,
						0)
		);
		
		optionsScroll = new JScrollPane(searchOptionsPanel);
		optionsScroll.setSize(300, 640);
		optionsScroll.setLocation(0, 40);
		optionsScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		optionsInnerPanel = new JPanel();
		optionsInnerPanel.setLocation(0, 0);
		optionsInnerPanel.setSize(300, 680);
		optionsInnerPanel.setLayout(null);
		optionsInnerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		clearAllButton = new JButton("Wyczyœæ");
		clearAllButton.setSize(120, 30);
		clearAllButton.setLocation(20, 5);
		clearAllButton.addActionListener(actionListener);
		
		searchButton = new JButton("Szukaj");
		searchButton.setSize(120, 30);
		searchButton.setLocation(160, 5);
		searchButton.addActionListener(actionListener);
		
		optionsInnerPanel.add(clearAllButton);
		optionsInnerPanel.add(searchButton);
		optionsInnerPanel.add(optionsScroll);
		
		this.add(optionsInnerPanel);
		
		resultsPanel = new JPanel();
		resultsPanel.setSize(900, 725);
		resultsPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		resultsPanel.setLocation(300, 0);
		this.add(resultsPanel);
		
		this.setVisible(true);
	}
	
	private class MainWindowActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent evt)
		{
			if (quitMenuItem.equals(evt.getSource()))
				System.exit(0);
			else if (markButton.equals(evt.getSource()))
			{
				markList.setVisible(!markList.isVisible());
			}
			else if (modelButton.equals(evt.getSource()))
			{
				modelList.setVisible(!modelList.isVisible());
			}
			else if (clearAllButton.equals(evt.getSource()))
			{
				System.out.println("wyczysc");
			}
			else if (searchButton.equals(evt.getSource()))
			{
				System.out.println("szukaj");
			}
		}
	}
}
