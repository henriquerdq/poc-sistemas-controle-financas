package br.com.scf.auth.controller.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRequest implements Serializable {

	private static final long serialVersionUID = -8387927312441830960L;

	private String username;
	private String password;

}
