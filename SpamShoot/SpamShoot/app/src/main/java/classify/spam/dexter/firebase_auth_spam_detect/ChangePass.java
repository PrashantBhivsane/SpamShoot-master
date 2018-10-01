package classify.spam.dexter.firebase_auth_spam_detect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Prashant on 05-10-2017.
 */



public class ChangePass extends AppCompatActivity{

    EditText pass,passc;
    Button chng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_pass);

        //pass=(EditText) findViewById(R.id.passco);
        //passc=(EditText) findViewById(R.id.passct);

        chng=(Button) findViewById(R.id.chng_pass);

        chng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pass.getText().equals(passc.getText()))
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    user.updatePassword(pass.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangePass.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ChangePass.this,MainActivity.class));
                                    } else {
                                        Toast.makeText(ChangePass.this, "Failed to update password!", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });

    }
}