package com.winwin.andoid.app;

import android.R.drawable;
import android.app.TabActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
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
