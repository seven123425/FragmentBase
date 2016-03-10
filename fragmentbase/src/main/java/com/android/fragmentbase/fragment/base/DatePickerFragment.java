package com.android.fragmentbase.fragment.base;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView textView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void show(FragmentManager manager, String tag, TextView view) {
        super.show(manager, tag);
        this.textView = view;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        textView.setText("");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
        //textView.setText("Date changed...");
        //textView.setText(textView.getText() + "\nYear: " + year);
        //textView.setText(textView.getText() + "\nMonth: " + month);
       // textView.setText(textView.getText() + "\nDay of Month: " + day);
        try {

            String dateString = String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Date date = sdf.parse(dateString);

            sdf = new SimpleDateFormat("yyyy-mm-dd");
            String formateDate = sdf.format(date);
            textView.setText(formateDate);

        } catch (Exception e) {
            Log.e("Exception",e.toString());
        }

        //String stringOfDate = year + "/" + month + "/" + day;
        //textView.setText(stringOfDate);
        //textView.setText(textView.getText() + "\n\nFormatted date: " + stringOfDate);
    }
}