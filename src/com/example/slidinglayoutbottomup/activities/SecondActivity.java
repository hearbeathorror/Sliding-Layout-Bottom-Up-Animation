package com.example.slidinglayoutbottomup.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.slidinglayoutbottomup.R;
import com.example.slidinglayoutbottomup.fragments.AboutFragment;
import com.example.slidinglayoutbottomup.fragments.AboutFragment.OnViewReverseAnimationListener;

public class SecondActivity extends FragmentActivity implements OnClickListener, OnViewReverseAnimationListener{
	private AboutFragment mAboutFragment;
	private ListView mListView;
	private FrameLayout mFrameLayout;
	private TextView mTxtView;
	private Animation mAnimation;
	private OnviewAnimatedListener mOnviewAnimatedListener;
	
	public interface OnviewAnimatedListener {
		void onViewAnimated(boolean isReverseAnimation);
	}
	
	public void setListener(OnviewAnimatedListener onviewAnimatedListener) {
		mOnviewAnimatedListener = onviewAnimatedListener;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		mListView = (ListView)findViewById(android.R.id.list);
		mFrameLayout = (FrameLayout)findViewById(R.id.content_frame);
		mTxtView = (TextView)findViewById(R.id.txtAbout);
		mTxtView.setOnClickListener(this);
		setListView();
		setFragment();
	}
	
	private void setListView() {
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(SecondActivity.this,
						android.R.layout.simple_list_item_1, 
						getResources().getStringArray(R.array.navigation_items));
		mListView.setAdapter(adapter);
	}
	
	private void setFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		mAboutFragment = AboutFragment.newInstance(this);
		ft.replace(R.id.content_frame, mAboutFragment);
		ft.commit();
	}
	
	private void animateView() {
		mAnimation = AnimationUtils.loadAnimation(SecondActivity.this,
				R.anim.layout_anim);
		mAnimation.setDuration(1000);
		mAnimation.setFillAfter(true);
		mFrameLayout.startAnimation(mAnimation);
		
		mAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				mFrameLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
				mFrameLayout.setVisibility(View.VISIBLE);
				mOnviewAnimatedListener.onViewAnimated(false);
				mListView.setEnabled(false);
				mTxtView.setVisibility(View.GONE);
				mFrameLayout.setClickable(true);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
	}
	
	private void animateReverse() {
		Animation animation = AnimationUtils.loadAnimation(SecondActivity.this, 
				R.anim.layout_reverse_anim);
		animation.setDuration(1000);
		mFrameLayout.startAnimation(animation);
		
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				mOnviewAnimatedListener.onViewAnimated(true);
				mFrameLayout.setVisibility(View.GONE);
				mListView.setEnabled(true);
				mTxtView.setVisibility(View.VISIBLE);
				mFrameLayout.setClickable(false);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtAbout:
			animateView();
			break;

		default:
			break;
		}
	}

	@Override
	public void onViewReverseAnimated() {
		animateReverse();
	}
}
