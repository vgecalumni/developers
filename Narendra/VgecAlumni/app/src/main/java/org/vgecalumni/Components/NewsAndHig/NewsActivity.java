package org.vgecalumni.Components.NewsAndHig;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Components.Profile.NewsAdapter;
import org.vgecalumni.Model.AutoScroll;
import org.vgecalumni.Model.News;
import org.vgecalumni.Model.NewsResponse;
import org.vgecalumni.Model.PaddingItemDecoration;
import org.vgecalumni.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    RecyclerView rec_event, rec_notice, rec_imp, rec_mag, rec_meeting;
    CardView cr_event, cr_notice, cr_imp, cr_mag, cr_meeting;
    NewsAdapter newsAdapter, newsAdapter1, newsAdapter2, newsAdapter3, newsAdapter4;
    List<RecyclerView> recyclerViewList = new ArrayList<>();
    List<News> list = new ArrayList<>();
    List<News> list1 = new ArrayList<>();
    List<News> list2 = new ArrayList<>();
    List<News> list3 = new ArrayList<>();
    List<News> list4 = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main_activity);

        setTitle(R.string.hint_news);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progress);

        rec_event = findViewById(R.id.rec_event_news);
        rec_notice = findViewById(R.id.rec_notice_news);
        rec_imp = findViewById(R.id.rec_imp_news);
        rec_mag = findViewById(R.id.rec_mag_news);
        rec_meeting = findViewById(R.id.rec_meeting_news);

        cr_event = findViewById(R.id.card_event);
        cr_notice = findViewById(R.id.card_notice);
        cr_imp = findViewById(R.id.card_imp);
        cr_mag = findViewById(R.id.card_mag);
        cr_meeting = findViewById(R.id.card_meeting);

        recyclerViewList.add(rec_event);
        recyclerViewList.add(rec_notice);
        recyclerViewList.add(rec_imp);
        recyclerViewList.add(rec_mag);
        recyclerViewList.add(rec_meeting);

        setRecAdp();
    }

    private void setRecAdp() {
        setProgress();
        for (int i = 0; i < recyclerViewList.size(); i++) {
            recyclerViewList.get(i).setLayoutManager(new LinearLayoutManager(this));
            recyclerViewList.get(i).addItemDecoration(new PaddingItemDecoration(this));
            recyclerViewList.get(i).addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

        Call<NewsResponse> responseCall = RetrofitClient.getInstance()
                .getInterPreter().getNews();
        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse response1 = response.body();
                List<News> news = response1.getNews();
                Iterator<News> in = news.iterator();
                while (in.hasNext()) {
                    News n = in.next();
                    switch (n.getCategory()) {
                        case 0:
                            list.add(n);
                            break;
                        case 1:
                            list1.add(n);
                            break;
                        case 2:
                            list2.add(n);
                            break;
                        case 3:
                            list3.add(n);
                            break;
                        case 4:
                            list4.add(n);
                            break;
                    }
                }

                if (list.size() != 0) {
                    newsAdapter = new NewsAdapter(list, NewsActivity.this);
                    rec_imp.setAdapter(newsAdapter);
                    new AutoScroll(rec_imp);
                    cr_imp.setVisibility(View.VISIBLE);
                }
                if (list1.size() != 0) {
                    newsAdapter1 = new NewsAdapter(list1, NewsActivity.this);
                    rec_event.setAdapter(newsAdapter1);
                    new AutoScroll(rec_event);
                    cr_event.setVisibility(View.VISIBLE);
                }
                if (list2.size() != 0) {
                    newsAdapter2 = new NewsAdapter(list2, NewsActivity.this);
                    rec_notice.setAdapter(newsAdapter2);
                    new AutoScroll(rec_notice);
                    cr_notice.setVisibility(View.VISIBLE);
                }
                if (list3.size() != 0) {
                    newsAdapter3 = new NewsAdapter(list3, NewsActivity.this);
                    rec_mag.setAdapter(newsAdapter3);
                    new AutoScroll(rec_mag);
                    cr_mag.setVisibility(View.VISIBLE);
                }
                if (list4.size() != 0) {
                    newsAdapter4 = new NewsAdapter(list4, NewsActivity.this);
                    rec_meeting.setAdapter(newsAdapter4);
                    new AutoScroll(rec_meeting);
                    cr_meeting.setVisibility(View.VISIBLE);
                }
                unsetProgress();
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                unsetProgress();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setProgress() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void unsetProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
