package pl.smolo.icse.model;

import pl.smolo.icse.utils.TextFieldName;

public class UstawieniaField
{
	private String fieldText;
	private TextFieldName fieldName;
	
	public UstawieniaField(String pmText, TextFieldName pmName)
	{
		fieldText = pmText;
		fieldName = pmName;
	}

	public String getFieldText()
	{
		return fieldText;
	}

	public void setFieldText(String fieldText)
	{
		this.fieldText = fieldText;
	}

	public TextFieldName getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(TextFieldName fieldName)
	{
		this.fieldName = fieldName;
	}
	
	@Override
	public String toString()
	{
		return fieldText;
	}
}
