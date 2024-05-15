package org.nibejpi.pro.app.auto.pages.spregister;

public interface SPRegister 
{
	//Locators for Service Partner Registration screen
	public final String SP_NAME = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText";
	public final String ADDRESS_LINE1 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText";
	public final String ADDRESS_LINE2 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText";
	public final String POSTAL_CODE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[4]/android.widget.FrameLayout/android.widget.EditText";
	public final String CITY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.EditText";
	public final String STATE = "com.myuplink.pro:id/regionSpinner";
	public final String STATE_OPT = "//android.widget.EditText[@text=\"State/Province/Region (Optional)\"]";
	public final String COUNTRY = "com.myuplink.pro:id/countrySpinner";
	public final String SELECTED_COUNTRY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.Spinner/android.widget.TextView";
	public final String EMAIL = "//android.widget.EditText[@text='E-mail']";
	public final String NAME = "//android.widget.EditText[@text='Name']";
	public final String PHONENUMBER = "//android.widget.EditText[@text='Phone number']";
	public final String BRAND = "com.myuplink.pro:id/brandButton";
	public final String BRANDINITIAL = "//android.widget.Button[@index='2']";
	public final String NEXT_BTN="com.myuplink.pro:id/next";
	public final String BRANDNAME1="android:id/numberpicker_input";
	public final String BRANDNAME2="//android.widget.Button[@text='%s' and @index='2']";
	public final String VAT ="//android.widget.EditText[@text='VAT']";
	public final String TOS = "com.myuplink.pro:id/checkBox";
	public final String REGISTER_BTN ="com.myuplink.pro:id/registerButton";
	public final String NUMBERPKR_INPUT = "android:id/numberpicker_input";
	public final String TEXT_BRAND = "com.myuplink.pro:id/textViewBrand";
	public final String TOS_WARNING = "com.myuplink.pro:id/tosWarning";
	
}
