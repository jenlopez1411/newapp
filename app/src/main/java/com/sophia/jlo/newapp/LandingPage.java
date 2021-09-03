package com.sophia.jlo.newapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingPage extends AppCompatActivity {
    public static final String ACTIVITY_LABEL = "SECOND_ACTIVITY_COM_SOPHIA";
    private UserDao userDao;
    private TextView textViewResult;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getDatabase();
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText(text);
        List<User> user = userDao.getAllUsers();

        for(User Users: user){
            if(Users.getUserName().equals(text)){
                Log.v("LandingPage", "list" + Users.getId());
                TextView tv2 = (TextView) findViewById(R.id.tv2);
                userID = Users.getId();
                tv2.setText("" + Users.getId());
            }
        }
        //retrofit
        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    if(post.getUserId() == userID) {
                        Log.v("Api", "" + post.getId() + post.getText());
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User ID: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n\n";
                        textViewResult.append(content);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.v("ErrorApi", "errorApi");
                textViewResult.setText(t.getMessage());
            }
        });
        String toastValue = getIntent().getStringExtra(ACTIVITY_LABEL);
        Toast.makeText(this, toastValue, Toast.LENGTH_SHORT).show();

    }
    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(LandingPage.ACTIVITY_LABEL, toastValue);
        return intent;
    }
    public void getDatabase(){
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }
}
