package ir.silvertech.cheathelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.codinguser.android.contactpicker.ContactsPickerActivity;

import sdk.adenda.lockscreen.AdendaAgent;
import sdk.adenda.widget.AdendaButton;
import sdk.adenda.widget.AdendaButtonCallback;

public class MainActivity extends AppCompatActivity {
    private static final int GET_PHONE_NUMBER = 3007;
    EditText et;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    AdendaButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdendaAgent.setAdendaConfirmationText(getApplicationContext(), null);
        button = (AdendaButton) findViewById(R.id.lock_in_button);
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
//                fm.beginTransaction().replace(R.layout.lockscreen_fragment,frag).it();

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
        sp = this.getSharedPreferences("ir.silvertech.cheathelper_preferences", MODE_PRIVATE);
        spe = sp.edit();
        et = (EditText) findViewById(R.id.editText);
        if (!sp.getString("Number", "").isEmpty()) {
            et.setText(sp.getString("Number", ""));
        }
       /* et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!et.getText().toString().isEmpty()) {
                    spe.putString("Number", s.toString()).apply();
                    Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    sp.edit().putString("Number", et.getText().toString()).apply();
                    hideKeyboard(v);
                }

            }
        });
        if (button.isOptedIn()) {
            button.setEnabled(true);
        } else
        if (!sp.getString("Number", "").isEmpty()) {
            et.setText(sp.getString("Number", ""));
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                    if (sp.getString("Number", "").isEmpty() && !button.isOptedIn()) {
                        button.setEnabled(false);
                    }
                } else {
                    String phoneNumber = (String) data.getExtras().get(ContactsPickerActivity.KEY_PHONE_NUMBER);
                    //Do what you wish to do with phoneNumber e.g.

                    sp.edit().putString("Number", phoneNumber).apply();
                    et.setText(sp.getString("Number", phoneNumber));
                    button.setEnabled(true);

                }
            default:
                break;
        }
    }

}
