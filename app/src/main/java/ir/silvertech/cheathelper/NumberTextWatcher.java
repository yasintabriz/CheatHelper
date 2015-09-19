package ir.silvertech.cheathelper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class NumberTextWatcher implements TextWatcher {

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";
    final Context context;
    final private DecimalFormat dfnd;
    final private EditText et;
    private boolean hasFractionalPart;

    public NumberTextWatcher(Context context, EditText et) {
        dfnd = new DecimalFormat("#,#####");
        this.et = et;
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);
        String v = s.toString().replaceAll("\\*", "");
        StringBuilder sb = new StringBuilder(v);
        int asteriskCount = v.length() / 5;
        if (asteriskCount > 0) {
            for (int a = 1; a <= asteriskCount; a++) {
                sb.insert(6 * a - 1, '*');
            }
        }
        et.setText(sb.toString());
        et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}