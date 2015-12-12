package com.rambabusaravanan.texticon.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rambabusaravanan.TextIcon;

import java.util.ArrayList;

/**
 * Created by Andro Babu on Dec 12, 2015.
 */
public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private ArrayList<Model> dataSet, originalSet;
    private String searchText;

    public IconAdapter(Context context, ArrayList<Model> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.originalSet = dataSet;
    }

    @Override
    public IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item, null, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(IconAdapter.ViewHolder holder, int position) {
        holder.icon.setText(dataSet.get(position).icon);

        if(searchText==null) searchText="";
        int idx1 = dataSet.get(position).text.toLowerCase().indexOf(searchText);
        int idx2 = idx1 + searchText.length();

        if (idx1 >= 0) {
            Spannable spannableString = new SpannableString(dataSet.get(position).text);
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), idx1, idx2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new BackgroundColorSpan(context.getResources().getColor(R.color.colorPrimaryDark)), idx1, idx2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.text.setText(spannableString);
        } else {
            holder.text.setText(dataSet.get(position).text);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        return new IconFilter(this, originalSet);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextIcon icon;

        public ViewHolder(View row) {
            super(row);
            text = (TextView) row.findViewById(R.id.text);
            icon = (TextIcon) row.findViewById(R.id.icon);
        }
    }

    private class IconFilter extends Filter {
        private final ArrayList<Model> originalList;
        private ArrayList<Model> filteredList;
        private IconAdapter adapter;

        public IconFilter(IconAdapter adapter, ArrayList<Model> originalList) {
            this.adapter = adapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            adapter.searchText = constraint.toString().toLowerCase().trim();
            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                for (final Model model : originalList) {
                    if (model.text.contains(adapter.searchText)) {
                        filteredList.add(model);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.dataSet = (ArrayList<Model>) results.values;
            adapter.notifyDataSetChanged();
        }
    }
}
