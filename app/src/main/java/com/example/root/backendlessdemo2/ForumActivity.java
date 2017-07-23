package com.example.root.backendlessdemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.root.backendlessdemo2.model.Complain;

import java.util.List;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Intent intent=getIntent();
        Log.e("TAG", "onClick:2 ");
        Backendless.initApp(getApplicationContext(),"F4C26AE7-9CA0-8CB8-FF49-D66D9F1C0D00", "9D8E4C70-E734-CAA5-FFFD-B69BAE068400");


        String whereClause = "";
        DataQueryBuilder dataQuery = DataQueryBuilder.create();
        dataQuery.setWhereClause( whereClause );

        Backendless.Persistence.of(Complain.class).find( new AsyncCallback<List<Complain>>() {
            @Override
            public void handleResponse(List<Complain> response) {
                
                Log.e("TAG", "onClick:2 "+response.get(0).toString()+"/n"+response.get(1).toString() );
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("TAG", "handleFault: "+fault.toString() );

            }
        });


    }
}
