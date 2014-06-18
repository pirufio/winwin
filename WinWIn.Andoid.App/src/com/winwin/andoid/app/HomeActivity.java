package com.winwin.andoid.app;

import android.app.TabActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

public class HomeActivity extends TabActivity {


	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	TabHost tabHost = getTabHost();
	 tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator( 
			 "", getResources().getDrawable(R.drawable.ic_launcher) ).setContent(R.id.tab1));
	       tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator( 
    		 "", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.tab2));
	       tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator( 
	          "", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.tab3));
	}
}
