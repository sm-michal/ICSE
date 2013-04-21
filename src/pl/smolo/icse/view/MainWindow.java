package pl.smolo.icse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
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
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import pl.smolo.icse.core.SearchExecutor;
import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.model.Ustawienia;
import pl.smolo.icse.model.UstawieniaField;
import pl.smolo.icse.utils.CarModelsDb;
import pl.smolo.icse.utils.TextFieldName;
import pl.smolo.icse.view.custom.CustomComboBoxRenderer;
import pl.smolo.icse.view.custom.CustomProgressMonitor;

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
	private JPanel pagingPanel;
	
	private JComboBox<UstawieniaField> checkedOptionsCombo;
	
	private JButton firstPageButton;
	private JButton previousPageButton;
	private JLabel currentPageLabel;
	private JButton nextPageButton;
	private JButton lastPageButton;
	
	private JScrollPane resultsScrollPanel;
	private JPanel resultsInnerPanel;
	
	private MainWindowActionListener actionListener;
	
	private Ustawienia ustawienia = Ustawienia.getInstance();
	
	private SearchExecutor searchExecutor = new SearchExecutor();
	private CustomProgressMonitor searchMonitor;
	
	public MainWindow()
	{
		super();
		initComponents();
	}
	
	private void initComponents()
	{
		actionListener = new MainWindowActionListener();
		
		this.setSize(1220, 750);
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
		mobileEuMenuItem.setState(ustawienia.isMobileEuActive());
		mobileEuMenuItem.addActionListener(actionListener);
		
		autotraderPlMenuItem = new JCheckBoxMenuItem("autotrader.pl");
		autotraderPlMenuItem.setState(ustawienia.isAutotraderActive());
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
		
		markList = new JList<String>(CarModelsDb.getModelsList());
		markList.setSelectionModel(new MultiSelectListSelectionModel());
		markList.setCellRenderer(new CustomComboBoxRenderer());
		markList.setVisible(false);
		markList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				List<String> selected = markList.getSelectedValuesList();
				
				Vector<String> wynik = new Vector<String>();
				for (String mark : selected)
				{
					wynik.add("--" + mark + "--");
					wynik.addAll(CarModelsDb.getModelsForMark(mark));
				}
				if (wynik.size() == 0)
					wynik.add("--Wybierz marke--");
				
				modelList.setListData(wynik);
			}
		});
		
		modelButton = new JButton("Model >>");
		modelButton.setFocusPainted(false);
		modelButton.setBorderPainted(false);
		modelButton.setContentAreaFilled(false);
		modelButton.addActionListener(actionListener);
		modelButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		modelList = new JList<String>(new String[]{"--Wybierz marke--"});
		modelList.setSelectionModel(new MultiSelectListSelectionModel());
		modelList.setCellRenderer(new CustomComboBoxRenderer(markList));
		modelList.setVisible(false);
		
		priceLabel = new JLabel("Cena");
		
		priceFromField = new JTextField(8);
		priceFromField.setSize(80, 20);
		priceFromField.getDocument().addDocumentListener(actionListener);
		priceFromField.getDocument().putProperty("name", TextFieldName.CENA_OD);
		
		priceToField = new JTextField(8);
		priceToField.setSize(80, 20);
		priceToField.getDocument().addDocumentListener(actionListener);
		priceToField.getDocument().putProperty("name", TextFieldName.CENA_DO);
		
		yearLabel = new JLabel("Rocznik");
		
		yearFromField = new JTextField(4);
		yearFromField.setSize(80, 20);
		yearFromField.getDocument().addDocumentListener(actionListener);
		yearFromField.getDocument().putProperty("name", TextFieldName.ROCZNIK_OD);
		
		yearToField = new JTextField(4);
		yearToField.setSize(80, 20);
		yearToField.getDocument().addDocumentListener(actionListener);
		yearToField.getDocument().putProperty("name", TextFieldName.ROCZNIK_DO);
		
		mileageLabel = new JLabel("Przebieg");
		
		mileageFromField = new JTextField(7);
		mileageFromField.setSize(80, 20);
		mileageFromField.getDocument().addDocumentListener(actionListener);
		mileageFromField.getDocument().putProperty("name", TextFieldName.PRZEBIEG_OD);
		
		mileageToField = new JTextField(7);
		mileageToField.setSize(80, 20);
		
		horsepowerLabel = new JLabel("Moc");
		
		horsepowerFromField = new JTextField(4);
		horsepowerFromField.setSize(80, 20);
		horsepowerFromField.getDocument().addDocumentListener(actionListener);
		horsepowerFromField.getDocument().putProperty("name", TextFieldName.MOC_OD);
		
		horsepowerToField = new JTextField(4);
		horsepowerToField.setSize(80, 20);
		horsepowerToField.getDocument().addDocumentListener(actionListener);
		horsepowerToField.getDocument().putProperty("name", TextFieldName.MOC_DO);
		
		capacityLabel = new JLabel("Pojemnosc");
		
		capacityFromField = new JTextField(5);
		capacityFromField.setSize(80, 20);
		capacityFromField.getDocument().addDocumentListener(actionListener);
		capacityFromField.getDocument().putProperty("name", TextFieldName.POJEMNOSC_OD);
		
		capacityToField = new JTextField(5);
		capacityToField.setSize(80, 20);
		capacityToField.getDocument().addDocumentListener(actionListener);
		capacityToField.getDocument().putProperty("name", TextFieldName.POJEMNOSC_DO);
		
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
		searchOptionsPanel.add(new JLabel(" od "),
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
		searchOptionsPanel.add(new JLabel(" do "),
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
		searchOptionsPanel.add(new JLabel(" od "),
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
		searchOptionsPanel.add(new JLabel(" do "),
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
		searchOptionsPanel.add(new JLabel(" od "),
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
		searchOptionsPanel.add(new JLabel(" do "),
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
		searchOptionsPanel.add(new JLabel(" od "),
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
		searchOptionsPanel.add(new JLabel(" do "),
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
		searchOptionsPanel.add(new JLabel(" od "),
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
		searchOptionsPanel.add(new JLabel(" do "),
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
		resultsPanel.setSize(920, 725);
		resultsPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		resultsPanel.setLayout(new BorderLayout());
		resultsPanel.setLocation(300, 0);
		
		pagingPanel = new JPanel();
		pagingPanel.setSize(920, 100);
		pagingPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		pagingPanel.setLocation(0, 0);
		
		checkedOptionsCombo = new JComboBox<UstawieniaField>();
		checkedOptionsCombo.removeAllItems();
		checkedOptionsCombo.setSize(150, 25);
		checkedOptionsCombo.setVisible(false);
		checkedOptionsCombo.addItemListener(actionListener);
		
		firstPageButton = new JButton("|<<");
		firstPageButton.setSize(40, 25);
		
		previousPageButton = new JButton(" < ");
		previousPageButton.setSize(40, 25);
		
		currentPageLabel = new JLabel(" 0 - 0 / 0 ");
		currentPageLabel.setSize(60, 25);
		
		nextPageButton = new JButton(" > ");
		nextPageButton.setSize(40, 25);
		
		lastPageButton = new JButton(">>|");
		lastPageButton.setSize(40, 25);
		
		pagingPanel.add(checkedOptionsCombo);
		pagingPanel.add(firstPageButton);
		pagingPanel.add(previousPageButton);
		pagingPanel.add(currentPageLabel);
		pagingPanel.add(nextPageButton);
		pagingPanel.add(lastPageButton);
		
		resultsInnerPanel = new JPanel();
		resultsInnerPanel.setLayout(new BoxLayout(resultsInnerPanel, BoxLayout.PAGE_AXIS));
		
		resultsScrollPanel = new JScrollPane(resultsInnerPanel);
		resultsScrollPanel.setSize(920, 625);
		resultsScrollPanel.setLocation(0, 100);
		
		resultsPanel.add(pagingPanel, BorderLayout.PAGE_START);
		resultsPanel.add(resultsScrollPanel);
		
		this.add(resultsPanel);
		
		this.setVisible(true);
	}
	
	private void updateOptionsCombo()
	{
		UstawieniaField[] lvOptions = Ustawienia.getInstance().getUstawieniaList();
		checkedOptionsCombo.removeItemListener(actionListener);
		checkedOptionsCombo.removeAllItems();
		for (UstawieniaField lvOpt : lvOptions)
			checkedOptionsCombo.addItem(lvOpt);
		
		checkedOptionsCombo.setVisible(lvOptions.length > 0);
		checkedOptionsCombo.addItemListener(actionListener);
	}
	
	private void wyswietlWynik()
	{
		searchMonitor = new CustomProgressMonitor(MainWindow.this, "Trwa wczytywanie danych...", "", 0, 100, true, "Czekaj...", true);
		searchMonitor.setMillisToPopup(0);
		searchMonitor.setMillisToDecideToPopup(0);
		
		SwingWorker<Void, Void> lvWorker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception
			{
				try
				{
					setProgress(1);
					
					searchButton.setEnabled(false);
					
					resultsInnerPanel.removeAll();
					
					updateOptionsCombo();
					
					List<SamochodRow> wyniki = searchExecutor.search();					
					for (SamochodRow samochod : wyniki)
						resultsInnerPanel.add(new SamochodView(samochod));
					
					resultsInnerPanel.updateUI();	
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				setProgress(100);
				
				return null;
			}
			
			@Override
			protected void done()
			{
				System.out.println("Done");
				searchButton.setEnabled(true);
			}
		};
		
		lvWorker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if ("progress".equals(evt.getPropertyName()))
				{
					int progress = (Integer)evt.getNewValue();
					searchMonitor.setProgress(progress);
				}
			}
		});
		lvWorker.execute();
		
	}
	
	private class MainWindowActionListener implements ActionListener, DocumentListener, ItemListener
	{
		private boolean optionComboSelectionChanged = false;
		
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
				ustawienia.reset();
			}
			else if (searchButton.equals(evt.getSource()))
			{
				wyswietlWynik();
			}
			else if (autotraderPlMenuItem.equals(evt.getSource()))
			{
				ustawienia.setAutotraderActive(!ustawienia.isAutotraderActive());
			}
			else if (mobileEuMenuItem.equals(evt.getSource()))
			{
				ustawienia.setMobileEuActive(!ustawienia.isMobileEuActive());
			}
			/*else if (checkedOptionsCombo.equals(evt.getSource()))
			{
				if (checkedOptionsCombo.getSelectedIndex() != -1 && checkedOptionsCombo.getItemCount() > 0)
				{
					checkedOptionsCombo.remove(checkedOptionsCombo.getSelectedIndex());
					wyswietlWynik();
				}
			}*/
		}

		@Override
		public void changedUpdate(DocumentEvent evt)
		{
			zmienUstawienia(evt);
		}

		@Override
		public void insertUpdate(DocumentEvent evt)
		{
			zmienUstawienia(evt);
		}

		@Override
		public void removeUpdate(DocumentEvent evt)
		{
			zmienUstawienia(evt);
		}
		
		private void zmienUstawienia(DocumentEvent evt)
		{
			Document doc = evt.getDocument();
			TextFieldName txName = (TextFieldName)doc.getProperty("name");
			try
			{
				ustawienia.update(txName.getSetterMethodName(), doc.getText(0, doc.getLength()));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if (checkedOptionsCombo.equals(e.getSource()) && e.getStateChange() == ItemEvent.SELECTED && optionComboSelectionChanged)
			{
				UstawieniaField field = (UstawieniaField)checkedOptionsCombo.getSelectedItem();
				
				Component[] lvComponents = searchOptionsPanel.getComponents();
				for (Component comp : lvComponents)
				{
					if (comp instanceof JTextField)
					{
						JTextField tf = (JTextField)comp;
						System.out.println(tf.getDocument().getProperty("name"));
						if (field.getFieldName() == tf.getDocument().getProperty("name"))
						{
							tf.setText("");
							break;
						}
					}
				}
				
				optionComboSelectionChanged = false;
				
				wyswietlWynik();
			}
			else if (checkedOptionsCombo.equals(e.getSource()) && e.getStateChange() == ItemEvent.DESELECTED && !optionComboSelectionChanged)
			{
				optionComboSelectionChanged = true;
			}
		}
	}
}
