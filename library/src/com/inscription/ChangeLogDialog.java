package com.inscription;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.inscription.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

/*
 * Class to show a change log dialog
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

public class ChangeLogDialog {
    static final private String TAG = "ChangeLogDialog"; 
    
    static final private String TITLE_CHANGELOG = "title_changelog"; 
    static final private String CHANGELOG_XML = "changelog"; 
	
    private Activity fActivity;
    private String fStyle =	"h1 { margin-left: 0px; font-size: 12pt; }" 
			+ "li { margin-left: 0px; font-size: 9pt; }" 
			+ "ul { padding-left: 30px; }"
			+ ".summary { font-size: 9pt; color: #606060; }";
 
    
	public ChangeLogDialog(Activity context) {
        fActivity = context;
    }

	protected Context GetContext() {
		return fActivity;
	}
	
	//Get the current app version
	private String GetAppVersion(){
		try {
			PackageInfo _info = fActivity.getPackageManager().getPackageInfo(fActivity.getPackageName(), 0);
			return _info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	//Parse a the release tag and return html code
	private String ParseReleaseTag(XmlResourceParser aXml) throws XmlPullParserException, IOException {
		String _Result = "<h1>Release: " + aXml.getAttributeValue(null, "version") + "</h1>";
		//Add summary if available
		if (aXml.getAttributeValue(null, "summary") != null)
			_Result +=  "<span class='summary'>" + aXml.getAttributeValue(null, "summary") + "</span>";
		
		_Result += "<ul>";
		
		//Parse child nodes
        int eventType = aXml.getEventType();
        while ((eventType != XmlPullParser.END_TAG) || (aXml.getName().equals("change"))) {
        	if ((eventType == XmlPullParser.START_TAG) &&(aXml.getName().equals("change"))){
            	eventType = aXml.next();
        		_Result = _Result + "<li>" + aXml.getText() + "</li>";
            }
        	eventType = aXml.next();
        }		
        _Result = _Result + "</ul>";
        return _Result;
	}
	
	//CSS style for the html
	private String GetStyle() {
		return "<style type=\"text/css\">" + fStyle + "</style>";
	}
	
	public void SetStyle(String aStyle) {
		fStyle = aStyle;
	}
	
	//Get the changelog in html code, this will be shown in the dialog's webview
	private String GetHTMLChangelog(int aResourceId, Resources aResource, int aVersion) {
		boolean _ReleaseFound = false;
		String _Result = "<html><head>" + GetStyle() + "</head><body>";
    	XmlResourceParser _xml = aResource.getXml(aResourceId);
    	try
    	{
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
            	if ((eventType == XmlPullParser.START_TAG) &&(_xml.getName().equals("release"))){
            		//Check if the version matches the release tag.
            		//When aVersion is 0 every release tag is parsed.
            		int _VersionCode = Integer.parseInt(_xml.getAttributeValue(null, "versioncode"));
            		if ((aVersion == 0) || (_VersionCode == aVersion)) {
            			_Result = _Result + ParseReleaseTag(_xml);
            			_ReleaseFound = true; //At lease one release tag has been parsed.
            		}      		
	            }
            	eventType = _xml.next();
            }
    	} 
    	catch (XmlPullParserException e)
    	{
    		Log.e(TAG, e.getMessage(), e);
    		return "";
    	}
    	catch (IOException e)
    	{
    		Log.e(TAG, e.getMessage(), e);
    		return "";    		
    	}        	
    	finally
    	{        	
    		_xml.close();
    	}		
		_Result = _Result + "</body></html>";
		
		//Check if there was a release tag parsed, if not return an empty string.
		if (_ReleaseFound)
			return _Result;
		else
			return "";
	}
	
	//Call to show the change log dialog
    public void show() {
    	show(0);
    }
    
	protected void show(int aVersion) {
    	//Get resources
    	String _PackageName = fActivity.getPackageName();
    	Resources _Resource;
		try {
			_Resource = fActivity.getPackageManager().getResourcesForApplication(_PackageName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return;
		} 
		
        //Get dialog title	        	
       	int _resID = _Resource.getIdentifier(TITLE_CHANGELOG , "string", _PackageName);
        String _Title = _Resource.getString(_resID);
      	_Title = _Title + " v" + GetAppVersion();
 
        //Get change log xml resource id
      	_resID = _Resource.getIdentifier(CHANGELOG_XML, "xml", _PackageName);
        //Create html change log
       	String _HTML = GetHTMLChangelog(_resID, _Resource, aVersion);
       	
        //Get button strings
        String _Close =  _Resource.getString(R.string.changelog_close);

        //Check for empty change log
        if (_HTML.equals("") == true)
        {
        	//It seems like there is nothing to show, just bail out.
        	return;
        }
        
        //Create web view and load html
        WebView _WebView = new WebView(fActivity);
        _WebView.loadData(_HTML, "text/html", "utf-8");
        AlertDialog.Builder builder = new AlertDialog.Builder(fActivity)
                .setTitle(_Title)
                .setView(_WebView)
                .setPositiveButton(_Close, new Dialog.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

}

