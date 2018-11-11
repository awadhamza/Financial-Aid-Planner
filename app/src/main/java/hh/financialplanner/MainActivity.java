package hh.financialplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mainTitle;
    Button createPlanner;
    RecyclerView plannerRecycler;

    PlannerAdapter p;
    SharedPreferences storage;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTitle = findViewById(R.id.main_title);
        createPlanner = findViewById(R.id.create_planner_button);
        //plannerRecycler
        plannerRecycler = findViewById(R.id.planner_recycler);
        plannerRecycler.setLayoutManager(new LinearLayoutManager(this));

        storage = getSharedPreferences("H&H", MODE_PRIVATE);
        editor = storage.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();


        String temp = storage.getString("plannermodel", "");
        Gson gson = new Gson();
        final PlannerObjectModel HOLDER = gson.fromJson(temp, PlannerObjectModel.class);

        if(HOLDER == null || HOLDER.getPlannerObjectList() == null){
            //If model, or model's list doesn't exist
            Gson gson1 = new Gson();
            String json = gson1.toJson(HOLDER);
            editor.putString("usermodel", json);
            p = new PlannerAdapter(this, null);

            plannerRecycler.setAdapter(p);

        }
        else if(HOLDER.getPlannerObjectList().size() > 0){
            //If model's list has a planner
            String current;
            ArrayList<String> plannerNames = new ArrayList<>();
            for (int i = 0; i < HOLDER.getPlannerObjectList().size(); i++) {
                current = HOLDER.getPlannerObjectList().get(i).getName();
                plannerNames.add(current);
            }

            p = new PlannerAdapter(this, plannerNames);

            plannerRecycler.setAdapter(p);
            p.setClickListener(new PlannerAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(MainActivity.this, "I clicked at position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            p = new PlannerAdapter(this, null);

            plannerRecycler.setAdapter(p);
        }

        editor.apply();

        createPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCreatePlanner = new Intent(MainActivity.this, CreatePlanner.class);
                startActivity(goToCreatePlanner);
            }
        });

    }
}
