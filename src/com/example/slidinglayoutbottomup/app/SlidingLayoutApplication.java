package com.example.slidinglayoutbottomup.app;

import android.app.Application;

public class SlidingLayoutApplication extends Application{
	private static SlidingLayoutApplication mApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
	}
	
	public static SlidingLayoutApplication getAppCxt() {
		return mApplication;
	}
}
