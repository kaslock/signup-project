package com.web.blog.controller.account;

import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.AuthenticationRequest;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:8081" })
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@Valid @RequestBody AuthenticationRequest request) {

    	String email = request.getEmail();
    	String password = request.getPassword();
    	
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

        if (userOpt.isPresent()) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")

    public Object signup(@Valid @RequestBody SignupRequest request) {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.
    	
    	String email = request.getEmail();
    	String password = request.getPassword();
    	String uid = request.getNickname();
    	LocalDateTime createDate = LocalDateTime.now();
    	
    	User user = new User();
    	
    	user.setUid(uid);
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setCreateDate(createDate);
    	
    	
    	final BasicResponse result = new BasicResponse();
        
    	try {
	    	userDao.getUserByUid(uid)
		    	.ifPresent(m -> {		
		    		throw new IllegalStateException("이미 존재하는 회원입니다.");		    		
		    	});

	    	userDao.getUserByEmail(email)
		    	.ifPresent(m -> {		
		    		throw new IllegalStateException("이미 존재하는 이메일입니다.");
		    	});
	    	
	    	userDao.save(user);
	    	result.status = true;
	        result.data = "success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
	        
    	} catch(IllegalStateException e) {
    		System.out.println("aaaaa: " + e);
    		result.status = true;
	        result.data = e.getMessage();
	        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    	}
    }

}