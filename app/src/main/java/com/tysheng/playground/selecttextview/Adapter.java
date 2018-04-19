package com.tysheng.playground.selecttextview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tysheng.playground.R;

/**
 * Created by tysheng
 * Date: 22/9/17 2:06 PM.
 * Email: tyshengsx@gmail.com
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    public Adapter(SelectTextActivity activity) {
        mActivity = activity;
    }

    SelectTextActivity mActivity;
    RecyclerView rv;

    public void setRV(RecyclerView rv) {
        this.rv = rv;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new Holder(viewItem);
    }

    private static final String TAG = "sty";

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.init();
        holder.mSelectableTextView.setLongClickListener(new SelectableTextView.LongClickListener() {
            @Override
            public void onLongClick(int adapterPosition) {
//                if (pos != -1 && pos != adapterPosition) {
//                    mActivity.show();
//                    clear();
//                    pos = adapterPosition;
//                } else {
//
//                }
                clear();
                pos = adapterPosition;
                mActivity.show();
            }
        });
    }

    public int getPos() {
        return pos;
    }

    private int pos = -1;

    @Override
    public void onViewRecycled(Holder holder) {
        super.onViewRecycled(holder);
        holder.reset();
        Log.d(TAG, "onViewRecycled: ");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public void clear() {
        Holder holder = (Holder) rv.findViewHolderForAdapterPosition(getPos());
        if (holder != null) {
            holder.mSelectableTextView.clear();
        }

    }

    public void move( int num) {
        Holder holder = (Holder) rv.findViewHolderForAdapterPosition(getPos());
        if (holder != null) {
            holder.mSelectableTextView.move(num);
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        SelectableTextView mSelectableTextView;

        public Holder(View itemView) {
            super(itemView);
            mSelectableTextView = (SelectableTextView) itemView.findViewById(R.id.text);
        }

        public void init() {
            mSelectableTextView.setPositionInAdapter(getLayoutPosition());
        }

        public void reset() {
            mSelectableTextView.reset();
        }
    }


}
