package com.ismailhakkiaydin.livedatamvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainViewModel;

    BlogAdapter blogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.blogRecyclerView);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        swipeRefreshLayout.setRefreshing(true);
        mainViewModel.getAllBlog().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                swipeRefreshLayout.setRefreshing(false);

                blogAdapter = new BlogAdapter(blogs);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(blogAdapter);
                blogAdapter.notifyDataSetChanged();

            }
        });

    }
}
