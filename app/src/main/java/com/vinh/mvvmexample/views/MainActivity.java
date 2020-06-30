package com.vinh.mvvmexample.views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vinh.mvvmexample.R;
import com.vinh.mvvmexample.adapters.UserAdapter;
import com.vinh.mvvmexample.databinding.ActivityMainBinding;
import com.vinh.mvvmexample.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements UserAdapter.UserAdapterListener {

    private HandlersClicked handlers;
    private ActivityMainBinding activityMainBinding;

    private RecyclerView recyclerView;
    private List<UserViewModel> data = new ArrayList<>();
    private UserAdapter userAdapter;
    private FloatingActionButton fab;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);

        // generate-data
        UserViewModel userViewModel = new UserViewModel("Quang Vinh", 20);
        data.add(userViewModel);
        UserViewModel userViewModel1 = new UserViewModel("Hoang Long", 20);
        data.add(userViewModel1);

        userAdapter = new UserAdapter(data, this, this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        handlers = new HandlersClicked(this);
        activityMainBinding.setHandlers(handlers);
    }

    @Override
    public void onUserClicked(UserViewModel user, int position) {
        Toast.makeText(this, "Clicked: " + user.getUserName() + " - " + user.getUserAge(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserLongClicked(UserViewModel user, int position) {
        data.remove(position);
        userAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Removed: " + user.getUserName() + " - " + user.getUserAge(), Toast.LENGTH_SHORT).show();
    }


    private void addUser() {
        data.add(new UserViewModel("User " + data.size(), Math.abs(random.nextInt() % 40)));
        userAdapter.notifyItemInserted(data.size() - 1);
    }



    public class HandlersClicked {
        Context context;

        public HandlersClicked(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            addUser();
        }
    }

}
