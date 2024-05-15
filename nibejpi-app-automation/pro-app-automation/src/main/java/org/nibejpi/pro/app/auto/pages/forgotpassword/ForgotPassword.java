package org.nibejpi.pro.app.auto.pages.forgotpassword;

public interface ForgotPassword 
{
	public final String INPUT_EMAIL = "//android.widget.EditText[@text='E-mail']" ;
	public final String FORGOT_PWD_LINK = "com.myuplink.pro:id/forgotPasswordText";
	public final String RESET_BTN = "com.myuplink.pro:id/btnReset";
	
	//Assertions
	public final String EMPTY_FIELD_ERROR_MSG = "com.myuplink.pro:id/textinput_error";
	public final String INVALID_EMAILADD = "//android.widget.TextView[@text='Invalid email address.']";
}
