package ir.silvertech.cheathelper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class NumberTextWatcher implements TextWatcher {

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";
    Context context;
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;
    private EditText et;

    public NumberTextWatcher(Context context, EditText et) {
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,#####");
        this.et = et;
        hasFractionalPart = false;
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);

        boolean handled = false;

        int inilen, endlen;
        inilen = et.getText().length();
        String v = s.toString().replaceAll("\\*", "");
        int cp = et.getSelectionStart();

        StringBuilder sb = new StringBuilder(v);
        int asteriskCount = v.length() / 5;
        if (asteriskCount > 0) {
            for (int a = 1; a <= asteriskCount; a++) {
                sb.insert(6 * a - 1, '*');
            }
        }
       /* int asterisks=0;
        if(v.length()%5==0) {
            sb.append('*');
         *//*   for (int a = 5; a <= v.length(); a += 5) {
                sb.insert(a+asterisks, '*');
                asterisks++;
            }*//*
        }*/
        et.setText(sb.toString());
        et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        hasFractionalPart = s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()));
    }

}