package BestFareCalculator;
public class TransitCalculator {
	int numDays;
	int numRides;
	double [] prices = new double[]{2.75,33.00,127.00};
	
	public TransitCalculator(int days, int rides){
		numDays = days;
		numRides = rides;
	}

	public double unlimited7Price() {
		int numPasses = numDays / 7;
		if (numDays%7!=0)
			numPasses+=1;
		double pPerRide = (numPasses*prices[1]/numRides);
		return pPerRide;
		
	}
	public double unlimited30Price() {
		int numPasses = numDays / 30;
		if (numDays%30!=0)
			numPasses+=1;
		double pPerRide = (numPasses*prices[2]/numRides);
		return pPerRide;
		
	}
	public double pPerRidePrice() {
		double pPerRide = (numRides*prices[0]);
		return pPerRide;
	}
	
	public double [] getRiderPrices() {
		double [] pPerRide = new double[3];
		pPerRide[0] = pPerRidePrice();
		pPerRide[1]=unlimited7Price();
		pPerRide[2]=unlimited30Price();
		return pPerRide;
	}
	
	public String getBestFare() {
		String [] methods = new String[] {"Pay-per-ride", "7-day Unlimited", "30-day Unlimited"};
		double tempPrice = 10000;
		String tempMethod = "";
		for (int i=0; i<prices.length; i++) {
			if (prices[i]<tempPrice) {
				tempPrice = prices[i];
				tempMethod = methods[i];
			}
		}return ("You should get the " + tempMethod + " option at " + tempPrice + " per ride.");
	}
	public static void main(String [] args) {
		TransitCalculator a = new TransitCalculator (20, 10);
		System.out.print(a.getBestFare());
	}
}
