package org.nibejpi.pro.app.auto.pages.scheduling;

public interface Scheduling 
{

	public final String TITLE_TXT_VIEW = "com.myuplink.pro:id/titleTextView";
	public final String BACK_BTN = "com.myuplink.pro:id/navigateBackButton";
	
	//Scheduling screen
	public final String SCHEDULING_ICON = "//android.widget.TextView[@text='Scheduling']";
	public final String OVERRIDE_SWITCH = "com.myuplink.pro:id/overrideSwitch";
	public final String SCHEDULE = "//android.widget.TextView[@text='Schedule']";
	public final String VACATION = "//android.widget.TextView[@text='Vacation']";
	public final String MODES ="//android.widget.TextView[@text='Modes']";
	public final String SEND_TO_GRP ="//android.widget.TextView[@text='Send to group']";
	public final String STATUS_CARD = "(//android.view.ViewGroup[@resource-id=\"com.myuplink.pro:id/detailsParentLayout\"])[1]";
	//Weekly Schedule screen
	public final String ADD_BTN_SUNDAY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[3]";
	public final String MORE_BTN_SUNDAY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[2]";
	public final String COPY_SCHEDULE = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout";
	public final String DELETE_SCHEDULE = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout";
	
	
	//CREATE EVENT SCREEN
	
	//Start-Stop time screen
	public final String EVENT_TIME_PICKER = "com.myuplink.pro:id/eventTimePicker";
	public final String END_TIME_PICKER = "com.myuplink.pro:id/endTimePicker";
	public final String MINUTE_SELECTOR = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.Button[2]";
	public final String SELECTED_HOUR = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText";
	public final String SELECTED_MINUTE = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.EditText";
	public final String AM_PM_SELECTOR ="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout/android.widget.NumberPicker/android.widget.EditText"; 
	public final String CANCEL_BUTTON = "android:id/button2";
	public final String OK_BUTTON = "android:id/button1";
	public final String START_TIME = "com.myuplink.pro:id/timeTextView";
	public final String STOP_TIME = "com.myuplink.pro:id/endTimeTextView";
	public final String SAVE_BUTTON = "com.myuplink.pro:id/nextButton";
	public final String CANCEL = "com.myuplink.pro:id/cancelButton";
	public final String EVENT_SELECT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ImageView[2]";
	public final String TOOL_TIP = "com.myuplink.pro:id/balloon_text";
	
	public final String ALERT_TITLE = "com.myuplink.pro:id/alertTitle"; 
	public final String ALERT_MSG = "android:id/message";
	public final String SAVE_ALRT = "android:id/button1";
	public final String DISCARD_ALRT = "android:id/button2";
	
	//CopySchedulingScreen
	public final String COPY_BUTTON = "com.myuplink.pro:id/positiveButton";
	public final String COPY_FROM_TEXT_VIEW = "com.myuplink.pro:id/textViewCopyFrom";
	public final String COPY_TO_TEXT_VIEW ="com.myuplink.pro:id/textViewHeadingForCopyTo";
	public final String CHECKS ="com.myuplink.pro:id/imageViewCheck";
	public final String COPIED_EVENT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ImageView[2]";

	//DeleteScheduleScreen 
	
	public final String DELETE_BUTTON = "android:id/button1";
	public final String DELETED_EVENT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ImageView";
	
	
	
	 
	
	
	
	

}
