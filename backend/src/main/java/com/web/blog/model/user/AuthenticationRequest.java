package com.web.blog.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Valid
@ToString
public class AuthenticationRequest {
	@ApiModelProperty(required = true)
	@NotNull
    String email;
	@NotNull
	@ApiModelProperty(required = true)
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
