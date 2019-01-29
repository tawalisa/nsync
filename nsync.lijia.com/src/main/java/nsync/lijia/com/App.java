package nsync.lijia.com;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		long c = System.nanoTime();
		new PrimeNumber().calPrimeNumber(10000).forEach(System.out::println);
		System.out.println(System.nanoTime() - c);
		
		
//		c = System.nanoTime();
//		((IPrimeNumber)new NsyncFactory( new PrimeNumber()).getInstance()).calPrimeNumber(3).forEach(System.out::println);
//		System.out.println(System.nanoTime() - c);
	}
}
