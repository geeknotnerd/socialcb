/**
 * 
 */
package com.socialcb.connect;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;

/**
 * @author sagarpatil
 * 
 */
public class CustomConnectController extends ConnectController {

	public CustomConnectController(
			ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);

	}
	
	/**
	 * After connection is complete/ deleted redirect to home 
	 */
	
	@Override
    protected String connectedView(String providerId){
        
        return "home";
    }

}
