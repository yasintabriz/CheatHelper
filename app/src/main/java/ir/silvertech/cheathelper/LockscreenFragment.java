package ir.silvertech.cheathelper;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdubina.multiprocesspreferences.MultiprocessPreferences;

import sdk.adenda.lockscreen.fragments.AdendaFragmentInterface;
/**
 * Created by yasin on 9/13/2015.
 */
public class LockscreenFragment extends android.support.v4.app.Fragment implements AdendaFragmentInterface {
    private EditText number;
    private View view;
    private String phoneNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lockscreen_fragment, container, false);
        InitializeButtons();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MultiprocessPreferences.MultiprocessSharedPreferences sp;
        //sp = getActivity().getSharedPreferences("ir.silvertech.cheathelper_preferences", Context.MODE_MULTI_PROCESS);
        sp = MultiprocessPreferences.getDefaultSharedPreferences(getActivity());
        phoneNumber = sp.getString("Number", "");
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


    private void InitializeButtons() {
        Button b1 = (Button) view.findViewById(R.id.button1);
        Button b2 = (Button) view.findViewById(R.id.button2);
        Button b3 = (Button) view.findViewById(R.id.button3);
        Button b4 = (Button) view.findViewById(R.id.button4);
        Button b5 = (Button) view.findViewById(R.id.button5);
        Button b6 = (Button) view.findViewById(R.id.button6);
        Button b7 = (Button) view.findViewById(R.id.button7);
        Button b8 = (Button) view.findViewById(R.id.button8);
        Button b9 = (Button) view.findViewById(R.id.button9);
        Button b0 = (Button) view.findViewById(R.id.button0);
        Button bclear = (Button) view.findViewById(R.id.buttonclear);
        Button bok = (Button) view.findViewById(R.id.buttonok);
        number = (EditText) view.findViewById(R.id.PinText);
        //number.setScroller(new Scroller(getActivity()));
        // number.setMovementMethod(new ScrollingMovementMethod());
        number.addTextChangedListener(new NumberTextWatcher(getActivity(), number));
        //number.setMovementMethod(new ScrollingMovementMethod());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("1");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number.append("0");
            }
        });
        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = number.getText().length() - 1;
                if (index != -1 && number.getText().charAt(index) != '*') {
                    number.setText(new StringBuilder(number.getText().delete(index, index + 1)));
                } else if (index != -1 && number.getText().charAt(index) == '*') {
                    number.setText(new StringBuilder(number.getText().delete(index - 1, index + 1)));
                }
            }
        });
        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

                if (!phoneNumber.isEmpty() && !number.getText().toString().isEmpty()) {
                    try {
                        sendSMS(phoneNumber, number.getText().toString());
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    }
                    number.setText("");
                    Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private void sendSMS(String InputNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(InputNumber, null, message, null, null);
        number.setText("");

    }


}
