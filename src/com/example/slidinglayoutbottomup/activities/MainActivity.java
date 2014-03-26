package com.example.slidinglayoutbottomup.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.slidinglayoutbottomup.R;
import com.example.slidinglayoutbottomup.fragments.AboutFragment;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends FragmentActivity {
	private AboutFragment mAboutFragment;
	private ListView mListView;
	private DisplayMetrics mMetrics;
	private int height;
	private FrameLayout mFrameLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (ListView)findViewById(android.R.id.list);
		mFrameLayout = (FrameLayout)findViewById(R.id.content_frame);
		setListView();
		setFragment();
		setHeight();
		setPosition();
	}
	
	private void setPosition() {
		int value = getHeight() - 120;
		Log.e("dhara","getHeight : " + getHeight());
		Log.e("dhara","value : " + value);
		ViewHelper.setY(mFrameLayout,value);
	}
	
	public void setPositionTop() {
		Log.e("dhara","getHeight : " + getHeight());
		ViewHelper.setY(mFrameLayout,-10);
	}
	
	private void setHeight() {
		mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		height = mMetrics.heightPixels;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void setListView() {
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_list_item_1, 
						getResources().getStringArray(R.array.navigation_items));
		mListView.setAdapter(adapter);
	}
	
	private void setFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		mAboutFragment = new AboutFragment();
		ft.replace(R.id.content_frame, mAboutFragment);
		ft.commit();
	}
}
