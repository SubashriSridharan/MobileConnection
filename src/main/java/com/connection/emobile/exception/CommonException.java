package com.connection.emobile.exception;

import java.io.Serializable;

public class CommonException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public CommonException(String message) {
		super(message);

	}
}