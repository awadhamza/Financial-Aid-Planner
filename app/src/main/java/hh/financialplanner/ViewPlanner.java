package hh.financialplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewPlanner extends AppCompatActivity {

    TextView plannerName;
    TextView plannerInitcap;
    Button eraseButton;

    SharedPreferences storage;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_planner);
        plannerName = findViewById(R.id.planner_name_view);
        plannerInitcap = findViewById(R.id.planner_initcap_view);
        eraseButton = findViewById(R.id.erase_planner_button);
        storage = getSharedPreferences("H&H", MODE_PRIVATE);
        editor = storage.edit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        String temp = storage.getString("nameclicked", "");

        Gson gson = new Gson();
        String json = storage.getString("nameClicked", "");
        final PlannerObject obj = gson.fromJson(json, PlannerObject.class);

        plannerName.setText(obj.getName());
        plannerInitcap.setText(obj.getInitialCapital());

        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<PlannerObject> dummyMode = new ArrayList<>();
                String temp = storage.getString("plannermodel", "");
                Gson gson = new Gson();
                // Get arrayListHolder from storage
                PlannerObjectModel HOLDER = gson.fromJson(temp, PlannerObjectModel.class);
                // Find profile to delete
                List<PlannerObject> tempList = HOLDER.getPlannerObjectList();

                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getName().equals(obj.getName()) && tempList.get(i).getInitialCapital().equals(obj.getInitialCapital())) {
                        tempList.remove(i);
                        HOLDER.setPlannerObjectList(tempList);
                        Log.d("eraseHelp", "YOINKED PLANNER");
                        // Put new usermodel object back into storage
                        Gson gson2 = new Gson();
                        String json = gson2.toJson(HOLDER);
                        editor = storage.edit();
                        editor.putString("plannermodel", json);
                        editor.apply();

                        // Go back home after delete
                        finish();
                    }
                }

                // Go back home after delete
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
