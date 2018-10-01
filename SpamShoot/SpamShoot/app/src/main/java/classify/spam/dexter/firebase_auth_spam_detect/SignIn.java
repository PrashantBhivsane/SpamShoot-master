package classify.spam.dexter.firebase_auth_spam_detect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prashant on 04-10-2017.
 */

public class SignIn extends AppCompatActivity {

    TextView goRegister;
    EditText mail,passwd;
    Button lg;
    FirebaseAuth firebaseAuth;
    String email,password,username;
    DatabaseReference fred;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        goRegister=(TextView) findViewById(R.id.link_signup);
        mail=(EditText) findViewById(R.id.input_email);
        passwd=(EditText) findViewById(R.id.input_password);
        lg=(Button)findViewById(R.id.btn_login);
        firebaseAuth=FirebaseAuth.getInstance();


        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }


        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username=mail.getText().toString().trim();
                password=passwd.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    mail.setError("Please enter username");
                    mail.requestFocus();
                    return;
                }



                if (TextUtils.isEmpty(password)) {
                    passwd.setError("Enter a password");
                    passwd.requestFocus();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignIn.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SignIn.this, MainActivity.class));

                            finish();
                        }

                    }
                });


            }
        });




        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,Register.class));
            }
        });
    }



}
