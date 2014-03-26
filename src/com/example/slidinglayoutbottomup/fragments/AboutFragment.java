package com.example.slidinglayoutbottomup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.slidinglayoutbottomup.R;
import com.example.slidinglayoutbottomup.activities.SecondActivity;
import com.example.slidinglayoutbottomup.activities.SecondActivity.OnviewAnimatedListener;

public class AboutFragment extends Fragment implements OnClickListener, OnviewAnimatedListener{
	private View mView;
	private TextView mTxtHeader;
	private RelativeLayout relLayout;
	private static AboutFragment mAboutFragment;
	private static OnViewReverseAnimationListener mOnViewReverseAnimationListener;
	
	public interface OnViewReverseAnimationListener {
		void onViewReverseAnimated();
	}
	
	public static AboutFragment newInstance(OnViewReverseAnimationListener onViewReverseAnimationListener) {
		mAboutFragment = new AboutFragment();
		mOnViewReverseAnimationListener = onViewReverseAnimationListener;
		return mAboutFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_about, container, false);
		mTxtHeader = (TextView)mView.findViewById(R.id.header);
		relLayout = (RelativeLayout)mView.findViewById(R.id.relativeLayout);
		mTxtHeader.setOnClickListener(this);
		((SecondActivity)getActivity()).setListener(this);
		mOnViewReverseAnimationListener.onViewReverseAnimated();
		return mView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header:
			//performReverseAnimation();
			mOnViewReverseAnimationListener.onViewReverseAnimated();
		default:
			break;
		}
	}
	
	@Override
	public void onViewAnimated(boolean isReverseAnimation) {
		//performAnimation();
		if(isReverseAnimation) {
			relLayout.setVisibility(View.GONE);
		}else {
			relLayout.setVisibility(View.VISIBLE);
		}
	}
}
