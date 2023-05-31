package com.swatitiwari.tracktofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swatitiwari.tracktofit.Pojo.BucketListEntry;
import com.swatitiwari.tracktofit.R;

public class BucketListEntryAdapter extends RecyclerView.Adapter<BucketListEntryAdapter.BucketListEntryViewHolder> {

    BucketListEntry[] entries;
    static Context context;

    public BucketListEntryAdapter(BucketListEntry[] entries,Context context) {
        this.entries = entries;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return entries.length;
    }

    @NonNull
    @Override
    public BucketListEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bucket_list,parent,false);
        return new BucketListEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketListEntryViewHolder holder, int position) {
     holder.bind(entries[position],position );
    }


    static class BucketListEntryViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public  TextView headertext;
        public TextView descriptionText;
        public RatingBar ratingBar;

        public BucketListEntryViewHolder(@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.cerelac_image);
            headertext=itemView.findViewById(R.id.text_view_heading);
            descriptionText=itemView.findViewById(R.id.text_view_description);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }


        public void bind(BucketListEntry entry, int position){
            image.setImageResource(entry.image);
            String headerString = position + 1 +". "+entry.heading;
            headertext.setText(headerString);
            descriptionText.setText(entry.description);
            ratingBar.setRating(entry.rating);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, entry.description, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
