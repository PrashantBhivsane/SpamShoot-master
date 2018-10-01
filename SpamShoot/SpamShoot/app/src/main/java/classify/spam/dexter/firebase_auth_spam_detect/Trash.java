package classify.spam.dexter.firebase_auth_spam_detect;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;


public class Trash extends AppCompatActivity {
    private RecyclerView recyclerView;
    ListView listView;
    private RecyclerView.LayoutManager layoutManager;
    private CoordinatorLayout coordinatorLayout;
    ArrayList<Mail> dataModels= new ArrayList<>();;
    ProgressBar progressBar;

    DatabaseReference dref;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        setTitle("SpamShoot : Trash");
        progressBar=(ProgressBar) findViewById(R.id.progressBar3);

        progressBar.setVisibility(View.VISIBLE);

        coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        listView=(ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.mylistsimple,R.id.ItemnameS,arrayList);
        dref= FirebaseDatabase.getInstance().getReference().child("Trash");
        dref.keepSynced(true);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar snackbar=Snackbar.make(coordinatorLayout,arrayList.get(i), Snackbar.LENGTH_SHORT);

                snackbar.show();
                //Toast.makeText(Inbox.this,arrayList.get(i),Toast.LENGTH_LONG).show();
            }


        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Trash.this,arrayList.get(i),Toast.LENGTH_LONG).show();
                return true;
            }
        });

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                arrayList.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                arrayList.remove(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            dref.removeValue();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
