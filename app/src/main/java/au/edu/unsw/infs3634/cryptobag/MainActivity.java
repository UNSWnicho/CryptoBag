package au.edu.unsw.infs3634.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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

  // Instantiate the menu
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        // Call filterable method from the adapter class to perform filtering
        mAdapter.getFilter().filter(s);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        mAdapter.getFilter().filter(s);
        return false;
      }
    });
    return true;
  }

  // React to user interaction with the menu
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.sortName:
        // Sort the list by name
        mAdapter.sort(1);
        return true;
      case R.id.sortValue:
        // Sort the list by PriceUSD
        mAdapter.sort(2);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  // Called when user taps on a row on the Recycler View
    private void launchDetailActivity(String message) {
      Intent intent = new Intent(MainActivity.this, DetailActivity.class);
      intent.putExtra("key", message);
      startActivity(intent);
    }
    }





