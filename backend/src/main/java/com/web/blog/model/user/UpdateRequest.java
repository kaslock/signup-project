package com.web.blog.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Valid
@ToString
public class UpdateRequest {
	@ApiModelProperty(required = true)
	@NotNull
    String email;
	@NotNull
	@ApiModelProperty(required = true)
	String newPassword;
	
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
}
