package ir.silvertech.cheathelper;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by yasin on 9/15/2015.
 */
public class GetPhoneNumber {
    private static final String TAG = "PhoneUtils";

    public static String getContactPhoneNumber(Context context, String contactId) {
        int type = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
        String phoneNumber = null;

        String[] whereArgs = new String[]{String.valueOf(contactId), String.valueOf(type)};

        Log.d(TAG, "Got contact id: " + contactId);

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone._ID + " = ? and " + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",
                whereArgs,
                null);

        int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

        if (cursor != null) {
            Log.d(TAG, "Returned contact count: " + cursor.getCount());
            try {
                if (cursor.moveToFirst()) {
                    phoneNumber = cursor.getString(phoneNumberIndex);
                }
            } finally {
                cursor.close();
            }
        }

        Log.d(TAG, "Returning phone number: " + phoneNumber);
        return phoneNumber;
    }
}
