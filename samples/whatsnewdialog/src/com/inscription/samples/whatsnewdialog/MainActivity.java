package com.inscription.samples.whatsnewdialog;

import com.inscription.WhatsNewDialog;

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
    protected void onResume() {
        super.onResume();
        //Launch what's new dialog (will only be shown once)
        final WhatsNewDialog whatsNewDialog = new WhatsNewDialog(this);
        whatsNewDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onShowChangeLogClick(final View v) {
        //Launch what's new dialog
        final WhatsNewDialog whatsNewDialog = new WhatsNewDialog(this);
        whatsNewDialog.forceShow();
    }

    public void onCustomStyleClick(final View v) {
        //Launch what's new dialog
        final WhatsNewDialog whatsNewDialog = new WhatsNewDialog(this);
        whatsNewDialog.setStyle("h1 { margin-left: 10px; font-size: 12pt; color: #006b9a; margin-bottom: 0px;}"
                + "li { margin-left: 0px; font-size: 12pt; padding-top: 10px; }"
                + "ul { padding-left: 30px; margin-top: 0px; }");
        whatsNewDialog.forceShow();
    }
}
