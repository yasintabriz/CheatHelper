package ir.silvertech.cheathelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import sdk.adenda.lockscreen.AdendaAgent;
import sdk.adenda.widget.AdendaButton;
import sdk.adenda.widget.AdendaButtonCallback;

//import com.codinguser.android.contactpicker.ContactsPickerActivity;
//import com.codinguser.android.contactpicker.OnContactSelectedListener;

public class MainActivity extends AppCompatActivity {

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


            }
        });
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


}
