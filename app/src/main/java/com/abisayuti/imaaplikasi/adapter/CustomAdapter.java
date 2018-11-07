package com.abisayuti.imaaplikasi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abisayuti.imaaplikasi.BuildConfig;
import com.abisayuti.imaaplikasi.R;
import com.abisayuti.imaaplikasi.activity.DetailActivity;
import com.abisayuti.imaaplikasi.model.ResultsItem;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<ResultsItem> data;
    LayoutInflater inflater;


    public CustomAdapter(Context mainActivity, List<ResultsItem> dataHasil) {

        this.context = mainActivity;
        this.data = dataHasil;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        final String title = data.get(i).getTitle();
        final String image = BuildConfig.URL_GAMBARKU + data.get(i).getPosterPath();
        holder.txtJudul.setText(title);
        Glide.with(context)
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultsItem data = new ResultsItem();
                data.setTitle(title);
                data.setPosterPath(image);
                Intent pass = new Intent(context, DetailActivity.class);
                pass.putExtra(DetailActivity.EXTRA_OBJECT, data);
                context.startActivity(pass);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPoster)
        ImageView imgPoster;
        @BindView(R.id.txtJudul)
        TextView txtJudul;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
