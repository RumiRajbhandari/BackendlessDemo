package com.example.root.backendlessdemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;


public class MainActivity extends AppCompatActivity {
    Button signup,login;
    EditText email,password;
    public String mail,pass;
    //String appVersion = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup=(Button)findViewById(R.id.btn_register);
        login=(Button)findViewById(R.id.btn_login);
        email=(EditText)findViewById(R.id.txt_email);
        password=(EditText)findViewById(R.id.txt_password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.initApp(getApplicationContext(),"F4C26AE7-9CA0-8CB8-FF49-D66D9F1C0D00", "9D8E4C70-E734-CAA5-FFFD-B69BAE068400");
                loginUserAsync();
            }
        });

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,SignUp.class);
               startActivity(intent);
           }
       });

    }


    private  void loginUserAsync()
    {
        mail=email.getText().toString();
        pass=password.getText().toString();
        Log.e("TAG", "loginUser: "+mail+pass );
        AsyncCallback<BackendlessUser> callback = new AsyncCallback<BackendlessUser>()
        {
            @Override
            public void handleResponse( BackendlessUser loggedInUser )
            {
                System.out.println( "User has been logged in - " + loggedInUser.getObjectId() );
            }

            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {
                System.out.println( "Server reported an error - " + backendlessFault.getMessage() );
            }
        };

        Backendless.UserService.login( mail, pass , callback );
    }
}
