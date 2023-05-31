package com.swatitiwari.tracktofit.fitnessCalculator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.swatitiwari.tracktofit.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CheckSafeSearch extends AppCompatActivity {
	List<SampleItem> msampleItem = new ArrayList<>();
	private EditText food;
	private RecyclerView recyclerView;
	private DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_safe_search);
		food = findViewById(R.id.editText11);
		recyclerView = findViewById(R.id.recyclerview);
		recyclerView.setHasFixedSize(true);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CheckSafeSearch.this);
		recyclerView.setLayoutManager(layoutManager);
		RecyclerView.Adapter adapter = new MainAdapter(msampleItem);
		recyclerView.setAdapter(adapter);
		databaseHelper = new DatabaseHelper(this);
		Cursor res = databaseHelper.getAllData();
		while (res.moveToNext()) {
			msampleItem.add(new SampleItem(res.getString(1)));
		}
		food.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// Toast.makeText(getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
				msampleItem.clear();
				Cursor res = databaseHelper.getAllData();
				while (res.moveToNext()) {
					if (res.getString(1).toLowerCase().indexOf(charSequence.toString().toLowerCase()) >= 0) {
						msampleItem.add(new SampleItem(res.getString(1)));
					}
				}
				//msampleItem.add(new SampleItem("new"));
				recyclerView.setHasFixedSize(true);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CheckSafeSearch.this);
				recyclerView.setLayoutManager(layoutManager);
				RecyclerView.Adapter adapter = new MainAdapter(msampleItem);
				recyclerView.setAdapter(adapter);
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
	}

	private class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

		private List<SampleItem> samples;

		MainAdapter(List<SampleItem> samples) {
			this.samples = samples;
			Log.e("nsp", samples.size() + "");
		}

		@NonNull
		@Override
		public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater
					.from(parent.getContext())
					.inflate(R.layout.item_main_feature, parent, false);

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
			holder.textView.setText(samples.get(position).getFood());
			final String str = holder.textView.getText().toString();
			holder.textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(CheckSafeSearch.this, CheckSafe.class);
					intent.putExtra("Food", str);
					startActivity(intent);
					finish();
				}
			});
		}

		@Override
		public int getItemCount() {
			return samples.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			private TextView textView;

			ViewHolder(View view) {
				super(view);
				textView = view.findViewById(R.id.textView11);
			}
		}
	}
}