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
import com.backendless.exceptions.BackendlessFault;
import com.example.root.backendlessdemo2.model.Comment;
import com.example.root.backendlessdemo2.model.Complain;


public class MainActivity extends AppCompatActivity{
    Button signup,login,forum;
    EditText email,password;
    public String mail,pass;
    Comment comment;
    public String userid;
    String TAG="TAG";
    //String appVersion = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup=(Button)findViewById(R.id.btn_register);
        login=(Button)findViewById(R.id.btn_login);
        email=(EditText)findViewById(R.id.txt_email);
        password=(EditText)findViewById(R.id.txt_password);
        forum=(Button)findViewById(R.id.btn_forum);

        comment=new Comment();
        comment.setAuthorEmail("rumi@gmail.com");
        comment.setMessage("Hello world");




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.initApp(getApplicationContext(),"F4C26AE7-9CA0-8CB8-FF49-D66D9F1C0D00", "9D8E4C70-E734-CAA5-FFFD-B69BAE068400");

                loginUserAsync();
//                passMessage();
//                subscribeMessage();
            }
        });

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,SignUp.class);
               startActivity(intent);
           }
       });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ForumActivity.class);
                startActivity(intent);
            }
        });

    }

//    private void passMessage()
//    {
//        AsyncCallback<MessageStatus> callback1=new AsyncCallback<MessageStatus>() {
//            @Override
//            public void handleResponse(MessageStatus response) {
//                Log.e("TAG", "handleResponse:messgae " +response);
//
//
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//
//            }
//        };
//        Backendless.Messaging.publish( comment, callback1);
//        //Log.e("TAG", "handleResponse: "+status.getStatus() );
//
//       }
//
//    private void subscribeMessage(){
//        Backendless.Messaging.subscribe(
//                new AsyncCallback<List<Message>>()
//                {
//                    public void handleResponse( List<Message> response )
//                    {
//                        for( Message message : response )
//                        {
//                            String publisherId = message.getPublisherId();
//                            Object data = message.getData();
//                            Toast.makeText(MainActivity.this, ""+data.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    public void handleFault( BackendlessFault fault )
//                    {
//                        Toast.makeText( MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT ).show();}
//                },
//
//                new AsyncCallback<Subscription>()
//                {
//                    public void handleResponse( Subscription response )
//                    {
//                        Subscription subscription = response;
//                    }
//                    public void handleFault( BackendlessFault fault )
//                    {
//                        Toast.makeText( MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT ).show();
//                    }
//                }
//        );
//    }



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
                userid=loggedInUser.getObjectId();
                Log.e(TAG, "userid1is: "+userid);
                Log.e(TAG, "handleResponse: "+loggedInUser.getUserId() );
                if (userid!=null){
                    Intent intent=new Intent(MainActivity.this, ComplainActivity.class);
                    intent.putExtra("rumi",userid);
                    Log.e(TAG, "userid:2 "+userid );
                    startActivity(intent);
                }
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
