/**
 * 
 */
package com.aptus;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author albrinbor
 *
 */
@WebService
public interface IWService {

	String sayHi(@WebParam(name = "text") String text);

}
