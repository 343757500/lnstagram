package com.lhh.lnstagram.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private OTPReceiveListener otpReceiveListener;

    public void initOtpReceiver(OTPReceiveListener otpReceiveListener,String smsType){
        this.otpReceiveListener = otpReceiveListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            // intent.getExtras() 会报NullPointerException
            handlerSMS(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlerSMS(Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch (status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    // Get SMS message contents
                    String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    if (message.contains("Sasai") || message.contains("sasai")){
                        Pattern p = Pattern.compile("\\d{4}");
                        Matcher matcher = p.matcher(message);
                        if (matcher.find()) {
                            message = matcher.group(0);
                            if (otpReceiveListener != null) {
                                otpReceiveListener.onOTPReceived(message);
                            }
                        }
                    }
                    break;
                case CommonStatusCodes.TIMEOUT:
                    // Waiting for SMS timed out (5 minutes)
                    // Handle the error ...
                    break;
            }
        }
    }

    public interface OTPReceiveListener {
        void onOTPReceived(String opt);
    }

}