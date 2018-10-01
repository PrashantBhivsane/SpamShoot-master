package classify.spam.dexter.firebase_auth_spam_detect;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class Design_Activity_MailBox extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> datas,datsu,datam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecycleview);
        datas=new ArrayList<String>();
        datsu=new ArrayList<String>();
        datam=new ArrayList<String>();



        recyclerView= (RecyclerView) findViewById(R.id.myRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //String[] datas={"Prashant Bhivsane","Andrew Flintoff","AB Devilliers","M.S. Dhoni","Arya Stark","Robb Stark"};
        //String[] datsu={"Winter Is Here","Get 50% Off on All Products","Fastest 200 Done !","Ticket Collection is so boring","A girls has no name","Red Wedding was awful"};
        //String[] datam={"Enter your list for random picking with each item/name on a new line.count will display in the","Enter your list for random picking with each item/name on a new line.","Enter your list for random picking with each item/name on a new line.","Enter your list for random picking with each item/name on a new line.","Enter your list for random picking with each item/name on a new line.","Enter your list for random picking with each item/name on a new line."};

        datas.add("Prashant Bhivsane");
        datas.add("Raghav Thatte");
        datas.add("Hrudaynath Dhabe");
        datas.add("Ameya Babhulgaonkar");
        datas.add("Jatin Warade");

        datsu.add("Winter Is Here");
        datsu.add("Get 50% Off on All Products Get 50% Off on All Products");
        datsu.add("Fastest 200 Done !");
        datsu.add("Ticket Collection is so boring");
        datsu.add("A girls has no name");

        datam.add("Enter your list for random picking with each item/name on a new line.count will display in the");
        datam.add("Enter your list for random picking with each item/name on a new line.count will display in the");
        datam.add("Enter your list for random picking with each item/name on a new line.count will display in the");
        datam.add("Enter your list for random picking with each item/name on a new line.");
        datam.add("Ticket Collection is so boring line.count will display in the");




        recyclerView.setAdapter(new CustomAdapter(datas,datsu,datam,this));


    }
}
