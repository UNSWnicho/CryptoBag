package au.edu.unsw.infs3634.cryptobag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    TextView mValue, mChange1h, mChange24h, mChange7d, mMarketCap, mVolume24h;
    ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail Activity");
        Intent intent = getIntent();
        String msg = intent.getStringExtra("key");
        Coin coin = Coin.findCoin(msg);
        Log.d(TAG, "Received Message " + msg);

        Log.d(TAG, "Coin Name: " + coin.getName());

        // Get handle to view elements
        mValue = findViewById(R.id.tvValue);
        mChange1h = findViewById(R.id.tvChange1hr);
        mChange24h = findViewById(R.id.tvChange24h);
        mChange7d = findViewById(R.id.tvChange7d);
        mMarketCap = findViewById(R.id.tvMarketCap);
        mVolume24h = findViewById(R.id.tvVolume24h);
        mSearch = findViewById(R.id.ivSearch);

        // Set the values
        mValue.setText(coin.getPriceUsd());
        mChange1h.setText(coin.getPercentChange1h());
        mChange24h.setText(coin.getPercentChange24h());
        mChange7d.setText(coin.getPercentChange7d());
        mMarketCap.setText(coin.getMarketCapUsd());
        mVolume24h.setText(String.valueOf(coin.getVolume24()));

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=bitcoin" + coin.getName()));
                startActivity(searchIntent);
            }
        });


                    }
                    void playVideo (String url) {
                        Intent impIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=7RzOuX8Kbx0&ab_channel=CSESoc"));
                        startActivity(impIntent);

}
                }

