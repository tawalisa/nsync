package nsync.lijia.com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class NsyncFactory<T> implements InvocationHandler {

	private T obj;

	public NsyncFactory(PrimeNumber primeNumber) throws InstantiationException, IllegalAccessException {
		
		obj = (T) primeNumber;
//		obj  = (T) Proxy.newProxyInstance(primeNumber.getClass().getClassLoader(),
//                primeNumber.getClass().getInterfaces(),
//                this);
	}
	public T getInstance() {
		return obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("===");
		return method.invoke(obj, args);
	}
}
