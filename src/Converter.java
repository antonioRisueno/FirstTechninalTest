import java.util.HashMap;

public class Converter {
	
	private HashMap<String, Float> currencies;
	
	public Converter()
	{
		currencies = new HashMap<String, Float>();
		currencies.put("USD", 1.0f);
		currencies.put("EUR", 0.78391f);
		currencies.put("GBP", 0.621484f);
		currencies.put("JPY", 107.174f);
	}

	public float process(Float amount, String fromCurrency, String toCurrency)
	{
		float rateToDollars = currencies.get(fromCurrency).floatValue();
		float rateToTarget = currencies.get(toCurrency).floatValue();
		
		Float targetAmount = (amount / rateToDollars) * rateToTarget;
		
		return targetAmount;
	}
}