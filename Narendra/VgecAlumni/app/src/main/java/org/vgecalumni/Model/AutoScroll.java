package org.vgecalumni.Model;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class AutoScroll {
    private final android.os.Handler handler;
    private final Runnable runnable;
    private RecyclerView recyclerView;
    private boolean flag = true;
    private int delay = 50;

    public AutoScroll(final RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        handler = new android.os.Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollBy(0, 2);
                handler.postDelayed(this, delay);
            }
        };
        handler.post(runnable);
        setListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int last = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (last >= linearLayoutManager.getItemCount() - 1) {
                    linearLayoutManager.scrollToPosition(0);
                }
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (flag) {
                    handler.removeCallbacks(runnable);
                    flag = false;
                } else {
                    flag = true;
                    handler.postDelayed(runnable, delay);
                }
                return false;
            }
        });
    }
}
