package pl.smolo.icse.view.custom;

import java.awt.Component;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import pl.smolo.icse.utils.CarModelsDb;

public class CustomComboBoxRenderer implements ListCellRenderer<Object>
{
	private JList<String> mParentList;
	
	public CustomComboBoxRenderer()
	{
	}
	
	public CustomComboBoxRenderer(JList<String> pmParentList)
	{
		mParentList = pmParentList;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> pmList, Object value, int pmIndex, boolean isSelected,
			boolean hasFocus)
	{
		String stringValue = value.toString();
		
		if (stringValue.startsWith("--"))
		{
			JLabel label = new JLabel(stringValue);
			label.setVisible(getVisibility(stringValue));
			return label;
		}
		else
		{
			JCheckBox checkbox = new JCheckBox(stringValue);
			checkbox.setSelected(isSelected);
			boolean isVisible = getVisibility(stringValue);
			if (!isVisible)
				checkbox.setSelected(false);
			
			checkbox.setVisible(isVisible);
			return checkbox;
		}
	}
	
	public boolean getVisibility(String value)
	{
		if (mParentList == null)
			return true;
		
		List<String> lvSelected = mParentList.getSelectedValuesList();
		if (lvSelected == null || lvSelected.size() == 0)
			return false;
		
		for (String mark : lvSelected)
		{
			if (value.startsWith("--") && value.contains(mark))
				return true;
			
			if (CarModelsDb.getModelsForMark(mark).contains(value))
				return true;
		}
		return false;
	}

}
