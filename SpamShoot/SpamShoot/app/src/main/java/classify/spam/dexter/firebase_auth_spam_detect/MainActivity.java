package classify.spam.dexter.firebase_auth_spam_detect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tone,ttwo,tthree;

    ImageView changeProfile;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase mdb;
    DatabaseReference mRef;
    TextView nameT, userT, mailT;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tone=(TextView) findViewById(R.id.user_profile_name);
        ttwo=(TextView) findViewById(R.id.user_profile_email);


        //tthree=(TextView) findViewById(R.id.three);

        changeProfile=(ImageView)findViewById(R.id.user_profile_photo);

        changeProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(),"Change Profile Photo !",Toast.LENGTH_SHORT).show();
                return true;
            }
        });





        mdb=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        String currentUser=mAuth.getCurrentUser().getEmail();

        //Toast.makeText(Inbox.this, currentUser.replace("@spamshoot.com",""), Toast.LENGTH_SHORT).show();

        //mRef=mdb.getReference().child("SetData").child("11");
        mRef= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.replace("@spamshoot.com","")).child("details");



        firebaseAuth = FirebaseAuth.getInstance();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tone.setText(dataSnapshot.getValue(UserData.class).getName());
                ttwo.setText(dataSnapshot.getValue(UserData.class).getEmail());
                //tthree.setText(dataSnapshot.getValue(StudentData.class).getSname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Getting You There !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                startActivity(new Intent(MainActivity.this, SendMail.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.textheader);
        String uname = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("nameKey",uname);
        editor.commit();

        String unamestored=sharedPreferences.getString("nameKey","");



        nav_user.setText(unamestored);

        firebaseAuth = FirebaseAuth.getInstance();


        Toast.makeText(MainActivity.this, uname.replace("@spamshoot.com",""), Toast.LENGTH_SHORT).show();

        navigationView.setNavigationItemSelectedListener(this);

//        NaiveBayes nb=new NaiveBayes();
//
//        String isSpam=nb.naive("You have points to redeem");
//
//        Toast.makeText(this,isSpam,Toast.LENGTH_LONG).show();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inbox) {
  startActivity(new Intent(this,Inbox.class));



        } else if (id == R.id.nav_spam) {
            startActivity(new Intent(this,Spam.class));
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_trash) {
            startActivity(new Intent(this,Trash.class));
        } else if (id == R.id.chng_pass) {

        } else if (id == R.id.sent) {

            startActivity(new Intent(this,Sent.class));

        } else if (id == R.id.log_out) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, SignIn.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
