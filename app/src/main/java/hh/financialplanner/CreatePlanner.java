package hh.financialplanner;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreatePlanner extends AppCompatActivity {
    Button SavePlanner;
    EditText PlannerNameEdit;
    EditText PlannerInitcapEdit;

    SharedPreferences storage;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_planner);
        SavePlanner = findViewById(R.id.save_planner_button);
        PlannerNameEdit = findViewById(R.id.planner_name_edit);
        PlannerInitcapEdit = findViewById(R.id.planner_initcap_edit);


        storage = getSharedPreferences("H&H", MODE_PRIVATE);
        editor = storage.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();


        SavePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save info into a new Planner Obj, json to gson

                PlannerObject plannerObj = new PlannerObject(PlannerNameEdit.getText().toString(), PlannerInitcapEdit.getText().toString());

                if (!storage.contains("plannermodel")){
                    String temp;
                    ArrayList<PlannerObject> tempObj = new ArrayList<>();
                    PlannerObjectModel obj = new PlannerObjectModel(tempObj);
                    tempObj.add(plannerObj);

                    Gson gson = new Gson();
                    String json = gson.toJson(new PlannerObjectModel(tempObj));
                    editor.putString("plannermodel", json);
                    editor.commit();

                    finish();

                }
                else {
                    String temp = storage.getString("plannermodel", "");
                    Gson gson = new Gson();
                    PlannerObjectModel obj = gson.fromJson(temp, PlannerObjectModel.class);
                    List<PlannerObject> tempObj = obj.getPlannerObjectList();
                    tempObj.add(plannerObj);

                    Gson gson22 = new Gson();
                    String json = gson22.toJson(new PlannerObjectModel(tempObj));
                    editor.putString("plannermodel", json);
                    editor.commit();

                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
