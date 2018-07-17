package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import xyz.ilyaxabibullin.onlinestore.R;
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product;

public class ProductListActivity extends AppCompatActivity
    implements ProductListContract.View{

    ArrayList<Product> users;
    Toolbar mActionToolbar;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mActionToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        rv = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        users = new ArrayList<>();
        fakeData();
        ProductListAdapter adapter = new ProductListAdapter(users);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            Toast toast = Toast.makeText(ProductListActivity.this, String.valueOf(position), Toast.LENGTH_LONG);
            toast.show();
            System.out.println(position);
        });


    }

    private void fakeData() {
        Product user = new Product();
        user.setPrice(17);
        user.setName("lolololo trolololooo");
        user.setDescription("kekekekekekek");
        user.setLink("https://sun9-8.userapi.com/c635104/v635104289/24d73/NpaOvn9JMUE.jpg");
        users.add(user);
    }
    @Override
    public void showItems(@NotNull List<Product> items) {
        ProductListAdapter adapter = new ProductListAdapter(users);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (android.R.id.home):
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return true;
            }
        });
        return true;
    }


}
