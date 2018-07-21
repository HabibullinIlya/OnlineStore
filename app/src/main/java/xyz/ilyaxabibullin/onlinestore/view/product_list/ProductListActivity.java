package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import xyz.ilyaxabibullin.onlinestore.R;
import xyz.ilyaxabibullin.onlinestore.base.PaginationScrollListener;
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product;
import xyz.ilyaxabibullin.onlinestore.view.cart.CartActivity;
import xyz.ilyaxabibullin.onlinestore.view.product.ProductActivity;
import xyz.ilyaxabibullin.onlinestore.view.userinfo.UserInfoActivity;

public class ProductListActivity extends AppCompatActivity
    implements ProductListContract.View{

    private static final String TAG = "ProductListActivity";

    ArrayList<Product> products;
    Toolbar mActionToolbar;

    ProductListContract.Presenter presenter;

    RecyclerView rv;
    ProductListAdapter adapter;
    LinearLayoutManager manager;
    ProgressBar progressBar;
    private DrawerLayout mDrawerLayout;


    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;
    NavigationView navigationView;

    private int startAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        //progressBar = findViewById(R.id.progress_bar);
        initWidgets();

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    mDrawerLayout.closeDrawers();

                    switch (menuItem.getItemId()){
                        case (R.id.nav_profile):
                            Intent intent = new Intent(ProductListActivity.this, UserInfoActivity.class);
                            startActivity(intent);
                            return true;

                        case(R.id.nav_cart):
                            Intent intent2 = new Intent(ProductListActivity.this, CartActivity.class);
                            startActivity(intent2);
                            return true;
                        default:
                            return true;

                    }

                });

        presenter = new ProductListPresenter(this);

        products = new ArrayList<>();
        //fakeData();

        adapter = new ProductListAdapter(products,this);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener((position, v) -> {
            /*Toast toast = Toast.makeText(ProductListActivity.this, String.valueOf(position), Toast.LENGTH_LONG);
            toast.show();*/
            bind(position);

        });


        rv.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1; //Increment page index to load the next one
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        presenter.defaultLoadProducts();
                    }
                }, 1000);
            }
            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        //presenter.defaultLoadProducts();
        int action = Integer.parseInt(getIntent().getStringExtra("action"));
        presenter.activityStarted(action);
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loadFirstPage(products);
                presenter.defaultLoadProducts();
            }
        }, 1000);*/

    }

    private void loadFirstPage(List<Product> products) {
        //List<Product> movies = Product.Companion.createProducts(adapter.getItemCount());
       // progressBar.setVisibility(View.GONE);
        this.products = (ArrayList<Product>) products;
        adapter.addAll(products);

        if (currentPage <= TOTAL_PAGES)
            adapter.addLoadingFooter();
        else isLastPage = true;
    }
    @Override
    public void loadNextPage(ArrayList<Product> products){
        //List<Product> products = Product.Companion.createProducts(adapter.getItemCount());
        this.products = (ArrayList<Product>) products;
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(products);
        if(currentPage !=TOTAL_PAGES)
            adapter.addLoadingFooter();
        else
            isLastPage = true;
    }

    private void initWidgets(){

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActionToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionToolbar);

        navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rv);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
    }



    @Override
    public void showItems(@NotNull ArrayList<Product> items) {
        this.products = items;
        adapter = new ProductListAdapter(products,this);
        Log.d("ProductListActivity","items"+items.toString());
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(((position, v) -> {
            bind(position);
        }));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (android.R.id.home):
                //this.finish();
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case(R.id.cart):
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
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

    private void bind(int position){
        System.out.println(position);
        Intent intent = new Intent(ProductListActivity.this, ProductActivity.class);
        intent.putExtra("name",products.get(position).getName());
        intent.putExtra("price",String.valueOf(products.get(position).getPrice()) );
        intent.putExtra("description",products.get(position).getDescription());
        //intent.putExtra("link",products.get(position).getLink());
        intent.putExtra("id",String.valueOf(products.get(position).getId()));
        intent.putExtra("number",String.valueOf(products.get(position).getNumber()));
        startActivity(intent);
    }

    private void fakeData() {
        Product product = new Product();
        product.setPrice(99999.9);
        product.setName("Кот с ноутбуком");
        product.setDescription("Очень хороший кот, который пишет отменный код");
        // product.setLink("https://sun9-8.userapi.com/c635104/v635104289/24d73/NpaOvn9JMUE.jpg");
        product.setNumber(10);
        products.add(product);
    }
}
