package org.nibejpi.pro.app.auto.pages.register;

public interface Register {
	
	public final String ERROR_MSG ="com.myuplink.pro:id/textinput_error";
	public final String PASSWORD_REQ = "com.myuplink.pro:id/regexTextView";
	
	// Registration Screen one

	public final String TITLE_TXT_SCR_ONE = "//android.widget.TextView[@text='Create your account']";
    public final String EMAIL = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"; 
    public final String PASSWORD = "com.myuplink.pro:id/et_confirmation";
    public final String CONFIRM_PWD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText";
    public final String TOS_RAD_BTN = "com.myuplink.pro:id/checkBox";
    public final String PP_RAD_BTN = "com.myuplink.pro:id/checkBox2";
    public final String TOS_TXT_LBL = "com.myuplink.pro:id/termsTextView";
    public final String PP_TXT_LBL = "com.myuplink.pro:id/privacyTextView";
    public final String NEXT_BTN ="com.myuplink.pro:id/nextButton";
    public final String BACK_BTN = "com.myuplink.pro:id/navigateBackButton";
    public final String EMAIL_EMPTYFIELD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String PASSWORD_EMPTYFIELD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String CONFIRM_PWD_EMPTYFIELD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String TOS_WARNING_MSG = "com.myuplink.pro:id/tosWarning";
    public final String PP_WARNING_MSG = "com.myuplink.pro:id/ppWarning";
    public final String ALREADY_REG_EMAIL_WARNING = "//android.widget.TextView[@text='An account for the specified email address already exists']";
    
    // Registration Screen two

    public final String TITLE_TXT_SCR_TWO = "//android.widget.TextView[contains(@text, 'Tell us some more about you')]";
    public final String FULL_NAME = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText";
    public final String ADDRESS_LINE1 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText";
    public final String ADDRESS_LINE2 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText";
    public final String ZIP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.FrameLayout/android.widget.EditText";    
    public final String CITY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.EditText";
    public final String STATE_DDL = "com.myuplink.pro:id/regionSpinner";
    public final String COUNTRY_DDL = "com.myuplink.pro:id/countrySpinner";
    public final String REGISTER_BTN = "com.myuplink.pro:id/finishButton";
    public final String FULL_NAME_EMPTY_FLD_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String ADDRESS_LINE1_EMPTY_FLD_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String ZIP_EMPTY_FLD_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String CITY_EMPTY_FLD_MSG = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView";
    public final String STATE_EMPTY_FLD_MSG = "com.myuplink.pro:id/regionErrorTextView";
    public final String COUNTRY_EMPTY_FLD_MSG = "com.myuplink.pro:id/countryErrorTextView";
     
    // Registration screen three
    public final String TITLE_TXT_SCR_THREE = "//android.widget.TextView[@text='Verify your email']";
    public final String RESEND_BTN = "com.myuplink.pro:id/resendButton";
    public final String CONTINUE_BTN = "com.myuplink.pro:id/continueButton";
    public final String BACK_BTN3 = "com.myuplink.pro:id/navigateBackButton"; 
	
	//Toast message
    public final String TOAST_MSG = "(//android.widget.Toast)[1]";
	
}
