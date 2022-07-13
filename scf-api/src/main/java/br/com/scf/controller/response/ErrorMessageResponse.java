package br.com.scf.controller.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorMessageResponse implements Serializable {
	
	private static final long serialVersionUID = -4074361644705125836L;
	
	private int statusCode;
	private String message;
	private String description;
	private String url;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime timestamp;

}