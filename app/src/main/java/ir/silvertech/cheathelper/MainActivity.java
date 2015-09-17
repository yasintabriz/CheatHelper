package ir.silvertech.cheathelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codinguser.android.contactpicker.ContactsPickerActivity;

import sdk.adenda.lockscreen.AdendaAgent;
import sdk.adenda.widget.AdendaButton;
import sdk.adenda.widget.AdendaButtonCallback;

public class MainActivity extends AppCompatActivity {
    private static final int GET_PHONE_NUMBER = 3007;
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdendaButton button = (AdendaButton) findViewById(R.id.lock_in_button);
        button.setAdendaCallback(new AdendaButtonCallback() {
            @Override
            public void onPreOptIn() {
            }

            @Override
            public void onPreOptOut() {
            }

            @Override
            public void onPostOptIn() {

                //            FragmentManager fm= getSupportFragmentManager();
                //              Fragment frag=new LockscreenFragment();
//                fm.beginTransaction().replace(R.layout.lockscreen_fragment,frag).commit();

                AdendaAgent.setEnableForegroundService(getApplicationContext(), false);
                AdendaAgent.setEnableCustomContentTracking(getApplicationContext(), false);
                AdendaAgent.setEnableAds(getApplicationContext(), false);

                AdendaAgent.addCustomFragmentContent(getApplicationContext(), null, "ir.silvertech.cheathelper.LockscreenFragment", null, null, true);
                AdendaAgent.setUnlockType(getApplicationContext(), AdendaAgent.ADENDA_UNLOCK_TYPE_NONE);

            }

            @Override
            public void onPostOptOut() {
                // AdendaAgent.setEnableForegroundService(getApplicationContext(),false);
            }

            @Override
            public String getUserId() {
                return "123456";
            }

            @Override
            public String getUserGender() {
                return "m";
            }

            @Override
            public String getUserDob() {
                return "19940113";
            }

            @Override
            public float getUserLatitude() {
                return 0;
            }

            @Override
            public float getUserLongitude() {
                return 0;
            }
        });
        Button cb = (Button) findViewById(R.id.ContactButton);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContact();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText et = (EditText) findViewById(R.id.editText);
        if (!sp.getString("Number", "").isEmpty()) {
            et.setText(sp.getString("Number", ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getContact() {
        startActivityForResult(new Intent(this, ContactsPickerActivity.class), GET_PHONE_NUMBER);
    }

    // Listen for results.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // See which child activity is calling us back.
        switch (requestCode) {
            case GET_PHONE_NUMBER:
                // This is the standard resultCode that is sent back if the
                // activity crashed or didn't doesn't supply an explicit result.
                if (resultCode == RESULT_CANCELED) {

                } else {
                    String phoneNumber = (String) data.getExtras().get(ContactsPickerActivity.KEY_PHONE_NUMBER);
                    //Do what you wish to do with phoneNumber e.g.

                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("Number", phoneNumber);
                }
            default:
                break;
        }
    }

}
