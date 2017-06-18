import java.util.GregorianCalendar;

public class Data {
	
	private String entity;
	private String transaction;
	private Float agreedFx;
	private String currency;
	private GregorianCalendar instructionDate;
	private GregorianCalendar settlementDate;
	private Integer unit;
	private Float priceUnit;
	
	public Data(String entity, String transaction, Float agreedFx, String currency, GregorianCalendar instructionDate,
			GregorianCalendar settlementDate, Integer unit, Float priceUnit) {
		super();
		this.entity = entity;
		this.transaction = transaction;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.unit = unit;
		this.priceUnit = priceUnit;
	}
	
	public Float amountUSDTotal()
	{
		return this.amountUSD(true);
	}
	
	public Float amountUSDperUnit()
	{
		return this.amountUSD(false);
	}
	
	private Float amountUSD(boolean withUnits)
	{
		Float amount = new Float(0);
		
		if(withUnits)
		{
			amount = priceUnit * unit;
		}
		else
		{
			amount = priceUnit;
		}
		
		Float amountonverted = new Float(0);
		
		if(!this.currency.equals("USD"))
		{
			Converter converter = new Converter();
			
			amountonverted = converter.process(amount, this.currency, "USD");
		}
		else{
			amountonverted = amount;
		}
		
		amount = amountonverted * this.agreedFx;
		
		return amount;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public Float getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(Float agreedFx) {
		this.agreedFx = agreedFx;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public GregorianCalendar getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(GregorianCalendar instructionDate) {
		this.instructionDate = instructionDate;
	}
	public GregorianCalendar getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(GregorianCalendar settlementDate) {
		this.settlementDate = settlementDate;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Float getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(Float priceUnit) {
		this.priceUnit = priceUnit;
	}
}
