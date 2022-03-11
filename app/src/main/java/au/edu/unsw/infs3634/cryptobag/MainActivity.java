package au.edu.unsw.infs3634.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private RecyclerView mRecyclerView;
  private CoinAdapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    // Get handle to the recycler view
    mRecyclerView = findViewById(R.id.rvList);

    // Instantiate the layout manager
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    // Instantiate an adapter object
    mAdapter = new CoinAdapter(Coin.getCoins());

    // Connect the adapter with the RecyclerView
    mRecyclerView.setAdapter(mAdapter);




  }
    void launchDetailActivity(String message){
      Intent intent = new Intent(MainActivity.this, DetailActivity.class);
      intent.putExtra("key", message);
      startActivity(intent);
    }
    }





