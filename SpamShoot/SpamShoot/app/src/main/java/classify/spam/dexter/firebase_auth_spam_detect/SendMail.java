package classify.spam.dexter.firebase_auth_spam_detect;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Prashant on 05-10-2017.
 */

public class SendMail extends AppCompatActivity{

    EditText mFrom,mTo,mSub,mMsg;
    Button mSend;
    FirebaseAuth mAuth;
    String from,to,subject,message,f="From : ",hamtext,spamtext;
    DatabaseReference fred,sred,sentmail,ssd,shd;
    FirebaseStorage storage;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose_mail);

        storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://showit-82a31.appspot.com").child("sp.txt");

        InputStream stream = getResources().openRawResource(R.raw.spam);


        UploadTask uploadTask = storageReference.putStream(stream);

        mFrom=(EditText) findViewById(R.id.frmMail);
        mTo=(EditText) findViewById(R.id.toMail);
        mSub=(EditText) findViewById(R.id.subjMail);
        mMsg=(EditText) findViewById(R.id.msgMail);

        mSend=(Button) findViewById(R.id.sendMail);

        setTitle("Compose a Mail !");
        mAuth=FirebaseAuth.getInstance();

        mFrom.setKeyListener(null);



        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //
                //
                to=mTo.getText().toString();
                fred= FirebaseDatabase.getInstance().getReference().child("Users").child(to).child("mails").child("inbox");
                sred=FirebaseDatabase.getInstance().getReference().child("Users").child(to).child("mails").child("spam");
                ssd=FirebaseDatabase.getInstance().getReference().child("SpamData");
                shd=FirebaseDatabase.getInstance().getReference().child("HamData");


                //DatabaseReference mailRef=fred.child(to).child("mails");
                subject=mSub.getText().toString();
                message=mMsg.getText().toString();



                String currentUser= mAuth.getCurrentUser().getEmail();
                if(to.equals(currentUser.replace("@spamshoot.com","")))
                {
                    Toast.makeText(getApplicationContext(),"Logically You Should not Mail Yourself !",Toast.LENGTH_LONG).show();
                }


                sentmail=FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.replace("@spamshoot.com","")).child("mails").child("sent");



                String keysent=sentmail.push().getKey();

                sentmail.child(keysent).child("to").setValue(to);
                sentmail.child(keysent).child("subject").setValue(subject);
                sentmail.child(keysent).child("message").setValue(message);


                NaiveBayes nb=new NaiveBayes();

                hamtext=readRawTextFile(getApplicationContext(),R.raw.ham);
                spamtext=readRawTextFile(getApplicationContext(),R.raw.spam);



                String isSpam=nb.naive(message,spamtext,hamtext);

                //ssd.push().setValue(spamtext);
                //shd.push().setValue(hamtext);

                Toast.makeText(getApplicationContext(),isSpam,Toast.LENGTH_LONG).show();

                if(isSpam.equals("spam"))
                {
                    String keyp=sred.push().getKey();
                    sred.child(keyp).child("from").setValue(currentUser);
                    sred.child(keyp).child("subject").setValue(subject);
                    sred.child(keyp).child("message").setValue(message);
                }


                mTo.setText("");
                mSub.setText("");
                mMsg.setText("");

                Toast.makeText(getApplicationContext(),"Mail Sucessully Sent To : " +to,Toast.LENGTH_LONG).show();


            }
        });





    }

    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    boolean writeToFile(String toWrite,String fileName)
    {
        try {
            File file = getFileStreamPath(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream writer = openFileOutput(file.getName(), Context.MODE_PRIVATE);


                writer.write(toWrite.getBytes());
                writer.flush();


            writer.close();
            //display file saved message
            Toast.makeText(getBaseContext(), Environment.getExternalStorageDirectory().toString(),
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
