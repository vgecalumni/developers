package org.vgecalumni.Model;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {
    int space;
    Context context;

    public PaddingItemDecoration(Context context) {
        this.context = context;
        space= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,90,context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        boolean isLast = position == state.getItemCount() - 1;
        boolean isFirst = position == 0;
        if (isLast) {
            outRect.bottom = space;
            outRect.top = 0;
        }
        if (isFirst&&state.getItemCount()-1!=0) {
            outRect.top = space;
            if (isLast)
                outRect.bottom = 0;
        }
    }
}
