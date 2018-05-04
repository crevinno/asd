package com.sksoft.tipcalculatorsurvey;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    public final String EMAIL_ADDRESS = "";
    public static final String PHONE_NUMBER = "7708665727";
    Button Submit,back;
    CheckBox c_1,c_2,c_3,c_4,c_5,c_6;
    String S_1="",S_2="",S_3="",S_4="",S_5="",S_6="";
    String uncheckedString="";
    Logger mLogger = new Logger(this);
    ReadWriteManagerInPrefference readWriteManagerInPrefference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        readWriteManagerInPrefference = new ReadWriteManagerInPrefference(this);
        Submit=(Button)findViewById(R.id.button2);
        back=(Button)findViewById(R.id.buttonExit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        c_1=(CheckBox)findViewById(R.id.checkBox1);
        c_2=(CheckBox)findViewById(R.id.checkBox2);
        c_3=(CheckBox)findViewById(R.id.checkBox3);
        c_4=(CheckBox)findViewById(R.id.checkBox4);
        c_5=(CheckBox)findViewById(R.id.checkBox5);
        c_6=(CheckBox)findViewById(R.id.checkBox6);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new UpdateTask().execute();


                uncheckedString = "";
                S_1="";
                S_2="";
                S_3="";
                S_4="";
                S_5="";
                S_6="";
                if(c_1.isChecked()){
                    S_1=" ,"+c_1.getText().toString();
                } else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_1.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_1.getText().toString();
                }
                if(c_2.isChecked()){
                    S_2=" ,"+c_2.getText().toString();
                } else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_2.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_2.getText().toString();
                }
                if(c_3.isChecked()){
                    S_3=" ,"+c_3.getText().toString();
                } else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_3.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_3.getText().toString();
                }
                if(c_4.isChecked()){
                    S_4=" ,"+c_4.getText().toString();
                }
                else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_4.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_4.getText().toString();
                }
                if(c_5.isChecked()){
                    S_5=" ,"+c_5.getText().toString();
                }
                else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_5.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_5.getText().toString();
                }
                if(c_6.isChecked()){
                    S_6=" ,"+c_6.getText().toString();
                }
                else{
                    if(uncheckedString.equals("")){
                        uncheckedString = c_6.getText().toString();
                    }
                    else uncheckedString = uncheckedString+", "+ c_6.getText().toString();
                }
                //if(!uncheckedString.equals(""))
                readWriteManagerInPrefference.writeStringIntoPrefference(MainActivity.net_id + "\n" + S_1 + S_2 + S_3 + S_4 + S_5 + S_6, TipCalculator.UNCHECK_STRING);
                Toast.makeText(MyProfile.this, "Submitted successfully.",Toast.LENGTH_LONG).show();
                try {
                    //sendEmail("Survey",MainActivity.net_id+S_1+S_2+S_3+S_4+S_5+S_6,"Send email via...");
                    //sendSMS(PHONE_NUMBER, MainActivity.net_id+S_1+S_2+S_3+S_4+S_5+S_6);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplication(),
                            "There are no email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });
    }


    public void sendEmail(String subject,String body, String chooserTitle){
       /* ShareCompat.IntentBuilder.from(this)
        .setType("message/rfc822")
        .addEmailTo(EMAIL_ADDRESS)
        .setSubject(subject)
        .setText(body)
        //.setHtmlText(body); //If you are using HTML in your body text
        .setChooserTitle(chooserTitle)
        .startChooser();*/

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
//Sends an SMS message to another device

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}
