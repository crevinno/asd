package com.sksoft.tipcalculatorsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculator extends AppCompatActivity {
    EditText Food_total;
    RadioButton radio_one,radio_two,radio_three;
    Button calculate,exit;
    TextView tax,tip,total_price;
    float text_Tax,text_tip;
    Logger mLogger = new Logger(this);
    ReadWriteManagerInPrefference readWriteManagerInPrefference;
    public static final String  UNCHECK_STRING = "uncheckedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        Food_total=(EditText)findViewById(R.id.editText);
        radio_one=(RadioButton)findViewById(R.id.checkBox10);
        radio_two=(RadioButton)findViewById(R.id.checkBox15);
        radio_three=(RadioButton)findViewById(R.id.checkBox20);
        calculate=(Button)findViewById(R.id.button2);
        exit=(Button)findViewById(R.id.buttonExit);
        tax=(TextView)findViewById(R.id.editText3);
        tip=(TextView)findViewById(R.id.editText4);
        total_price=(TextView)findViewById(R.id.editText5);
        readWriteManagerInPrefference = new ReadWriteManagerInPrefference(this);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send mail
              String checkedString =   readWriteManagerInPrefference.readStringFromPrefference(UNCHECK_STRING);
               sendSMS(MyProfile.PHONE_NUMBER, checkedString);
                mLogger.error(checkedString);
                Intent intent = new Intent(TipCalculator.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Common.isEmptyString(Food_total.getText().toString())){
                    if(radio_one.isChecked()){
                        text_tip=(10*Float.parseFloat(Food_total.getText().toString()))/100;
                        tip.setText("$"+text_tip);
                    }else if(radio_two.isChecked()){
                        text_tip=(15*Float.parseFloat(Food_total.getText().toString()))/100;
                        tip.setText("$"+text_tip);
                    }else if(radio_three.isChecked()){
                        text_tip=(20*Float.parseFloat(Food_total.getText().toString()))/100;
                        tip.setText("$"+text_tip);
                    }else{
                        Toast.makeText(getApplicationContext(), "Select TIP Option", Toast.LENGTH_LONG).show();
                    }
                    text_Tax=(6*Float.parseFloat(Food_total.getText().toString()))/100;
                    tax.setText("$"+text_Tax);
                    float total=Float.parseFloat(Food_total.getText().toString())+text_Tax+text_tip;
                    String max2Decimal = String.format("%.2f", total);
                    total_price.setText("$"+max2Decimal);
                }else {
                    Toast.makeText(getApplicationContext(), "Empty Field!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

//Sends an SMS message to another device

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
