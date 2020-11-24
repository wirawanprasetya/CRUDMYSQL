package com.sigarda.CRUDMySQL.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sigarda.CRUDMySQL.Models.Student;
import com.sigarda.CRUDMySQL.R;

import java.util.ArrayList;
import java.util.List;

public class MhsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Student> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
//    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
//        this.mOnItemClickListener = mItemClickListener;
//    }


    public MhsAdapter(Context context, List<Student> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView nim,name;

        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            nim = (TextView) v.findViewById(R.id.nim);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            final Student p = items.get(position);
            view.name.setText(p.getName());
            view.nim.setText(p.getNim());

        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Student obj, int pos);
    }

}