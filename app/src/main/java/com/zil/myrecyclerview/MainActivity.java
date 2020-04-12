package com.zil.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String title = "Mode List";
    private RecyclerView rvSinger;
    private ArrayList<singer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvSinger = findViewById(R.id.rv_singer);
        rvSinger.setHasFixedSize(true);

        list.addAll(singerdata.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvSinger.setLayoutManager(new LinearLayoutManager(this));
        ListSingerAdapter listSingerAdapter = new ListSingerAdapter(list);
        rvSinger.setAdapter(listSingerAdapter);

        listSingerAdapter.setOnItemClickCallback(new ListSingerAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(singer data) {
                showSelectedSinger(data);
            }
        });
    }

    private void showRecyclerGrid() {
        rvSinger.setLayoutManager(new GridLayoutManager(this, 2));
        GridSingerAdapter gridSingerAdapter = new GridSingerAdapter(list);
        rvSinger.setAdapter(gridSingerAdapter);

        GridSingerAdapter.setOnItemClickCallback(new ListSingerAdapter.OnItemClickCallback() {

            public void onItemClicked(singer data) {
                showSelectedSinger(data);
            }
        });
    }

    private void showSelectedSinger(singer data) {
    }

    private void showRecyclerCardView(){
        rvSinger.setLayoutManager(new LinearLayoutManager(this));
        CardViewSingerAdapter cardViewSingerAdapter = new CardViewSingerAdapter(list);
        rvSinger.setAdapter(cardViewSingerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }

        setActionBarTitle(title);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(singer singer) {
        Toast.makeText(this, "Kamu memilih " + singer.getName(), Toast.LENGTH_SHORT).show();
    }
}
