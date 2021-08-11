package com.oguzcan.util.validator;

import com.oguzcan.ex.ValidationException;

public interface Validator {

	public void validate(String input) throws ValidationException;
}
