package org.vgecalumni.Components.Portfolio;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.vgecalumni.Model.Portfolio;
import org.vgecalumni.R;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {

    List<Portfolio> portfolioList;

    public PortfolioAdapter(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    @NonNull
    @Override
    public PortfolioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.portfolio_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(portfolioList.get(i), i);
        viewHolder.setListener();
    }

    @Override
    public int getItemCount() {
        return portfolioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv_portfolio;
        Portfolio portfolio;
        ImageView iv_portfolio;
        TextView tv_portfolio;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_portfolio = itemView.findViewById(R.id.cv_portfolio);
            iv_portfolio = itemView.findViewById(R.id.iv_portfolio);
            tv_portfolio = itemView.findViewById(R.id.tv_portfolio);
        }

        public void setData(Portfolio portfolio, int position) {
            Glide.with(itemView.getContext()).load(portfolio.getEvent_image_url()).placeholder(R.drawable.ic_profolio).error(R.drawable.ic_profolio).transition(DrawableTransitionOptions.withCrossFade()).into(iv_portfolio);
            tv_portfolio.setText(portfolio.getEvent_name());

            this.portfolio = portfolio;
            this.position = position;
        }

        public void setListener() {
            cv_portfolio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.cv_portfolio) {
                if (URLUtil.isValidUrl(portfolio.getDrive_link())) {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(portfolio.getDrive_link())));
                }
            }
        }
    }
}

