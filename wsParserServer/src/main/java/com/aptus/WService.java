// WebService class

package com.aptus;

import javax.jws.WebService;

@WebService(endpointInterface = "com.aptus.IWService", serviceName = "WService")
public class WService implements IWService {

	public String sayHi(String text) {
		System.out.println("SayHI works!");
		return "Hello " + text;
	}

}