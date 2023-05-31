package com.swatitiwari.tracktofit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.swatitiwari.tracktofit.Adapter.BucketListEntryAdapter;
import com.swatitiwari.tracktofit.Pojo.BucketListEntry;
import com.swatitiwari.tracktofit.R;


public class NonVegFood extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_non_veg_food, container, false);
        BucketListEntry[] pre_school_children={
                new BucketListEntry("Poha with Sprouts and Spinach", " Energy - (84kcal of RDA of 1666kcal) Carbohydrate- (12gm of RDA of 160gm)  Total Fat-(3gm of RDA of 32gm) ",R.drawable.poha,3.5f),
                new BucketListEntry("Orange Sweet Lime Juice with Sugar", " Energy - 5 % Carbohydrate- 8 % Protein - 5 % Total Fat - 1% ",R.drawable.sweer_lime,4.5f),

                new BucketListEntry("Panner Johar Roti With Oil", " Energy - 12% Carbohydrate- 10%  Total Fat-16% Protein - 19% ",R.drawable.panner_johar_roti,4.0f),
                new BucketListEntry("Beet Root Carrot Raita", "  Energy - 12% Carbohydrate- 10%  Total Fat-16% Protein - 19%  ",R.drawable.beet_root_raita,4.5f),
                new BucketListEntry("Carrot Raita With Cow Milk Curd", " Energy - 11% Carbohydrate- 7%  Total Fat-21% Protein - 17% ",R.drawable.carrot_raita,4.0f),
                new BucketListEntry("Corn Rice Besan Chilla", "  Energy - 12% Carbohydrate- 10%  Total Fat-16% Protein - 19%  ",R.drawable.besan_chilla,4.5f)

        };
        RecyclerView recyclerView =view.findViewById(R.id.recycler_view_non_veg);
        BucketListEntryAdapter adapter = new BucketListEntryAdapter(pre_school_children,getActivity());
        recyclerView.setAdapter(adapter);
        return view;

    }
}