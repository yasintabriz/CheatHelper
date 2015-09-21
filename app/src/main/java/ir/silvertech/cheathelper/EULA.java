package ir.silvertech.cheathelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;

import com.gdubina.multiprocesspreferences.MultiprocessPreferences;

/**
 * Created by yasin on 9/20/2015.
 */
class EULA {

    private final Activity mActivity;

    public EULA(Activity context) {
        mActivity = context;
    }

    private PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            pi = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    public void show() {
        PackageInfo versionInfo = getPackageInfo();

        // the eulaKey changes every time you increment the version number in the AndroidManifest.xml
        String EULA_PREFIX = "eula_";
        final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
        final MultiprocessPreferences.MultiprocessSharedPreferences prefs = MultiprocessPreferences.getDefaultSharedPreferences(mActivity);
        boolean hasBeenShown = prefs.getBoolean(eulaKey, false);
        if (!hasBeenShown) {

            // Show the Eula
            String title = mActivity.getString(R.string.app_name) + " v" + versionInfo.versionName;

            //Includes the updates as well so users know what changed.
            String message = mActivity.getString(R.string.eula);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(R.string.agree, new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Mark this version as read.
                            MultiprocessPreferences.Editor editor = prefs.edit();
                            editor.putBoolean(eulaKey, true);
                            editor.apply();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton(R.string.disagree, new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Close the activity as they have declined the EULA
                            mActivity.finish();
                        }

                    });
            builder.create().show();
        }
    }

}
