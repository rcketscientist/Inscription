package com.inscription;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

/*
 * Class to show a dialog with the latest changes for the current app version.
 * (c) 2012 Martin van Zuilekom (http://martin.cubeactive.com)
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

public class WhatsNewDialog extends ChangeLogDialog {
    static final private String WHATS_NEW_LAST_SHOWN = "whats_new_last_shown";
    
	public WhatsNewDialog(Activity context) {
		super(context);
	}

	//Get the current app version
	private int GetAppVersionCode(){
		try {
			PackageInfo _info = GetContext().getPackageManager().getPackageInfo(GetContext().getPackageName(), 0);
			return _info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void forceShow() {
		//Show only the changes from this version (if available)
		super.show(GetAppVersionCode());		
	}
	
	@Override
	public void show() {
		//ToDo check if version is shown
    	final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(GetContext());
    	int _VersionShown = prefs.getInt(WHATS_NEW_LAST_SHOWN, 0);	    	
    	if(_VersionShown != GetAppVersionCode()){		
    		//This version is new, show only the changes from this version (if available)
    		super.show(GetAppVersionCode());
    		
    		//Update last shown version
            SharedPreferences.Editor _Editor = prefs.edit();
            _Editor.putInt(WHATS_NEW_LAST_SHOWN, GetAppVersionCode());
            _Editor.commit();    		
    	}
	}
}
