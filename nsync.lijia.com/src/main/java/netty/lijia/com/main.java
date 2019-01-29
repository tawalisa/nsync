package netty.lijia.com;

public class main {
	private int port;

	public main(int port) {
		this.port = port;
	}

	public void run() throws Exception {

	}

	public static void main(String[] args) throws Exception {
//        new main(5500).run();
		Bluetooth bluetooth = new Bluetooth();
		Myweb myweb = new Myweb();

		Thread th1 = new Thread(bluetooth);
		Thread th2 = new Thread(myweb);
		th1.start();
		th2.start();

	}
}
