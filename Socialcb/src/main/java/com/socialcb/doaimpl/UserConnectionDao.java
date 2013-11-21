/**
 * 
 */
package com.socialcb.doaimpl;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.socialcb.dao.IGlobalDao;
import com.socialcb.model.UserConnection;

/**
 * @author sagarpatil
 * 
 */
@Repository
public class UserConnectionDao implements IGlobalDao {

	@Inject
	private final JdbcTemplate template;
	
	public UserConnectionDao(JdbcTemplate template) {
		this.template = template;
	}


	public UserConnection getUserConnection(String userId) {
		String sql = "select * from userConnection where userId = ?";
		UserConnection userConnection = (UserConnection)template.queryForObject(
				sql, new Object[] { userId }, 
				new BeanPropertyRowMapper<UserConnection>(UserConnection.class));
		
		return userConnection;
	}

}
