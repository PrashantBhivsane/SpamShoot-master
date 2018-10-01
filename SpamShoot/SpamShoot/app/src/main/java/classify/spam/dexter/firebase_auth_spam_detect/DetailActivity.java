package classify.spam.dexter.firebase_auth_spam_detect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.firebase.auth.FirebaseAuth;

public class DetailActivity extends AppCompatActivity {

    TextView masterSub,masterMessage,masterSender,masterTo;
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        masterSub=(TextView) findViewById(R.id.detailText);
        masterMessage=(TextView) findViewById(R.id.detailMessage);
        masterSender=(TextView) findViewById(R.id.detailSender);
        masterTo=(TextView) findViewById(R.id.detailTo);

        im= (ImageView) findViewById(R.id.imageView3);





        Intent i=this.getIntent();
        String sub=i.getExtras().getString("KEY_SUB");
        String sen=i.getExtras().getString("KEY_SEN");
        String mes=i.getExtras().getString("KEY_MSG");

        masterSub.setText(sub);
        masterMessage.setText(mes);
        masterSender.setText(sen);

        masterTo.setText("to "+FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@spamshoot.com",""));


        ColorGenerator generator = ColorGenerator.MATERIAL;

        int color=generator.getRandomColor();

        String cap=sen.toUpperCase().substring(0);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(cap.charAt(0)), color); // radius in px

        im.setImageDrawable(drawable);

        setTitle("");

    }
}
