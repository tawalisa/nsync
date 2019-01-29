package nsync.lijia.com;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber implements IPrimeNumber{
	public List<Integer> calPrimeNumber(int number) {
		List<Integer> retu = new ArrayList<Integer>();
		for(int n=2; n<= number;n++) {
			int r = checkIsPrima(n);
			if(r>0) {
				retu.add(r);
			}
		}
		return retu;
	}

	private int checkIsPrima(int n) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 2;i<n;i++) {
			if(n%i==0) {
				return 0;
			}
		}
		return n;
	}
}
