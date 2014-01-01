package com.inscription.changelog;

import com.inscription.ChangeLogDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class CustomChangelogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_changelog);
		
		final ChangeLogDialog changeLog = new ChangeLogDialog(this);		
		final WebView webView = (WebView) findViewById(R.id.webView1);
		webView.loadDataWithBaseURL(null, changeLog.getHTML(), "text/html", "utf-8", null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_custom_changelog, menu);
		return true;
	}

}
