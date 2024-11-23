package com.codinginflow.bigots;

import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private List<SpannableString> items = new ArrayList<>();
    private OnItemClickListener listener;

    // Click listener interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Click listener setter
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void onItemClick(int position) {
        if (listener != null) {
            listener.onItemClick(position);
        }
    }
    // ViewHolder sınıfı
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(android.R.id.text1);
        }

        public void bind(final SpannableString text, final int position, final OnItemClickListener listener) {
            textView.setText(text);

            // Click olayını burada yönetiyoruz
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Verileri güncellemek için
    public void updateData(List<SpannableString> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}
