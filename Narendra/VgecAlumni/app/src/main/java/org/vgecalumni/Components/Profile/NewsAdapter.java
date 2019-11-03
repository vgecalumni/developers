package org.vgecalumni.Components.Profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.vgecalumni.Model.News;
import org.vgecalumni.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> newsList;
    Context context;

    public NewsAdapter(List<News> stringList, Context context) {
        this.newsList = stringList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_rec_lay, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News n = newsList.get(position);
        holder.str.setText(n.getDesc());
        holder.date.setText(n.getDate());
        if (n.getLink().isEmpty()) {
            holder.link.setVisibility(View.GONE);
        }/*if(position==newsList.size()-1){
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.bottomMargin = 140;
            holder.itemView.setLayoutParams(params);
        }if(position==0){
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.topMargin = 140;
            holder.itemView.setLayoutParams(params);
        }*/
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView str, date, link;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            str = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            link = itemView.findViewById(R.id.link);
            link.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse(newsList.get(getAdapterPosition()).getLink())));
        }
    }
}

