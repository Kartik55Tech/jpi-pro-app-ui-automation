package org.nibejpi.pro.app.auto.pages.login;

public interface Login {

	// Elements XPath and ID constants
	public final String NEXT_BUTTON = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button";
	public final String MYUPLINK_LOGO = "com.myuplink.pro:id/logoImage";
	public final String ENVIRONMENT = "//android.widget.TextView[@text='Environment']";
	public final String DEVPICKER = "//android.widget.Button[@text='DEVELOPMENT' and @index ='1']";
	public final String STAGEPICKER = "//android.widget.Button[@text='STAGING' and @index ='2']";
	public final String SELECT_ENV = "android.widget.TextView";
	public final String CLOSE_BUTTON = "com.myuplink.pro:id/closeButton";
	public final String EMAIL = "//android.widget.EditText[@text='E-mail']";
	public final String PASSWORD = "//android.widget.EditText[@text='Password']";
	public final String LOGIN_BUTTON = "com.myuplink.pro:id/loginButton";
	public final String HAMBURGER_MENU = "//android.widget.FrameLayout[@content-desc=\"Menu\"]/android.widget.FrameLayout/android.widget.ImageView";
	public final String USER_SETTINGS = "//android.widget.TextView[@text='Settings']";
	public final String LOGOUT_BUTTON = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]/android.widget.TextView";
	public final String REGISTER_BUTTON = "com.myuplink.pro:id/registerButton";
	public final String FORGOT_PWD_LINK = "com.myuplink.pro:id/forgotPasswordText";
	public final String HELP = "com.myuplink.pro:id/helpTextView";
	public final String ABOUT = "com.myuplink.pro:id/aboutTextView";
	public final String ENV_TEXTVIEW = "com.myuplink.pro:id/envTextView";
	public final String PASS_TOGGLE_VISIBLITY_BTN = "//android.widget.ImageButton[@content-desc=\"Show password\"]";
	public final String OK_BUTTON = "android:id/button1";

	// Assertions
	public final String INVALID_EMAIL = "com.myuplink.pro:id/textinput_error";
	public final String EMPTY_EMAIL_FIELD_ERROR_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
	public final String EMPTY_PWD_FIELD_ERROR_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
	public final String REGISTRED_EMAIL_INCORRECT_PWD = "android:id/message";
	public final String NON_REGISTERED_USER_ERROR_MSG = "android:id/message";
	public final String HOME_BUTTON = "//android.widget.FrameLayout[@content-desc='Home']/android.widget.FrameLayout/android.widget.ImageView";

}
