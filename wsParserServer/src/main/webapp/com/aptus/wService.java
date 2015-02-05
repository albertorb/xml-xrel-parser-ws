// WebService class

package com.aptus.wsParserServer;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class wService{
	@WebMethod
	public String hello(String name){
		return "Hello " + name;
	}
}