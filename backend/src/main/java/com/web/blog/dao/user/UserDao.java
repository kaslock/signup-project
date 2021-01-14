
package com.web.blog.dao.user;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
    
	Optional<User> getUserByEmail(String email);
	Optional<User> getUserByUid(String uid);
	
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
