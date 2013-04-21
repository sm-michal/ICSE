package pl.smolo.icse.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.smolo.icse.utils.StringUtils;
import pl.smolo.icse.utils.TextFieldName;

public class Ustawienia
{
	private static Ustawienia ustawieniaInstance = null;
	
	private List<String> marki = new ArrayList<String>();
	private List<String> modele = new ArrayList<String>();
	private String cenaOd;
	private String cenaDo;
	private String rocznikOd;
	private String rocznikDo;
	private String przebiegOd;
	private String przebiegDo;
	private String mocOd;
	private String mocDo;
	private String pojemnoscOd;
	private String pojemnoscDo;
	
	private boolean mobileEuActive = true;
	private boolean autotraderActive = true;
	
	private Ustawienia()
	{
	}
	
	public static Ustawienia getInstance()
	{
		if (ustawieniaInstance == null)
			ustawieniaInstance = new Ustawienia();
		
		return ustawieniaInstance;
	}
	
	public void reset()
	{
		marki.clear();
		modele.clear();
		cenaOd = null;
		cenaDo = null;
		rocznikOd = null;
		rocznikDo = null;
		przebiegOd = null;
		przebiegDo = null;
		mocOd = null;
		mocDo = null;
		pojemnoscOd = null;
		pojemnoscDo = null;
	}
	
	public void update(String pmMethodName, String pmValue) throws Exception
	{
		Method method = getClass().getDeclaredMethod(pmMethodName, String.class);
		method.invoke(ustawieniaInstance, pmValue);
	}
	
	public UstawieniaField[] getUstawieniaList()
	{
		List<UstawieniaField> lvUstawienia = new ArrayList<UstawieniaField>();
		//TODO dodac marki i modele
		if (!StringUtils.isEmpty(cenaOd))
			lvUstawienia.add(new UstawieniaField("Cena od " + cenaOd,TextFieldName.CENA_OD));
		if (!StringUtils.isEmpty(cenaDo))
			lvUstawienia.add(new UstawieniaField("Cena do " + cenaDo,TextFieldName.CENA_DO));
		if (!StringUtils.isEmpty(rocznikOd))
			lvUstawienia.add(new UstawieniaField("Rocznik od " + rocznikOd,TextFieldName.ROCZNIK_OD));
		if (!StringUtils.isEmpty(rocznikDo))
			lvUstawienia.add(new UstawieniaField("Rocznik do " + rocznikDo,TextFieldName.ROCZNIK_DO));
		
		
		return lvUstawienia.toArray(new UstawieniaField[0]);
	}

	public List<String> getMarki()
	{
		return marki;
	}

	public void setMarki(List<String> marki)
	{
		this.marki = marki;
	}

	public List<String> getModele()
	{
		return modele;
	}

	public void setModele(List<String> modele)
	{
		this.modele = modele;
	}

	public String getCenaOd()
	{
		return cenaOd;
	}

	public void setCenaOd(String cenaOd)
	{
		this.cenaOd = cenaOd;
	}

	public String getCenaDo()
	{
		return cenaDo;
	}

	public void setCenaDo(String cenaDo)
	{
		this.cenaDo = cenaDo;
	}

	public String getRocznikOd()
	{
		return rocznikOd;
	}

	public void setRocznikOd(String rocznikOd)
	{
		this.rocznikOd = rocznikOd;
	}

	public String getRocznikDo()
	{
		return rocznikDo;
	}

	public void setRocznikDo(String rocznikDo)
	{
		this.rocznikDo = rocznikDo;
	}

	public String getPrzebiegOd()
	{
		return przebiegOd;
	}

	public void setPrzebiegOd(String przebiegOd)
	{
		this.przebiegOd = przebiegOd;
	}

	public String getPrzebiegDo()
	{
		return przebiegDo;
	}

	public void setPrzebiegDo(String przebiegDo)
	{
		this.przebiegDo = przebiegDo;
	}

	public String getMocOd()
	{
		return mocOd;
	}

	public void setMocOd(String mocOd)
	{
		this.mocOd = mocOd;
	}

	public String getMocDo()
	{
		return mocDo;
	}

	public void setMocDo(String mocDo)
	{
		this.mocDo = mocDo;
	}

	public String getPojemnoscOd()
	{
		return pojemnoscOd;
	}

	public void setPojemnoscOd(String pojemnoscOd)
	{
		this.pojemnoscOd = pojemnoscOd;
	}

	public String getPojemnoscDo()
	{
		return pojemnoscDo;
	}

	public void setPojemnoscDo(String pojemnoscDo)
	{
		this.pojemnoscDo = pojemnoscDo;
	}

	public boolean isMobileEuActive()
	{
		return mobileEuActive;
	}

	public void setMobileEuActive(boolean mobileEuActive)
	{
		this.mobileEuActive = mobileEuActive;
	}

	public boolean isAutotraderActive()
	{
		return autotraderActive;
	}

	public void setAutotraderActive(boolean autotraderActive)
	{
		this.autotraderActive = autotraderActive;
	}
}
