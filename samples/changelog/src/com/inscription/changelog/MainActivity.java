package com.inscription.changelog;

import com.inscription.ChangeLogDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onShowChangeLogClick(final View v) {
		//Launch change log dialog
		final ChangeLogDialog changeLogDialog = new ChangeLogDialog(this);
		changeLogDialog.show();
	}

	public void onCustomStyleClick(final View v) {
		//Launch change log dialog
		final ChangeLogDialog changeLogDialog = new ChangeLogDialog(this);
		changeLogDialog.setStyle("h1 { margin-left: 10px; font-size: 12pt; color: #006b9a; margin-bottom: 0px;}"
                + "li { margin-left: 0px; font-size: 12pt; padding-top: 10px; }"
                + "ul { padding-left: 30px; margin-top: 0px; }"
                + ".summary { margin-left: 10px; font-size: 10pt; color: #006b9a; margin-top: 5px; display: block; clear: left; }"
                + ".date { margin-left: 10px; font-size: 10pt; color: #006b9a; margin-top: 5px; display: block; }");
		changeLogDialog.show();
	}	
}
