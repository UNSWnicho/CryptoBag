package au.edu.unsw.infs3634.cryptobag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> implements Filterable {

    private ArrayList<Coin> mCoins, mCoinsFiltered;
    public CoinAdapter(ArrayList<Coin> coins) {
        mCoins = coins;
        mCoinsFiltered = coins;

    }

    @NonNull
    @Override
    public CoinAdapter.CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new CoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.CoinViewHolder holder, int position) {
        Coin coin = mCoinsFiltered.get(position);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.name.setText(coin.getName());
        holder.value.setText(formatter.format(Double.valueOf(coin.getPriceUsd())));
        holder.change.setText(String.valueOf(coin.getPercentChange1h()) + " %");
        holder.itemView.setTag(coin.getSymbol());


    }

    @Override
    public int getItemCount() {
        return mCoinsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mCoinsFiltered = mCoins;
                } else {
                ArrayList<Coin> filteredList = new ArrayList<>();
                for(Coin coin : mCoins) {
                    if(coin.getName().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(coin);
                    }
                }
                    mCoinsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCoinsFiltered;
                        return filterResults;


            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mCoinsFiltered = (ArrayList<Coin>) filterResults.values;
                notifyDataSetChanged();
            }
        };



    }

    protected static class CoinViewHolder extends RecyclerView.ViewHolder {
        TextView name, value, change;


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get handle for view elements
            name = itemView.findViewById(R.id.tvName);
            value = itemView.findViewById(R.id.tvValue);
            change = itemView.findViewById(R.id.tvChange);

        }
//        @Override
//        public void onClick(View view) { mListener.onClick(view, (String) view.getTag()); }
    }

    // Use the java Comparator Interface and Collections.sort() to sort the list
    public void sort(final int sortMethod) {
        if (mCoinsFiltered.size() > 0) {
            Collections.sort(mCoinsFiltered, new Comparator<Coin>() {
                @Override
                public int compare(Coin o1, Coin o2) {
                    if (sortMethod == 1) {
                        // Sort the list by name
                        return o1.getName().compareTo(o2.getName());
                    } else if(sortMethod == 2) {
                        // Sort the list by value
                        return Double.valueOf(o1.getPriceUsd()).compareTo(Double.valueOf(o2.getPriceUsd()));
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            });

        }
        // Notify the adapter on changes to the list data
        notifyDataSetChanged();
    }
    // To-do
}
