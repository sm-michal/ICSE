package pl.smolo.icse.view;

import javax.swing.DefaultListSelectionModel;

public class MultiSelectListSelectionModel extends DefaultListSelectionModel
{
	private static final long serialVersionUID = 8654586606246976624L;

	@Override
	public void setSelectionInterval(int index0, int index1)
	{
		if (super.isSelectedIndex(index0))
			super.removeSelectionInterval(index0, index1);
		else
			super.addSelectionInterval(index0, index1);
	}
}
