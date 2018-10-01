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

public class Register extends AppCompatActivity {

    TextView goSignin;
    EditText sMail,sPass,sUsername,sName;
    Button signUp;
    String email,password,username,name;
    DatabaseReference fred;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        firebaseAuth=FirebaseAuth.getInstance();

        fred= FirebaseDatabase.getInstance().getReference().child("Users");

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(Register.this, MainActivity.class));
            finish();
        }




        signUp=(Button) findViewById(R.id.btn_signup);

        sName=(EditText) findViewById(R.id.nameEdit);
        sUsername=(EditText) findViewById(R.id.userEdit);
        //sMail=(EditText) findViewById(R.id.mailEdit);
        sPass=(EditText) findViewById(R.id.input_password_s);



        goSignin=(TextView) findViewById(R.id.link_signin);

        goSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,SignIn.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=sUsername.getText().toString().trim().concat("@spamshoot.com");


                name=sName.getText().toString().trim();
                username=sUsername.getText().toString().trim();
                password=sPass.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    sName.setError("Please Enter Name");
                    sName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    sUsername.setError("Please Enter Name");
                    sUsername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    sPass.setError("Enter a password");
                    sPass.requestFocus();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(Register.this, "loginWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            DatabaseReference n=fred.child(username).child("details");
                            n.child("name").setValue(name);
                            n.child("username").setValue(username);
                            n.child("email").setValue(email);
                            n.child("bio").setValue("");
                            startActivity(new Intent(Register.this, MainActivity.class));
                            DatabaseReference newref=fred.child(username);


                            finish();
                        }

                    }
                });





                }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
    }
}
