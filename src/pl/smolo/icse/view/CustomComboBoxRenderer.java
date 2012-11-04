package pl.smolo.icse.view;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomComboBoxRenderer implements ListCellRenderer<Object>
{

	@Override
	public Component getListCellRendererComponent(JList<?> pmList, Object value, int pmIndex, boolean isSelected,
			boolean hasFocus)
	{
		JCheckBox checkbox = new JCheckBox((String)value);
		checkbox.setSelected(isSelected);
		return checkbox;
	}

}
