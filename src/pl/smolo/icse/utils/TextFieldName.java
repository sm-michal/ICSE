package pl.smolo.icse.utils;

public enum TextFieldName
{
	CENA_OD("cenaOd"),
	CENA_DO("cenaDo"),
	ROCZNIK_OD("rocznikOd"),
	ROCZNIK_DO("rocznikDo"),
	PRZEBIEG_OD("przebiegOd"),
	PRZEBIEG_DO("przebiegDo"),
	MOC_OD("mocOd"),
	MOC_DO("mocDo"),
	POJEMNOSC_OD("pojemnoscOd"),
	POJEMNOSC_DO("pojemnoscDo");
	
	private String methodName;
	
	private TextFieldName(String pmMethodName)
	{
		methodName = pmMethodName;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
	
	public String getSetterMethodName()
	{
		return "set" + methodName.substring(0,1).toUpperCase() + methodName.substring(1);
	}
}