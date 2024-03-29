package pl.smolo.icse.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CarModelsDb
{
	private static TreeMap<String, List<String>> mModelMap = new TreeMap<String, List<String>>(new Comparator<String>() {
		public int compare(String pmKey1, String pmKey2)
		{
			return pmKey1.compareTo(pmKey2);
		}
	});
	
	static
	{
		mModelMap.put("Abarth", Arrays.asList("500","500C","Grande Punto","Punto Evo"));
		mModelMap.put("Acura", Arrays.asList("MSX","NSX","RL","RSX","TL","TSX"));
//		mModelMap.put("Aixam", Arrays.asList(""));
		mModelMap.put("Alfa Romeo", Arrays.asList("146","147","156","166"));
//		mModelMap.put("Alpina", Arrays.asList(""));
//		mModelMap.put("Aro", Arrays.asList(""));
//		mModelMap.put("Artega", Arrays.asList(""));
//		mModelMap.put("Asia Motors", Arrays.asList(""));
//		mModelMap.put("Aston Martin", Arrays.asList(""));
		mModelMap.put("Audi", Arrays.asList("A1","A3","S3","A4","S4","A5","S5","A6","S6","A7","A8","S8"));
//		mModelMap.put("Austin", Arrays.asList(""));
//		mModelMap.put("Austin Healev", Arrays.asList(""));
//		mModelMap.put("Bentley", Arrays.asList(""));
		mModelMap.put("BMW", Arrays.asList("1","3","5","6","7","8"));
//		mModelMap.put("Borgward", Arrays.asList(""));
//		mModelMap.put("Brilliance", Arrays.asList(""));
//		mModelMap.put("Bugatti", Arrays.asList(""));
//		mModelMap.put("Buick", Arrays.asList(""));
//		mModelMap.put("Cadillac", Arrays.asList(""));
//		mModelMap.put("Caterham", Arrays.asList(""));
//		mModelMap.put("Chevrolet", Arrays.asList(""));
//		mModelMap.put("Chrysler", Arrays.asList(""));
		mModelMap.put("Citroen", Arrays.asList("C1","C2"));
//		mModelMap.put("Cobra", Arrays.asList(""));
//		mModelMap.put("Corvette", Arrays.asList(""));
//		mModelMap.put("Dacia", Arrays.asList(""));
		mModelMap.put("Daewoo", Arrays.asList("Espero","Matiz","Tico"));
//		mModelMap.put("Daihatsu", Arrays.asList(""));
//		mModelMap.put("Datsun", Arrays.asList(""));
		
	}

	/*
	 <select name="makeModelVariant1.makeId" id="selectMake1" class="selectMake1">
		<option value="">Wszystkie</option>
	    	<option value="140">Abarth</option>     
	    	<option value="375">Acura</option>     
	    	<option value="800">Aixam</option>     
	    	<option value="900">Alfa Romeo</option>     
	    	<option value="1100">Alpina</option>     
	    	<option value="30001">Aro</option>     
	    	<option value="121">Artega</option>     
	    	<option value="1750">Asia Motors</option>     
	    	<option value="1700">Aston Martin</option>     
	    	<option value="1900">Audi</option>     
	    	<option value="2000">Austin</option>     
	    	<option value="1950">Austin Healey</option>     
	    	<option value="3100">Bentley</option>     
	    	<option value="3500">BMW</option>     
	    	<option value="3850">Borgward</option>     
	    	<option value="4025">Brilliance</option>     
	    	<option value="4350">Bugatti</option>     
	    	<option value="4400">Buick</option>     
	    	<option value="4700">Cadillac</option>     
	    	<option value="5300">Caterham</option>     
	    	<option value="5600">Chevrolet</option>     
	    	<option value="5700">Chrysler</option>     
	    	<option value="5900">Citroën</option>     
	    	<option value="6200">Cobra</option>     
	    	<option value="6325">Corvette</option>     
	    	<option value="6600">Dacia</option>     
	    	<option value="6800">Daewoo</option>     
	    	<option value="7000">Daihatsu</option>     
	    	<option value="30002">Datsun</option>     
	    	<option value="7400">DeTomaso</option>     
	    	<option value="7700">Dodge</option>     
	    	<option value="30003">Eagle</option>     
	    	<option value="30004">Excalibur</option>     
	    	<option value="8600">Ferrari</option>     
	    	<option value="8800">Fiat</option>     
	    	<option value="172">Fisker</option>     
	    	<option value="9000">Ford</option>     
	    	<option value="37">Gaz</option>     
	    	<option value="30005">Geely</option>     
	    	<option value="9900">GMC</option>     
	    	<option value="122">Grecav</option>     
	    	<option value="30006">GWM</option>     
	    	<option value="10850">Holden</option>     
	    	<option value="11000">Honda</option>     
	    	<option value="11050">Hummer</option>     
	    	<option value="11600">Hyundai</option>     
	    	<option value="11650">Infiniti</option>     
	    	<option value="11900">Isuzu</option>     
	    	<option value="12100">Iveco</option>     
	    	<option value="12400">Jaguar</option>     
	    	<option value="12600">Jeep</option>     
	    	<option value="13200">Kia</option>     
	    	<option value="13450">Koenigsegg</option>     
	    	<option value="13900">KTM</option>     
	    	<option value="14400">Lada</option>     
	    	<option value="14600">Lamborghini</option>     
	    	<option value="14700">Lancia</option>     
	    	<option value="14800">Land Rover</option>     
	    	<option value="14845">Landwind</option>     
	    	<option value="15200">Lexus</option>     
	    	<option value="15400">Ligier</option>     
	    	<option value="15500">Lincoln</option>     
	    	<option value="15900">Lotus</option>     
	    	<option value="30007">Luaz</option>     
	    	<option value="16200">Mahindra</option>     
	    	<option value="30008">Martin Motors</option>     
	    	<option value="30009">Maruti</option>     
	    	<option value="16600">Maserati</option>     
	    	<option value="16700">Maybach</option>     
	    	<option value="16800">Mazda</option>     
	    	<option value="137">McLaren</option>     
	    	<option value="17200">Mercedes-Benz</option>     
	    	<option value="30010">Mercury</option>     
	    	<option value="17300">MG</option>     
	    	<option value="30011">Microcar</option>     
	    	<option value="17500">MINI</option>     
	    	<option value="17700">Mitsubishi</option>     
	    	<option value="17900">Morgan</option>     
	    	<option value="30012">Moskvici</option>     
	    	<option value="18700">Nissan</option>     
	    	<option value="18875">NSU</option>     
	    	<option value="18975">Oldsmobile</option>     
	    	<option value="30013">Oltcit</option>     
	    	<option value="19000">Opel</option>     
	    	<option value="149">Pagani</option>     
	    	<option value="19300">Peugeot</option>     
	    	<option value="19600">Piaggio</option>     
	    	<option value="19800">Plymouth</option>     
	    	<option value="38">Polonez</option>     
	    	<option value="20000">Pontiac</option>     
	    	<option value="20100">Porsche</option>     
	    	<option value="20200">Proton</option>     
	    	<option value="20700">Renault</option>     
	    	<option value="21600">Rolls Royce</option>     
	    	<option value="21700">Rover</option>     
	    	<option value="125">Ruf</option>     
	    	<option value="21800">Saab</option>     
	    	<option value="22000">Santana</option>     
	    	<option value="30014">Saturn</option>     
	    	<option value="39">Scion</option>     
	    	<option value="22500">Seat</option>     
	    	<option value="30015">Shelby</option>     
	    	<option value="22900">Skoda</option>     
	    	<option value="23000">Smart</option>     
	    	<option value="100">Spyker</option>     
	    	<option value="23100">Ssangyong</option>     
	    	<option value="23500">Subaru</option>     
	    	<option value="23600">Suzuki</option>     
	    	<option value="40">Syrena</option>     
	    	<option value="23800">Talbot</option>     
	    	<option value="41">Tarpan</option>     
	    	<option value="23825">Tata</option>     
	    	<option value="135">Tesla</option>     
	    	<option value="24100">Toyota</option>     
	    	<option value="24200">Trabant</option>     
	    	<option value="24400">Triumph</option>     
	    	<option value="24500">TVR</option>     
	    	<option value="30016">UAZ</option>     
	    	<option value="30017">Volga</option>     
	    	<option value="25200">Volkswagen</option>     
	    	<option value="25100">Volvo</option>     
	    	<option value="30018">Warszawa</option>     
	    	<option value="25300">Wartburg</option>     
	    	<option value="113">Westfield</option>     
	    	<option value="25650">Wiesmann</option>     
	    	<option value="42">Zastawa</option>     
	    	<option value="30019">ZAZ</option>     
	    	<option value="43">�uk</option>     
	    	<option value="1400">Inne</option>     
	</select> 
	 
	 */
	
	public static String[] getModelsList()
	{
		return mModelMap.keySet().toArray(new String[0]);
	}
	
	public static List<String> getModelsForMark(String pmMark)
	{
		if (StringUtils.isEmpty(pmMark) || !mModelMap.containsKey(pmMark))
			return new ArrayList<String>();
		
		return mModelMap.get(pmMark);
	}
	
	public static String[] getAllModels()
	{
		List<String> lvWynik = new ArrayList<String>();
		for (Entry<String, List<String>> entry : mModelMap.entrySet())
		{
			lvWynik.add("-- " + entry.getKey() + " --");
			lvWynik.addAll(entry.getValue());
		}
		return lvWynik.toArray(new String[0]);
	}
}
