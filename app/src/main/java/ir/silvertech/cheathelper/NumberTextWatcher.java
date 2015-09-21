package ir.silvertech.cheathelper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NumberTextWatcher implements TextWatcher {

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";
    final private EditText et;

    public NumberTextWatcher(Context context, EditText et) {
        this.et = et;
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