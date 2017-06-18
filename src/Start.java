import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Start {
	
	private static List<Data> dataBase;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public static void main(String[] args) {
		dataBase = new ArrayList<Data>();
		
		//Exemple data
		dataBase.add(new Data("foo", "B", new Float(0.50f), "EUR",  new GregorianCalendar(2017, 5, 1), new GregorianCalendar(2017, 5, 2), new Integer(200), new Float(100.25f)));
		dataBase.add(new Data("bar", "S", new Float(0.22f), "GBP",  new GregorianCalendar(2017, 5, 5), new GregorianCalendar(2017, 5, 7), new Integer(450), new Float(150.5f)));
		dataBase.add(new Data("foo", "S", new Float(0.50f), "EUR",  new GregorianCalendar(2017, 5, 1), new GregorianCalendar(2017, 5, 2), new Integer(200), new Float(120.25f)));
		dataBase.add(new Data("bar", "B", new Float(0.22f), "GBP",  new GregorianCalendar(2017, 5, 5), new GregorianCalendar(2017, 5, 7), new Integer(450), new Float(130.5f)));
		
		menu();
	}
	
	public static void menu(){
        int choice;  

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Reports: ");
        System.out.println("1. Amount in USD settled incoming everyday");
        System.out.println("2. Amount in USD settled outgoing everyday");
        System.out.println("3. Ranking of entities based on incoming and outgoing amount");
        System.out.println("4. Exit");

        choice = keyboard.nextInt();
        keyboard.close();

        if(choice == 1){
        	dataBase.sort(Comparator.comparing(Data::getInstructionDate));
        	
        	for(Data data : dataBase)
        	{
        		if(data.getTransaction().equalsIgnoreCase("B"))
        		{
        			int numUnitUsed = 0;
        			
        			while(!data.getInstructionDate().after(data.getSettlementDate()))
        			{
        				int dayOfWeek = data.getInstructionDate().get(GregorianCalendar.DAY_OF_WEEK);
        				
        				if(!(dayOfWeek == 7 || dayOfWeek == 1))
        				{
        					System.out.println("-----------");
        					System.out.println("Date: " + sdf.format(data.getInstructionDate().getTime()));
                    		System.out.println("Entity: " + data.getEntity());
                    		System.out.println("Amount: " + data.amountUSDperUnit());
        				}
        				else if(data.getUnit().compareTo(new Integer(numUnitUsed)) == 0)
        				{
        					if(dayOfWeek == 7)
        						data.getInstructionDate().add(GregorianCalendar.DATE, 2);
        					if(dayOfWeek == 1)
        						data.getInstructionDate().add(GregorianCalendar.DATE, 1);
        					
        					System.out.println("-----------");
                    		System.out.println("Entity: " + data.getEntity());
                    		System.out.println("Amount: " + data.amountUSDperUnit());
        				}
        				
                		data.getInstructionDate().add(GregorianCalendar.DATE, 1);
        				
                		numUnitUsed++;
        			}
        		}
        	}
        }else if (choice == 2){
        	dataBase.sort(Comparator.comparing(Data::getInstructionDate));
        	
        	for(Data data : dataBase)
        	{
        		if(data.getTransaction().equalsIgnoreCase("S"))
        		{
        			int numUnitUsed = 0;
        			
        			while(!data.getInstructionDate().after(data.getSettlementDate()))
        			{
        				int dayOfWeek = data.getInstructionDate().get(GregorianCalendar.DAY_OF_WEEK);
        				
        				if(!(dayOfWeek == 7 || dayOfWeek == 1))
        				{
        					System.out.println("-----------");
        					System.out.println("Date: " + data.getInstructionDate());
                    		System.out.println("Entity: " + data.getEntity());
                    		System.out.println("Amount: " + data.amountUSDperUnit());
        				}
        				else if(data.getUnit().compareTo(new Integer(numUnitUsed)) == 0)
        				{
        					if(dayOfWeek == 7)
        						data.getInstructionDate().add(GregorianCalendar.DATE, 2);
        					if(dayOfWeek == 1)
        						data.getInstructionDate().add(GregorianCalendar.DATE, 1);
        					
        					System.out.println("-----------");
        					System.out.println("Date: " + data.getInstructionDate());
                    		System.out.println("Entity: " + data.getEntity());
                    		System.out.println("Amount: " + data.amountUSDperUnit());
        				}

                		data.getInstructionDate().add(GregorianCalendar.DATE, 1);
                		
                		numUnitUsed++;
        			}
        		}
        	}
        }else if (choice == 3){
        	dataBase.sort(Comparator.comparing(Data::amountUSDTotal).reversed());
        	
        	int numRanking = 1; 

			System.out.println("INCOMING");
        	for(Data data : dataBase)
        	{
        		if(data.getTransaction().equalsIgnoreCase("B"))
        		{
        			System.out.println(numRanking + ". " + data.getEntity() + " --> amount: " + data.amountUSDTotal());
        			numRanking++;
        		}
        	}
        	
        	numRanking = 1; //Reset value

			System.out.println("OUTGOING");
        	for(Data data : dataBase)
        	{
        		if(data.getTransaction().equalsIgnoreCase("S"))
        		{
        			System.out.println(numRanking + ". " + data.getEntity() + " --> amount: " + data.amountUSDTotal());
        			numRanking++;
        		}
        	}
        }else if (choice == 4){
            System.exit(0);
        }else{
            System.out.println("Please pick either option 1, 2, 3 or 4.");
            menu();
        }
    }
	
}
