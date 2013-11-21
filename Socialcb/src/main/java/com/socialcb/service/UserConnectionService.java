/**
 * 
 */
package com.socialcb.service;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.socialcb.doaimpl.UserConnectionDao;
import com.socialcb.model.UserConnection;

/**
 * @author sagarpatil
 *
 */
@Component
public class UserConnectionService {
	
	private final UserConnectionDao userConnectionDao;
	
	@Inject
	public  UserConnectionService(UserConnectionDao userConnectionDao ){
		this.userConnectionDao = userConnectionDao;
	}
	
	
	public UserConnection getUserConnection(){
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		return userConnectionDao.getUserConnection(userId);
	}
	
	
}
