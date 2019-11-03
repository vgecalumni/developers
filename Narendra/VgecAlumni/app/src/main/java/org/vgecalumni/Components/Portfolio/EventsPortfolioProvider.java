package org.vgecalumni.Components.Portfolio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.Model.Portfolio;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.List;

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

                                if (!name.equals("null")) {
                                    portfolioList.add(new Portfolio(image, name, drive_link));
                                }
                            }

                            Activity activity = (Activity) view.getContext();
                            if (!activity.isDestroyed()) {
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
