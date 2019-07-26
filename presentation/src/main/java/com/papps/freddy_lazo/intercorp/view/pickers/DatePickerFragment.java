package com.papps.freddy_lazo.intercorp.view.pickers;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.papps.freddy_lazo.intercorp.view.fragment.BaseFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private BaseFragment fragment;
    private Context context;

    public static DatePickerFragment newInstance(BaseFragment fragment) {
        DatePickerFragment f = new DatePickerFragment();
        f.fragment = fragment;
        f.context = fragment.getContext();
        return f;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // Use the current date as the default date in the picker
        return new DatePickerDialog(context, (DatePickerDialog.OnDateSetListener) fragment, mYear, mMonth, mDay);
    }


}
