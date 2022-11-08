package com.example.roominandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roominandroid.DAO.UserData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edName, edAddress;
    RecyclerView recyclerView;
    Button btnAddd;

    UserAdapter userAdapter;
    List<User> list;

    private static final int MY_NAME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edUser);
        edAddress = findViewById(R.id.edAddress);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddd = findViewById(R.id.btnAdd);

        userAdapter = new UserAdapter(this, list, new UserAdapter.ClickItem() {
            @Override
            public void updateUser(User user) {
                clickUpdate(user);
            }
        });
        list = new ArrayList<>();
        userAdapter.setData((ArrayList<User>) list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(userAdapter);

        list = UserData.getInstance(this).userDAO().getListUser();
        userAdapter.setData((ArrayList<User>) list);
        btnAddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                showData();
            }
        });
    }

    private void showData() {

        list = new ArrayList<>();
        userAdapter.setData((ArrayList<User>) list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(userAdapter);

        list = UserData.getInstance(this).userDAO().getListUser();
        userAdapter.setData((ArrayList<User>) list);
    }

    private void addUser() {
        String name = edName.getText().toString();
        String address = edAddress.getText().toString();
        if (name.isEmpty() || address.isEmpty()) {
            return;
        }
        User user = new User(name, address);
        if (isUserExists(user)) {
            Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show();
            return;
        }

        UserData.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "Theem Thanh Cong", Toast.LENGTH_SHORT).show();
    }

    public boolean isUserExists(User user) {
        List<User> list = UserData.getInstance(this).userDAO().checkUser(user.getName());
        return list != null & !list.isEmpty();
    }

    private void clickUpdate(User user) {
        Intent intent = new Intent(MainActivity.this, ActivitiUpdate.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ob", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_NAME);

        list = UserData.getInstance(this).userDAO().getListUser();
        userAdapter.setData((ArrayList<User>) list);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == MY_NAME && requestCode == Activity.RESULT_OK) {
//            showData();
//        }
//    }
}