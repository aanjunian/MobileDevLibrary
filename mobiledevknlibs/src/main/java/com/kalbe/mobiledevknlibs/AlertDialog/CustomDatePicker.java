package com.kalbe.mobiledevknlibs.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.kalbe.mobiledevknlibs.Helper.clsMainExlActivity;
import com.kalbe.mobiledevknlibs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dewi Oktaviani on 1/8/2018.
 */

public class CustomDatePicker {
    private static Date dt_hidden;
    private static Calendar calendar;
    private static int year,month, day;
    public static void showHint(EditText editText, int format){
        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        editText.setHint(formatSimpleDate(calendar.getTime(), format));
    }
    public static void showDatePicker(Context context, final EditText editText, String title, final int format) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.custom_popup_date_picker, null);
        final DatePicker dp = (DatePicker) promptView.findViewById(R.id.dp_tgl);

        Bundle args = new Bundle();
        if ( editText.getText().toString().equals("")) {
            args.putInt("year", 1918);
            args.putInt("month", 0);
            args.putInt("day", 1);
            dp.init(1918, 0, 1, null);
        } else {
            if (dt_hidden != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt_hidden);
                dp.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            }
        }

        dp.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);

        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       String date = formatDate(dp, format);
                        editText.setText(date);
                        editText.setHint("");

                        int day = dp.getDayOfMonth();
                        int month = dp.getMonth();
                        int year = dp.getYear();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                        dt_hidden = calendar.getTime();
                    }
                })
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final android.support.v7.app.AlertDialog alertD = alertDialogBuilder.create();
        alertD.setTitle(title);
        alertD.show();

    }

    public static String formatDate(DatePicker dp, int format){
        String date = "";
        String month2 = clsMainExlActivity.months[dp.getMonth() + 1];
        switch (format){
            case 0:
                date = String.valueOf(dp.getDayOfMonth()) + " - " + month2 + " - " + String.valueOf(dp.getYear());
                break;
            case 1:
                date = String.valueOf(dp.getDayOfMonth()) + " " + month2 + " " + String.valueOf(dp.getYear());
                break;
            case 2:
                date = String.valueOf(dp.getYear()) + "/" + String.valueOf(dp.getMonth() + 1) + "/"+ String.valueOf(dp.getDayOfMonth())  ;
                break;
            case 3:
                date = String.valueOf(dp.getYear()) + "-" + String.valueOf(dp.getMonth() + 1) + "-"+ String.valueOf(dp.getDayOfMonth())  ;
                break;
            default:
                date = String.valueOf(dp.getYear()) + "-" + String.valueOf(dp.getMonth() + 1) + "-"+ String.valueOf(dp.getDayOfMonth())  ;
        }
        return date;
    }

    public static String formatSimpleDate(Date dates, int format){
        String date = "";
        SimpleDateFormat simpleDateFormat;
        switch (format){
            case 0:
                simpleDateFormat = new SimpleDateFormat("dd - MMM - yyyy");
                break;
            case 1:
                simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                break;
            case 2:
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 3:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            default:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        date = simpleDateFormat.format(dates);
        return date;
    }

}
