package com.zihao.fragment;

import com.zihao.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 站内统计
 * 
 * @author zihao
 * 
 */
public class SiteStatisticsFragment extends Fragment {

	private View mView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_stie, null);
		return mView;
	}

}