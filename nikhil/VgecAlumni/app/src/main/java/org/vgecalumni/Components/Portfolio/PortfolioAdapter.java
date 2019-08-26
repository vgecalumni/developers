package org.vgecalumni.Components.Portfolio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.R;

import java.util.ArrayList;
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
            Glide.with(itemView.getContext()).load(portfolio.getEvent_image_url()).placeholder(R.drawable.event).error(R.drawable.event).transition(DrawableTransitionOptions.withCrossFade()).into(iv_portfolio);
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

            if(id == R.id.cv_portfolio) {
                if(URLUtil.isValidUrl(portfolio.getDrive_link())) {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(portfolio.getDrive_link())));
                }
            }
        }
    }
}

class Portfolio {

    String event_image_url, event_name, drive_link;

    public Portfolio(String event_image_url, String event_name, String drive_link) {
        this.event_image_url = event_image_url;
        this.event_name = event_name;
        this.drive_link = drive_link;
    }

    public String getEvent_image_url() {
        return event_image_url;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDrive_link() {
        return drive_link;
    }
}

class EventsPortfolioProvider {

    public void getEventList(final View view) {

        final ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shmr_protfolio);
        shimmerFrameLayout.startShimmer();

        final List<Portfolio> portfolioList = new ArrayList<>();
        String url = "https://admin.vgecalumni.org/android/getEventsPortfolio.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject item = array.getJSONObject(i);

                                String image = item.getString("e_photo");
                                String name = item.getString("e_name");
                                String drive_link = item.getString("e_photo_drive_link");

                                if(!name.equals("null")) {
                                    portfolioList.add(new Portfolio(image, name, drive_link));
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if(!activity.isDestroyed()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                RecyclerView rv_portfolio = view.findViewById(R.id.rv_portfolio);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_portfolio.setLayoutManager(layoutManager);

                                PortfolioAdapter portfolioAdapter = new PortfolioAdapter(portfolioList);
                                rv_portfolio.setAdapter(portfolioAdapter);
                                rv_portfolio.setItemAnimator(new DefaultItemAnimator());
                                rv_portfolio.scheduleLayoutAnimation();
                            }
                        } catch (JSONException e) {

                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("No Internet Connection")
                                    .setMessage("Please check your internet connectivity and try again")
                                    .setCancelable(false)
                                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            getEventList(view);
                                        }
                                    })
                                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Activity activity = (Activity) view.getContext();
                                            activity.finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(view.getContext())
                                .setTitle("No Internet Connection")
                                .setMessage("Please check your internet connectivity and try again")
                                .setCancelable(false)
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        getEventList(view);
                                    }
                                })
                                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        Activity activity = (Activity) view.getContext();
                                        activity.finish();
                                    }
                                })
                                .show();
                    }
                });

        Volley.newRequestQueue(view.getContext()).add(stringRequest);
    }
}

class AlumniPortfolioProvider {

    public void getAlumniList(final View view) {

        final ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shmr_protfolio);
        shimmerFrameLayout.startShimmer();

        final List<Portfolio> portfolioList = new ArrayList<>();
        String url = "https://admin.vgecalumni.org/android/getAlumniPortfolio.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject item = array.getJSONObject(i);

                                String image = item.getString("a_photo");
                                String name = item.getString("a_passing_year");
                                String drive_link = item.getString("a_photo_drive_link");

                                if(!name.equals("null")) {
                                    portfolioList.add(new Portfolio(image, name, drive_link));
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if(!activity.isDestroyed()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                if(portfolioList.isEmpty()) {
                                    TextView tv_portfolio_soon = view.findViewById(R.id.tv_portfolio_soon);
                                    tv_portfolio_soon.setVisibility(View.VISIBLE);

                                    RecyclerView rv_portfolio = view.findViewById(R.id.rv_portfolio);
                                    rv_portfolio.setVisibility(View.GONE);
                                } else {
                                    RecyclerView rv_portfolio = view.findViewById(R.id.rv_portfolio);

                                    LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    rv_portfolio.setLayoutManager(layoutManager);

                                    PortfolioAdapter portfolioAdapter = new PortfolioAdapter(portfolioList);
                                    rv_portfolio.setAdapter(portfolioAdapter);
                                    rv_portfolio.setItemAnimator(new DefaultItemAnimator());
                                    rv_portfolio.scheduleLayoutAnimation();
                                }
                            }
                        } catch (JSONException e) {

                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("No Internet Connection")
                                    .setMessage("Please check your internet connectivity and try again")
                                    .setCancelable(false)
                                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            getAlumniList(view);
                                        }
                                    })
                                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Activity activity = (Activity) view.getContext();
                                            activity.finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(view.getContext())
                                .setTitle("No Internet Connection")
                                .setMessage("Please check your internet connectivity and try again")
                                .setCancelable(false)
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        getAlumniList(view);
                                    }
                                })
                                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        Activity activity = (Activity) view.getContext();
                                        activity.finish();
                                    }
                                })
                                .show();
                    }
                });

        Volley.newRequestQueue(view.getContext()).add(stringRequest);
    }
}