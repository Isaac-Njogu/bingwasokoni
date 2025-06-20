package com.data.datasokoni;
import static androidx.core.content.ContextCompat.startActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
public class login extends AppCompatActivity {
    TextView datasokoni,logintxt,forgotpassword,donthaveanaccount,signup;
     EditText loginedt,password;
     Button loginbtw;
     ImageButton googlebtw,facebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        signup=findViewById(R.id.signup);
        datasokoni=findViewById(R.id.datasokoni);
        logintxt=findViewById(R.id.logintxt);
        donthaveanaccount=findViewById(R.id.donthaveanaccount);
        loginedt=findViewById(R.id.loginedt);
        password=findViewById(R.id.password);
        loginbtw=findViewById(R.id.loginbtw);
        signup.setOnClickListener(v -> {
            Intent intent= new Intent(login.this,register.class);
            startActivity(intent);
        });
        googlebtw=findViewById(R.id.googlebtw);
        googlebtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?response_type=code&client_id=799222349882-ne3i0s9jdm5s0p7ll2d7tlsi1vc1halt.apps.googleusercontent.com&redirect_uri=https%3A%2F%2Fauth.openai.com%2Fapi%2Faccounts%2Fcallback%2Fgoogle&scope=openid%20profile%20email&state=2b339264-f6af-4388-b42d-668ea4f3b61e&audience=799222349882-ne3i0s9jdm5s0p7ll2d7tlsi1vc1halt.apps.googleusercontent.com&nonce=9GaOfkDJlbKEAE5DbGqG&service=lso&o2v=2&flowName=GeneralOAuthFlow";
            Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse((url)));
           startActivity(browserIntent);
            }
        });
        facebook=findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://web.facebook.com/login";
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW, Uri.parse((url)));
                startActivity(facebookIntent);
            }
        });
        forgotpassword=findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotpasswordintent = new Intent(login.this,forgotpassword.class);
                startActivity(forgotpasswordintent);
            }
        });
        loginbtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtlogin=loginedt.getText().toString();
                if (edtlogin.isEmpty()){
                    loginedt.setError("Username can not be Empty");
                    loginedt.requestFocus();
                }
                else {

                }
                String edtpassword = password.getText().toString().trim();

                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$";

                if (edtpassword.isEmpty()) {
                    password.setError("Password field cannot be empty");
                    password.requestFocus();
                } else if (!edtpassword.matches(passwordPattern)) {
                    password.setError("Password must be 8–16 characters, with uppercase, lowercase, number & special character");
                    password.requestFocus();
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