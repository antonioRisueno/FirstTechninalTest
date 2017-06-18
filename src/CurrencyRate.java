
public class CurrencyRate {
	private final String name;
    private final double rate;
    
    public CurrencyRate(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

	public String getName() {
		return name;
	}

	public double getRate() {
		return rate;
	}
    
}