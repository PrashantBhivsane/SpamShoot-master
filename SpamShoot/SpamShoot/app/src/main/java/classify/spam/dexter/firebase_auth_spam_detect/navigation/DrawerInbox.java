package classify.spam.dexter.firebase_auth_spam_detect.navigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import classify.spam.dexter.firebase_auth_spam_detect.Inbox;
import classify.spam.dexter.firebase_auth_spam_detect.MainActivity;
import classify.spam.dexter.firebase_auth_spam_detect.R;
import classify.spam.dexter.firebase_auth_spam_detect.Sent;
import classify.spam.dexter.firebase_auth_spam_detect.SignIn;
import classify.spam.dexter.firebase_auth_spam_detect.Spam;
import classify.spam.dexter.firebase_auth_spam_detect.Trash;


public class DrawerInbox extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_pass);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_s);

        View hView = navigationView.getHeaderView(1);


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



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            //firebaseAuth.signOut();
            finish();
            startActivity(new Intent(DrawerInbox.this, SignIn.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
