package classify.spam.dexter.firebase_auth_spam_detect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import classify.spam.dexter.firebase_auth_spam_detect.Mail;


public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.myViewHolder>
    {

        private ArrayList<String> senders;
        private ArrayList<String> subjecs;
        private ArrayList<String> messages;
        private Context ctx;


        public CustomAdapter(ArrayList<String> senders, ArrayList<String> subjecs, ArrayList<String> messages,Context ctx) {
            this.senders = senders;
            this.subjecs = subjecs;
            this.messages = messages;
            this.ctx=ctx;
        }

        public CustomAdapter()
        {

        }



        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
            View view=layoutInflater.inflate(R.layout.mail_list,parent,false);
            myViewHolder myViewH=new myViewHolder(view,ctx);
            return myViewH;


        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {



            String sen=senders.get(position),mes=messages.get(position),sub=subjecs.get(position);

            ColorGenerator generator = ColorGenerator.MATERIAL;

            int color=generator.getRandomColor();

            String cap=senders.get(position).toUpperCase();


            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(cap.charAt(0)), color); // radius in px

            holder.backIcon.setImageDrawable(drawable);



            holder.mailSend.setText(sen);
            holder.mailSub.setText(sub);
            holder.mailMsg.setText(mes);

           // holder.mailInit.setText(String.valueOf(sen.charAt(0)));



        }



        @Override
        public int getItemCount() {
            return senders.size();
        }




        public class myViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

            TextView mailSub,mailSend,mailMsg,mailInit;
            ImageView backIcon;
            ImageView starIcon;
            private ClickListener clicklistener;
            Context ctx;

              public myViewHolder(View itemView, final Context ctx) {
              super(itemView);

                  this.ctx=ctx;

              itemView.setOnClickListener(this);



              mailSend=(TextView) itemView.findViewById(R.id.mailListFrom);
              mailMsg=(TextView) itemView.findViewById(R.id.mailListMessage);
              mailSub=(TextView) itemView.findViewById(R.id.mailListSubject);
              mailInit=(TextView) itemView.findViewById(R.id.icon_text);
              backIcon=(ImageView) itemView.findViewById(R.id.icon_back_circle);

//                  backIcon.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View view) {
//                          int po=getAdapterPosition();
//                          Toast.makeText(ctx,senders.get(po),Toast.LENGTH_LONG).show();
//
//                      }
//                  });

                  backIcon.setOnClickListener(this);
                  mailMsg.setOnClickListener(this);
                  mailSub.setOnClickListener(this);
                  mailSend.setOnClickListener(this);







          }


            @Override
            public void onClick(View view) {

                int pos=getAdapterPosition();

                Intent i=new Intent(this.ctx,DetailActivity.class);
                i.putExtra("KEY_SUB",subjecs.get(pos));
                i.putExtra("KEY_MSG",messages.get(pos));
                i.putExtra("KEY_SEN",senders.get(pos));
                this.ctx.startActivity(i);



            }
        }


    }