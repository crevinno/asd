package com.sksoft.tipcalculatorsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText net_id_input;
    Button btn_continue;
    public static String net_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }
        net_id_input=(EditText)findViewById(R.id.editText);
        btn_continue=(Button)findViewById(R.id.button);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Common.isEmptyString(net_id_input.getText().toString())){
                    net_id=net_id_input.getText().toString();
                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                }else {
                    Toast.makeText(getApplicationContext(),"Empty Field!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
