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
import com.backendless.async.callback.BackendlessCallback;

public class SignUp extends AppCompatActivity {
    EditText username,password,email;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent=getIntent();

        username=(EditText)findViewById(R.id.txt_name);
        email=(EditText)findViewById(R.id.txt_email);
        password=(EditText)findViewById(R.id.txt_password);
        signup=(Button)findViewById(R.id.btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.initApp(SignUp.this,"F4C26AE7-9CA0-8CB8-FF49-D66D9F1C0D00", "9D8E4C70-E734-CAA5-FFFD-B69BAE068400");
                final BackendlessUser user = new BackendlessUser();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setProperty("name",username.getText().toString());

                Backendless.UserService.register( user, new BackendlessCallback<BackendlessUser>()
                {
                    @Override
                    public void handleResponse( BackendlessUser backendlessUser )
                    {
                        Log.i( "Registration", backendlessUser.getEmail() + " successfully registered" );
                    }
                } );
            }
        });



    }
}
