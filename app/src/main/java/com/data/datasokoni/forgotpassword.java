package com.data.datasokoni;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class forgotpassword extends AppCompatActivity {
TextView datasokoni,forgotpassword;
    EditText Email;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotpassword);
        datasokoni=findViewById(R.id.datasokoni);
        forgotpassword=findViewById(R.id.forgotpassword);
        Email=findViewById(R.id.Email);
        submit=findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String edtemail=Email.getText().toString();
                    if (edtemail.isEmpty())
                    {
                        Email.setError("Emai field can not be empty");
                        Email.requestFocus();
                    }
                    else if(!edtemail.endsWith("@gmail.com")){
                        Email.setError("Invalid Email Address");
                        Email.requestFocus();

                    }
                    else {

                    }
                }
            });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}