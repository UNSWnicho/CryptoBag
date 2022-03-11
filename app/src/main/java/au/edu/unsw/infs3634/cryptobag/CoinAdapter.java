package au.edu.unsw.infs3634.cryptobag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {

    private ArrayList<Coin> mCoins;
    public CoinAdapter(ArrayList<Coin> coins) {
        mCoins = coins;
    }

    @NonNull
    @Override
    public CoinAdapter.CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new CoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.CoinViewHolder holder, int position) {
        Coin coin = mCoins.get(position);
        holder.name.setText(coin.getName());


        holder.value.setText(coin.getPriceUsd());
        holder.change.setText(coin.getPercentChange1h());

    }

    @Override
    public int getItemCount() {
        return mCoins.size();
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
    }
}
