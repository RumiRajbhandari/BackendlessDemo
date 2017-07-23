package com.example.root.backendlessdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.root.backendlessdemo2.model.Complain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ComplainActivity extends AppCompatActivity {
    public EditText txtTo, txtHead, txtBody;
    Button send,btnPost;
    String TAG = "TAG";
    public String objectId;
    public String userid,to,head,body,datee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        Bundle bundle = getIntent().getExtras();
        userid = bundle.getString("rumi");

        txtTo = (EditText) findViewById(R.id.send_to);
        txtHead = (EditText) findViewById(R.id.send_head);
        txtBody = (EditText) findViewById(R.id.send_body);
        send = (Button) findViewById(R.id.btn_send);
        btnPost=(Button)findViewById(R.id.btn_post);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveComplain();

             }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComplain();
            }
        });
     }


    public void saveComplain(){


//                 BackendlessUser user = Backendless.UserService.CurrentUser();
//                userid=user.getUserId();

        to=txtTo.getText().toString();
        body=txtBody.getText().toString();
        head=txtHead.getText().toString();
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        datee = df.format(Calendar.getInstance().getTime());
        Log.e(TAG, "userid3: "+userid );
        //complain.setId(userid);

        Backendless.Persistence.save( new Complain(to,head, body,datee,userid), new BackendlessCallback<Complain>()
        {

            @Override
            public void handleResponse(Complain response) {
                Toast.makeText(ComplainActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                objectId=response.getObjectId();
                Log.e(TAG, "object id "+objectId );
                Backendless.Persistence.of( Complain.class).findFirst(new AsyncCallback<Complain>() {
                    @Override
                    public void handleResponse(Complain response) {
                        Log.e(TAG, "handleResponse:2 " +response.getBody());
                        Log.e(TAG, "handleResponse:2 " +response.toString());

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
            }
        } );
    }

    public void postComplain(){
         String whereClause = "objectId='"+objectId+"'";
                DataQueryBuilder dataQuery = DataQueryBuilder.create();
                dataQuery.setWhereClause( whereClause );
                //Log.e(TAG, "userid: "+userid );

                Backendless.Persistence.of(Complain.class).find(dataQuery, new AsyncCallback<List<Complain>>() {
                    @Override
                    public void handleResponse(List<Complain> response) {
                        Log.e(TAG, "object id2 "+objectId );
                        Log.e(TAG, "onClick:2 "+response.get(0).toString()+"/n" );
                        Toast.makeText(ComplainActivity.this, "Posted to Forum", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "handleFault: "+fault.toString() );

                    }
                });
    }
}