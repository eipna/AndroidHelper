package com.serbi.sampledetailedrecyclerview;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataAdapter adapter;
    DataClass dataClass;
    ArrayList<DataClass> dataList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        dataClass = new DataClass("Camera", R.string.camera, "Java", R.drawable.camera_detail);
        dataList.add(dataClass);

        dataClass = new DataClass("Toggle", R.string.recyclerview, "Kotlin", R.drawable.toggle_detail);
        dataList.add(dataClass);

        dataClass = new DataClass("Date Picker", R.string.date, "Java", R.drawable.date_detail);
        dataList.add(dataClass);

        dataClass = new DataClass("EditText", R.string.edit, "Kotlin", R.drawable.edit_detail);
        dataList.add(dataClass);

        dataClass = new DataClass("Rating Bar", R.string.rating, "Java", R.drawable.rating_detail);
        dataList.add(dataClass);

        adapter = new DataAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text) {
        ArrayList<DataClass> dataClasses = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataClasses.add(data);
            }
        }

        if (dataClasses.isEmpty()) {
            Toast.makeText(this, "No match", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataClasses);
        }
    }
}