package com.data.datasokoni;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class register extends AppCompatActivity {
    TextView datasokoni,Register,haveaccount,signin;
    EditText username,Email,phone,password,rpassword;
    Button Registerbtw;
    ImageButton googlebtw,facebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        datasokoni=findViewById(R.id.datasokoni);
        Register=findViewById(R.id.Register);
        haveaccount=findViewById(R.id.haveaccount);
        signin=findViewById(R.id.signin);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        rpassword=findViewById(R.id.rpassword);
        Registerbtw=findViewById(R.id.Registerbtw);
        Email=findViewById(R.id.Email);
        signin.setOnClickListener(v -> {
            Intent intent= new Intent(register.this, login.class);
            startActivity(intent);
        });
        googlebtw=findViewById(R.id.googlebtw);
        googlebtw.setOnClickListener(v -> {
            String url="https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?response_type=code&client_id=799222349882-ne3i0s9jdm5s0p7ll2d7tlsi1vc1halt.apps.googleusercontent.com&redirect_uri=https%3A%2F%2Fauth.openai.com%2Fapi%2Faccounts%2Fcallback%2Fgoogle&scope=openid%20profile%20email&state=2b339264-f6af-4388-b42d-668ea4f3b61e&audience=799222349882-ne3i0s9jdm5s0p7ll2d7tlsi1vc1halt.apps.googleusercontent.com&nonce=9GaOfkDJlbKEAE5DbGqG&service=lso&o2v=2&flowName=GeneralOAuthFlow";
            Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse((url)));
            startActivity(browserIntent);
        });
        facebook=findViewById(R.id.facebook);
        facebook.setOnClickListener(v -> {
            String url="https://web.facebook.com/login";
            Intent facebookIntent=new Intent(Intent.ACTION_VIEW, Uri.parse((url)));
            startActivity(facebookIntent);
        });
        Registerbtw.setOnClickListener(v -> {
            String edt_username=username.getText().toString();
            String edtEmail=Email.getText().toString();
            String edtphone=phone.getText().toString();
            String edtpassword=password.getText().toString();
            String edtrpassword=rpassword.getText().toString();
            String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,16}$";
            if (edt_username.isEmpty()){
                username.setError("Username can not be Empty");
                username.requestFocus();
            }
            if (edtEmail.isEmpty()){
                Email.setError("Username can not be Empty");
                Email.requestFocus();
                return;
            } else if (!edtEmail.endsWith("@gmail.com")) {
                Email.setError("please include @gmail.com");
                Email.requestFocus();
                return;
            }
            if (edtphone.isEmpty()){
                phone.setError("Username can not be Empty");
                phone.requestFocus();
                return;
            } else if (!edtphone.startsWith("+254")) {
                phone.setError("Phone number must start with +254");
                phone.requestFocus();
                return;
            }
            else if (edtphone.length() != 13) {
                phone.setError("Phone number must be exactly 13 digits");
                phone.requestFocus();
                return;
            } else if (!edtphone.matches("\\+254[0-9]{9}")) {
                phone.setError("Invalid phone number format");
                phone.requestFocus();
                return;
            }
            if (edtpassword.isEmpty()) {
                password.setError("Password cannot be empty");
                password.requestFocus();
            } else if (!edtpassword.matches(passwordPattern)) {
                password.setError("Password must be 8-16 characters with uppercase, lowercase, digit & special character");
                password.requestFocus();
            } else if (edtrpassword.isEmpty()) {
                rpassword.setError("Re Enter password");
                rpassword.requestFocus();
            } else if (!edtpassword.equals(edtrpassword)) {
                rpassword.setError("Passwords do not match");
                rpassword.requestFocus();
            }
            else
            {
                mAuth.createUserWithEmailAndPassword(edtEmail, edtpassword)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                assert firebaseUser != null;
                                String userId = firebaseUser.getUid();
                                user user = new user(edt_username, edtEmail, edtphone);
                                databaseReference.child(userId).setValue(user)
                                        .addOnCompleteListener(dbTask -> {
                                            if (dbTask.isSuccessful()) {
                                                Intent intent = new Intent(register.this, homepage.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Email.setError("Failed to save user info. Try again.");
                                                Email.requestFocus();
                                            }
                                        });
                            } else {
                               Email.setError("Registration failed: " + Objects.requireNonNull(task.getException()).getMessage());
                                Email.requestFocus(); Email.setError("Registration failed: " + Objects.requireNonNull(task.getException()).getMessage());
                                Email.requestFocus();

                            }
                        });
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}