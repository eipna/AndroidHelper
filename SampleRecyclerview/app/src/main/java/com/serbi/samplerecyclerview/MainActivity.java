package com.serbi.samplerecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerviewAdapter adapter;

    ArrayList<RecyclerviewModel> models;

    String[] titles = new String[] {
            "Active", "ATM Card", "Checkbox",
            "Question Mark", "Todo List", "User"
    };

    int[] images = new int[] {
            R.drawable.active, R.drawable.atm_card, R.drawable.checkbox,
            R.drawable.question_mark, R.drawable.to_do_list, R.drawable.user
    };

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

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);

        models = new ArrayList<>();
        adapter = new RecyclerviewAdapter(MainActivity.this, models);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < titles.length; i++) {
            RecyclerviewModel model = new RecyclerviewModel(titles[i], images[i]);
            models.add(model);
        }
        adapter.notifyDataSetChanged();
    }
}