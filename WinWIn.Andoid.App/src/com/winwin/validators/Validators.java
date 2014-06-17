package com.winwin.validators;

import java.util.regex.Pattern;

public class Validators {
	
	public static boolean validEmail(String email) {
	    Pattern pattern = EMAIL_ADDRESS;
	    return pattern.matcher(email).matches();
	}
	
	public static final Pattern EMAIL_ADDRESS
    = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
    );
}
