package ir.silvertech.cheathelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sdk.adenda.lockscreen.fragments.AdendaFragmentInterface;

/**
 * Created by yasin on 9/13/2015.
 */
public class LockscreenFragment extends android.support.v4.app.Fragment implements AdendaFragmentInterface {
    TextView number;
    View view;
    SmsManager sms;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b0;
    Button basterisk;
    Button bok;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lockscreen_fragment, container, false);
        InitializeButtons();
        return view;


    }


    @Override
    public boolean expandOnRotation() {
        return false;
    }

    @Override
    public Intent getActionIntent() {
        return null;
    }

    @Override
    public boolean coverEntireScreen() {
        return true;
    }

    @Override
    public Pair<Integer, Integer> getGlowpadResources() {
        return null;
    }

    @Override
    public boolean getStartHelperForResult() {
        return false;
    }

    @Override
    public void onActionFollowedAndLockScreenDismissed() {

    }


    public void InitializeButtons() {
        b1 = (Button) view.findViewById(R.id.button1);
        b2 = (Button) view.findViewById(R.id.button2);
        b3 = (Button) view.findViewById(R.id.button3);
        b4 = (Button) view.findViewById(R.id.button4);
        b5 = (Button) view.findViewById(R.id.button5);
        b6 = (Button) view.findViewById(R.id.button6);
        b7 = (Button) view.findViewById(R.id.button7);
        b8 = (Button) view.findViewById(R.id.button8);
        b9 = (Button) view.findViewById(R.id.button9);
        b0 = (Button) view.findViewById(R.id.button0);
        basterisk = (Button) view.findViewById(R.id.buttonasterisk);
        bok = (Button) view.findViewById(R.id.buttonok);
        number = (TextView) view.findViewById(R.id.textView);
        //number.setMovementMethod(new ScrollingMovementMethod());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('1'));

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('2'));

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('3'));

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('4'));

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('5'));

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('6'));

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('7'));

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('8'));

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('9'));

            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.setText(new StringBuilder(number.getText()).append('0'));

            }
        });
        basterisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number.setText(new StringBuilder(number.getText()).append('*'));
            }
        });
        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//sendSMS("09148615141", number.getText().toString());
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

                Toast.makeText(getActivity(), sp.getString("Number", null), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sendSMS(String phoneNumber, String message) {
        sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        sms = null;
        number.setText(null);

    }


}