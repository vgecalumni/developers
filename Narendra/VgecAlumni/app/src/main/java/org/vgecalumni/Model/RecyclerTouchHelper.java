package org.vgecalumni.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import org.vgecalumni.R;


public class RecyclerTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerTouchHelperListener listener;
    private Drawable delete, edit;
    private ColorDrawable colorDrawableRed, colorDrawableGreen;

    public RecyclerTouchHelper(int dragDirs, int swipeDirs, RecyclerTouchHelperListener listener, Context context) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
        colorDrawableRed = new ColorDrawable(Color.parseColor("#FF0000"));
        colorDrawableGreen = new ColorDrawable(Color.parseColor("#2E7D32"));
        delete = ContextCompat.getDrawable(context, R.drawable.ic_delete);
        edit = ContextCompat.getDrawable(context, R.drawable.ic_editwhite);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemview = viewHolder.itemView;
        int margin = (viewHolder.itemView.getHeight() - delete.getIntrinsicHeight()) / 2;

        if (dX > 0) {
            colorDrawableRed.setBounds(itemview.getLeft(), itemview.getTop(), (int) dX, itemview.getBottom());
            delete.setBounds(itemview.getLeft() + margin, itemview.getTop() + margin,
                    itemview.getLeft() + margin + delete.getIntrinsicWidth(), itemview.getBottom() - margin);
            colorDrawableRed.draw(c);
        } else {
            colorDrawableGreen.setBounds(itemview.getRight() + (int) dX, itemview.getTop(), itemview.getRight(), itemview.getBottom());
            edit.setBounds(itemview.getRight() - margin - edit.getIntrinsicWidth(), itemview.getTop() + margin,
                    itemview.getRight() - margin, itemview.getBottom() - margin);
            edit.setLevel(0);
            colorDrawableGreen.draw(c);
        }
        c.save();

        if (dX > 0) {
            c.clipRect(itemview.getLeft(), itemview.getTop(), (int) dX, itemview.getBottom());
            delete.draw(c);
        } else {
            c.clipRect(itemview.getRight() + (int) dX, itemview.getTop(), itemview.getRight(), itemview.getBottom());
            edit.draw(c);
        }
        c.restore();

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public interface RecyclerTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
