package com.example.chess360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chess360.dao.Dao;
import com.example.chess360.vo.Club;
import com.example.chess360.vo.Organizer;
import com.example.chess360.vo.Player;

public class ProfileOrganizer extends AppCompatActivity {

    private String userProfile;
    private String userSearch;
    private TextView username;
    private boolean searchingUser;

    private Button post, follow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_organizer);

        // Buttons:
        post = findViewById(R.id.profile_organizer_post_button);
        follow = findViewById(R.id.profile_organizer_follow_button);

        // User input:
        String [] userInput = retrieveHomeData();
        this.userProfile = userInput[0];
        this.userSearch = userInput[1];

        // Searching user?:
        this.searchingUser = this.userSearch==null? false:true;

        this.username = findViewById(R.id.profile_organizer_username);

        this.setUsername();
        this.showButtons();
    }

    private void showButtons(){

        if (this.searchingUser && !this.userProfile.equals(this.userSearch)){
            post.setVisibility(View.GONE);
            follow.setVisibility(View.VISIBLE);
        }
        else{
            post.setVisibility(View.VISIBLE);
            follow.setVisibility(View.GONE);
        }
    }

    private String[] retrieveHomeData(){

        String [] output = new String[2];
        Bundle extras = getIntent().getExtras();
        output[0] = extras.getString("CURRENT_USER");
        output[1] = extras.getString("SEARCHED_USER");

        return(output);
    }

    private void setUsername(){

        Organizer myOrganizer;

        if (!this.searchingUser){
            myOrganizer = Dao.getOrganizer(this.userProfile);
        }
        else{
            myOrganizer = Dao.getOrganizer(this.userSearch);
        }

        String output = "@";
        output += myOrganizer.getUsername();
        this.username.setText(output);
    }

    // Creates the option menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.full_menu, menu);
        return true;
    }

    // Sets the functionality of the options menu:
    @Override
    public boolean
    onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.full_menu_go_back_button:
                Intent i = new Intent(this, HomeActivity.class);
                i.putExtra("LOGOUT","NO");
                setResult(RESULT_OK, i);
                finish();
                return true;
            case R.id.full_menu_log_out_button:
                i = new Intent(this, HomeActivity.class);
                i.putExtra("LOGOUT","YES");
                setResult(RESULT_OK, i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}