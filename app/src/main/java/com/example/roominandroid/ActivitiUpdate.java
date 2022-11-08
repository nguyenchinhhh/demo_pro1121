package com.example.roominandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roominandroid.DAO.UserData;

public class ActivitiUpdate extends AppCompatActivity {
    EditText edName, edAddress;
    Button btnAddd;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiti_update);

        edName = findViewById(R.id.edUser);
        edAddress = findViewById(R.id.edAddress);

        btnAddd = findViewById(R.id.btnUpdateUser);

        user = (User) getIntent().getExtras().get("ob");
        if (user != null) {
            edName.setText(user.getName());
            edAddress.setText(user.getAddress());
        }
        btnAddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }


        });
    }

    private void updateUser() {
        String name = edName.getText().toString();
        String address = edAddress.getText().toString();
        if (name.isEmpty() || address.isEmpty()) {
            return;
        }
        //update
        user.setName(name);
        user.setAddress(address);
        UserData.getInstance(this).userDAO().updateUser(user);
        Toast.makeText(this, "Update thành Công", Toast.LENGTH_SHORT).show();

//        Intent intent1 = new Intent();
//        setResult(Activity.RESULT_OK , intent1);
        finish();
    }
}