package org.nibejpi.app.constant;

public interface ErrorMessage {
    public static final String PASSWORD_RULE = "Password should contain: \r\n"
    											+ "	 between 8 and 128 characters  \r\n"
									    		+ "	 a lowercase letter \r\n"
									    		+ "	 an uppercase letter \r\n"
									    		+ "	 a digit \r\n"
									    		+ "";
    public static final String INVALID_EMAIL = "Invalid email address.";
    public static final String EMPTY_FIELD = "Field can't be empty.";
    public static final String INCORRECT_AUTHORIZATION = "Incorrect email or password.";
    public static final String CHECK_EMAIL = "Please check your email.";
    public static final String PASSWORD_MISMATCH = "Passwords do not match.";
    public static final String ALREADY_REG_EMAIL="An account for the specified email address already exists";
}
