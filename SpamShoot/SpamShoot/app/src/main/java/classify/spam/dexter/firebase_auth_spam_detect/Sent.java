package classify.spam.dexter.firebase_auth_spam_detect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class Sent extends AppCompatActivity {
    private RecyclerView recyclerView;
    ListView listView;
    private RecyclerView.LayoutManager layoutManager;
    private CoordinatorLayout coordinatorLayout;
    ArrayList<Mail> dataModels= new ArrayList<>();;
    ProgressBar progressBar;

    DatabaseReference dref,fred;
    FirebaseAuth mAuth;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayListMes=new ArrayList<>();
    ArrayList<String> arrayListSen=new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecycleview);
        //progressBar=(ProgressBar) findViewById(R.id.progressBar3);

        //progressBar.setVisibility(View.VISIBLE);
        setTitle("SpamShoot : Sent");
        coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        //listView=(ListView) findViewById(R.id.listview);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.mylist,R.id.Itemname,arrayList);

        recyclerView= (RecyclerView) findViewById(R.id.myRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter=new CustomAdapter(arrayListSen,arrayList,arrayListMes,this);

        recyclerView.setAdapter(adapter);

        mAuth=FirebaseAuth.getInstance();

        String currentUser=mAuth.getCurrentUser().getEmail();
        //Toast.makeText(Inbox.this, currentUser.replace("@spamshoot.com",""), Toast.LENGTH_SHORT).show();
        dref= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.replace("@spamshoot.com","")).child("mails").child("sent");
        //dref= FirebaseDatabase.getInstance().getReference().child("Mails");

        //listView.setAdapter(adapter);



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Snackbar snackbar=Snackbar.make(coordinatorLayout,arrayList.get(i)+arrayListSen.get(i)+arrayListMes.get(i), Snackbar.LENGTH_SHORT);
//
//                openMaster(arrayList.get(i),arrayListMes.get(i),arrayListSen.get(i));
//
//                snackbar.show();
//
//
//
//                //Toast.makeText(Inbox.this,arrayList.get(i),Toast.LENGTH_LONG).show();
//            }
//
//
//        });


//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Sent.this,arrayList.get(i),Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    MailSent mail=ds.getValue(MailSent.class);

                    //setTitle(mail.getSubject());
                    arrayList.add(mail.getSubject());
                    arrayListMes.add(mail.getMessage());
                    arrayListSen.add(mail.getTo());

                }

                adapter.notifyDataSetChanged();
                //progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        dref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                for(DataSnapshot ds:dataSnapshot.getChildren())
//                {
//                    Mail mail=ds.getValue(Mail.class);
//
//                   // setTitle(mail.getSubject());
//                    arrayList.add(mail.getSubject());
//                    arrayListMes.add(mail.getMessage());
//                    arrayListSen.add(mail.getFrom());
//
//                }
//                adapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                    arrayList.remove(dataSnapshot.getValue(String.class));
//                    adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



    }

    private void openMaster(String...args)
    {
        Intent i=new Intent(getApplicationContext(),DetailActivity.class);
        i.putExtra("KEY_SUB",args[0]);
        i.putExtra("KEY_MSG",args[1]);
        i.putExtra("KEY_SEN",args[2]);
        startActivity(i);


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
}
