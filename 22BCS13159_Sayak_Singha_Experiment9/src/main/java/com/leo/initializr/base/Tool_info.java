package com.leo.initializr.base;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record Tool_info(
		@NotNull
		long _id,
		@NotEmpty
		String _name,
		@NotEmpty
		String _author,
		String _authTag,
		@Positive
		@DecimalMax("5300")
		int _size,
		Boolean _valid,
		LocalDate _dateAdded
		) {
	public Tool_info {
		_authTag = _author+'.'+_name+'.'+_id;
	}
}
