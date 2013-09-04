package com.inscription.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.demo.R;
import com.inscription.CreditsDialog;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onShowCreditsClick(final View v) {
		//Launch change log dialog
		final CreditsDialog changeLogDialog = new CreditsDialog(this);
		changeLogDialog.show();
	}

	public void onCustomStyleClick(final View v) {
		//Launch change log dialog
		final CreditsDialog cd = new CreditsDialog(this);
		cd.setStyle(""

				+ "body { font-size: 9pt; text-align: center; }" 
				+ "h1 { margin-top: 20px; margin-bottom: 15px; margin-left: 0px; font-size: 1.7EM; text-align: center; color: #006b9a; }" 
				+ "h2 { margin-top: 15px; margin-bottom: 5px; padding-left: 0px; margin-left: 0px; font-size: 1EM; }" 
				+ "li { margin-left: 0px; font-size: 1EM; }" 
				+ "ul { margin-top: 0px;   margin-bottom: 5px; padding-left: 0px; list-style-type: none; }"
				+ "a { color: #006b9a; }"				
				+ "</style>");
		cd.show();
	}	

	public void onCustomStyle2Click(final View v) {
		//Launch change log dialog
		final CreditsDialog cd = new CreditsDialog(this);
		cd.setStyle(""

				+ "body { font-size: 15pt; text-align: center; font-family: sans-serif-condensed;}" 
				+ "h1 { margin-top: 20px; margin-bottom: 15px; margin-left: 0px; font-size: 1.7EM; text-align: center; color: #cc0000; }" 
				+ "h2 { margin-top: 15px; margin-bottom: 5px; padding-left: 0px; margin-left: 0px; font-size: 1EM; }" 
				+ "li { margin-left: 0px; font-size: 1EM; }" 
				+ "ul { margin-top: 0px;   margin-bottom: 5px; padding-left: 0px; list-style-type: circle; }"
				+ "a { color: #006b9a; }"
				+ "</style>");

		cd.setCustomIcon(android.R.drawable.ic_menu_info_details);
		
		cd.setCustomTitle(R.string.app_name);

		cd.show();
	}	

}
