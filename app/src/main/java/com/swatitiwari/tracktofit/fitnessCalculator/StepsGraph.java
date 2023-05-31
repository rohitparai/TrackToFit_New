package com.swatitiwari.tracktofit.fitnessCalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.swatitiwari.tracktofit.R;

import java.util.ArrayList;

import kotlin.Unit;

public class StepsGraph extends AppCompatActivity {

	private String MOB_NUMBER;
	CircularProgressBar circularProgressBar;
	Button reset;
	int stepcount =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steps_graph);

		MOB_NUMBER = getIntent().getStringExtra("MOB_NUMBER");
		stepcount = getIntent().getIntExtra("STEPS",0);

		Log.e("TAG", "onCreate: "+stepcount );
		reset =findViewById(R.id.reset);
		circularProgressBar =findViewById(R.id.circularProgressBar);
		circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);

		circularProgressBar.setOnIndeterminateModeChangeListener(isEnable -> {
			// Do something
			return Unit.INSTANCE;
		});

		circularProgressBar.setOnProgressChangeListener(progress -> {
			// Do something
			return Unit.INSTANCE;
		});
		progressBarSetting(stepcount);
		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/" + MOB_NUMBER);
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				Users user = dataSnapshot.getValue(Users.class);
				ArrayList<Entry> entries = new ArrayList<>();
				assert user != null;
				ArrayList<Integer> data = user.getSteps();
				for (int i = 1; i < data.size(); i++) {
					entries.add(new Entry(i - 1, data.get(i)));
				}
				if (entries.size() >= 2) {
					LineDataSet dataSet = new LineDataSet(entries, "Steps taken values");
					dataSet.setColor(ContextCompat.getColor(StepsGraph.this, R.color.colorPrimary));
					dataSet.setValueTextColor(ContextCompat.getColor(StepsGraph.this, R.color.colorPrimaryDark));

					com.github.mikephil.charting.charts.LineChart chart = findViewById(R.id.chart);

					//****
					// Controlling X axis
					XAxis xAxis = chart.getXAxis();
					// Set the xAxis position to bottom. Default is top
					xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
					//Customizing x axis value
					final String[] months = new String[data.size() - 1];
					for (int i = 0; i < months.length; i++) {
						months[i] = "Day " + i;
					}
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Toast.makeText(StepsGraph.this, "Sorry, attempt failed!", Toast.LENGTH_LONG).show();
			}
		});
	}

	void progressBarSetting(int stepcounts){
		circularProgressBar.setProgressWithAnimation(stepcounts, (long) 50);
		circularProgressBar.setProgressMax(5000f);
		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				stepcount=0;
				Log.e("66", "onClick: ");
			}
		});
	}
}
