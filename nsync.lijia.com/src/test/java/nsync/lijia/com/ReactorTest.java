package nsync.lijia.com;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {
	static class User{
		int id;
		String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public User(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
	}
	Mono<User> findById(long id){
		return Mono.just(new User(1,"test"));
	}
	 Flux<User> findAll(){
		 return Flux.fromArray(new User[] {new User(1,"test1"),new User(2,"test2"),new User(3,"test3")});
	 }
	@Test
	public void test() {
		Integer[] array = new Integer[]{1,2,3,4,5,6};
		Flux.fromArray(array);
		List<Integer> list = Arrays.asList(array);
		Flux.fromIterable(list);
		Stream<Integer> stream = list.stream();
		Flux.fromStream(stream);
	}
	
	@Test
	public void test1() {
		Flux.just();
		Flux.empty();
		Mono.empty();
		Mono.justOrEmpty(Optional.empty());
		// 只有错误信号的数据流
		Flux.error(new Exception("some error"));
		Mono.error(new Exception("some error"));
	}
	private String getStringSync() {
		 System.out.println("222222222");
	    try {
	        TimeUnit.SECONDS.sleep(2);
	        System.out.println("333333");
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    return "Hello, Reactor!";
	}
	@Test
	public void testSyncToAsync() throws InterruptedException {
//	    CountDownLatch countDownLatch = new CountDownLatch(1);
//	    Mono.fromCallable(() -> getStringSync())    // 1
//	            .subscribeOn(Schedulers.elastic())  // 2
//	            .subscribe(System.out::println, null, countDownLatch::countDown);
//	    System.out.println("11111111111");
//	    countDownLatch.await(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSyncToAsync1() throws InterruptedException {
	    CountDownLatch countDownLatch = new CountDownLatch(1);
	    Mono m = Mono.fromCallable(() -> getStringSync())    // 1
	            .subscribeOn(Schedulers.elastic());  // 2
	    
	    System.out.println("11111111111");
	    TimeUnit.SECONDS.sleep(2);
	            m.subscribe(System.out::println, null, countDownLatch::countDown);
	    
	    countDownLatch.await(10, TimeUnit.SECONDS);
	}
}
