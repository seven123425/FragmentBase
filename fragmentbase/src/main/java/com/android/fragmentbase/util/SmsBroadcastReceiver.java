package com.android.fragmentbase.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    public Activity activity;
    public UpdateSmsEvent updateSmsEvent;


    public interface UpdateSmsEvent{
        void updateSms(String sms);
    }

    public SmsBroadcastReceiver(UpdateSmsEvent inte){
        updateSmsEvent = inte;
    }


    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            String smsMessageBody= "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                //smsMessage.getTimestampMillis() is current time
                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
                smsMessageBody = smsMessage.getMessageBody().toString();
            }
            Toast.makeText(context, smsMessageBody, Toast.LENGTH_SHORT).show();
            smsMessageBody = smsMessageBody.replaceAll("\\D+","");
            //this will update the UI with message
            updateSmsEvent.updateSms(smsMessageBody);
            activity.unregisterReceiver(this);
        }
    }

    public void registerSmsReceiver(Activity activity){
        this.activity = activity;
        IntentFilter filter = new IntentFilter();
        filter.setPriority(999);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        activity.registerReceiver(this, filter);
    }
}