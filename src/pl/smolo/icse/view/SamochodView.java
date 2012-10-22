package pl.smolo.icse.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pl.smolo.icse.model.SamochodRow;
import pl.smolo.icse.utils.URLUtils;

/**
 * 
 * @author smolo
 * 
 */
public class SamochodView extends JPanel {

	private static final long serialVersionUID = 6906973155370233410L;
	/** Dane wyswietlanego samochodu */
	private SamochodRow samochodData;
	/** Label z miniatura */
	private JLabel imageLabel;
	/** Panel z nazwa i opisem */
	private JPanel descriptionPanel;
	/** Nazwa */
	private JLabel nameLabel;
	/** Krotki opis */
	private JLabel descriptionLabel;
	/** Rocznik */
	private JLabel yearLabel;
	/** Przebieg */
	private JLabel mileageLabel;
	/** Cena */
	private JLabel priceLabel;
	/** Serwis z ktorego pochodzi ogloszenie */
	private JLabel sourceLabel;

	public SamochodView(SamochodRow pmSamochod)
	{
		super();
		this.samochodData = pmSamochod;
		initComponents();
	}

	private void initComponents()
	{
		try
		{
			this.setMaximumSize(new Dimension(900, 150));
			this.setLayout(new GridBagLayout());
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
			this.addMouseListener(new SamochodMouseAdapter());

			ImageIcon icon = new ImageIcon(ImageIO.read(new URL(samochodData.getImageHref())));

			imageLabel = new JLabel();
			imageLabel.setIcon(icon);
			imageLabel.setPreferredSize(new Dimension(90, 80));

			nameLabel = new JLabel(samochodData.getCarName());
			nameLabel.setFont(new Font("Arial", Font.BOLD, 12));

			descriptionLabel = new JLabel(samochodData.getShortDesc());
			descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 9));

			descriptionPanel = new JPanel();
			descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
			descriptionPanel.setPreferredSize(new Dimension(400, 80));
			descriptionPanel.add(nameLabel);
			descriptionPanel.add(descriptionLabel);

			yearLabel = new JLabel(samochodData.getYear());
			yearLabel.setPreferredSize(new Dimension(90, 80));
			yearLabel.setHorizontalAlignment(SwingConstants.CENTER);

			mileageLabel = new JLabel(samochodData.getMileage());
			mileageLabel.setPreferredSize(new Dimension(100, 80));
			mileageLabel.setHorizontalAlignment(SwingConstants.CENTER);

			priceLabel = new JLabel(samochodData.getPrice());
			priceLabel.setPreferredSize(new Dimension(120, 80));
			priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			sourceLabel = new JLabel(samochodData.getSource());
			sourceLabel.setPreferredSize(new Dimension(80, 80));
			sourceLabel.setHorizontalAlignment(SwingConstants.CENTER);

			this.add(imageLabel,
			        new GridBagConstraints(
			                0,
			                0,
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
			this.add(descriptionPanel,
			        new GridBagConstraints(
			                1,
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
			this.add(yearLabel,
			        new GridBagConstraints(
			                5,
			                0,
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
			this.add(mileageLabel,
			        new GridBagConstraints(
			                6,
			                0,
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
			this.add(priceLabel,
			        new GridBagConstraints(
			                7,
			                0,
			                1,
			                1,
			                0.5,
			                0.5,
			                GridBagConstraints.CENTER,
			                GridBagConstraints.BOTH,
			                new Insets(0, 0, 0, 10),
			                0,
			                0)
			        );
			this.add(sourceLabel,
			        new GridBagConstraints(
			                8,
			                0,
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void applyHover()
	{
		// imageLabel.setBackground(Color.LIGHT_GRAY);
		// nameLabel.setBackground(Color.LIGHT_GRAY);
		nameLabel.setForeground(Color.BLUE);
		// descriptionLabel.setBackground(Color.LIGHT_GRAY);
		descriptionLabel.setForeground(Color.BLUE);
		yearLabel.setForeground(Color.BLUE);
		mileageLabel.setForeground(Color.BLUE);
		priceLabel.setForeground(Color.BLUE);
		sourceLabel.setForeground(Color.BLUE);
	}

	private void applyNormal()
	{
		nameLabel.setForeground(Color.BLACK);
		descriptionLabel.setForeground(Color.BLACK);
		yearLabel.setForeground(Color.BLACK);
		mileageLabel.setForeground(Color.BLACK);
		priceLabel.setForeground(Color.BLACK);
		sourceLabel.setForeground(Color.BLACK);
	}

	/**
	 * 
	 * @author smolo
	 * 
	 */
	private class SamochodMouseAdapter extends MouseAdapter
	{
		public void mouseClicked(MouseEvent evt)
		{
			System.out.println("Kliknales w auto " + samochodData.getCarName());
			URLUtils.openUrlInBrowser(samochodData.getCarHref());
		}

		public void mouseEntered(MouseEvent evt)
		{
			applyHover();
		}

		public void mouseExited(MouseEvent evt)
		{
			applyNormal();
		}
	}

}
