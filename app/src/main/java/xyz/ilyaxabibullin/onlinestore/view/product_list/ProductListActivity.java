package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xyz.ilyaxabibullin.onlinestore.R;
import xyz.ilyaxabibullin.onlinestore.base.PaginationScrollListener;
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product;
import xyz.ilyaxabibullin.onlinestore.view.addproduct.AddProductActivity;
import xyz.ilyaxabibullin.onlinestore.view.cart.CartActivity;
import xyz.ilyaxabibullin.onlinestore.view.order.list.OrdersListActivity;
import xyz.ilyaxabibullin.onlinestore.view.product.ProductActivity;
import xyz.ilyaxabibullin.onlinestore.view.userinfo.UserInfoActivity;

public class ProductListActivity extends AppCompatActivity
        implements ProductListContract.View {

    private static final String TAG = "ProductListActivity";

    boolean itMyProducts = false;

    public boolean isItMyProducts() {
        return itMyProducts;
    }

    @Override
    public void setItMyProducts(boolean itMyProducts) {
        this.itMyProducts = itMyProducts;
        if(itMyProducts){
            addProduct.setVisibility(View.VISIBLE);
        }else{
            addProduct.setVisibility(View.GONE);
        }
    }



    ArrayList<Product> products = new ArrayList<>();
    Toolbar mActionToolbar;

    ProductListContract.Presenter presenter;


    //for recycler
    RecyclerView rv;
    ProductListAdapter adapter;
    LinearLayoutManager manager;


    private DrawerLayout mDrawerLayout;

    //for pagination
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;



    NavigationView navigationView;
    ProgressBar progressBar;
    FloatingActionButton addProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Log.d(TAG, "onCreate start");

        Log.d(TAG, "it my products" + String.valueOf(itMyProducts));
        itMyProducts = false;

        Log.d(TAG, "it my products" + String.valueOf(itMyProducts));


        initWidgets();
        presenter = new ProductListPresenter(this);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    mDrawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case (R.id.nav_profile):
                            Intent intent = new Intent(ProductListActivity.this, UserInfoActivity.class);
                            startActivity(intent);
                            return true;

                        case (R.id.nav_cart):
                            Intent intent2 = new Intent(ProductListActivity.this, CartActivity.class);
                            startActivity(intent2);
                            return true;
                        case(R.id.nav_orders):
                            Intent intent3 = new Intent(ProductListActivity.this, OrdersListActivity.class);
                            startActivity(intent3);
                            return true;
                        default:
                            return true;

                    }
                });





        adapter = new ProductListAdapter(products, this);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> bind(position));

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


        int action = Integer.parseInt(getIntent().getStringExtra("action"));

        Log.d(TAG, "action " + String.valueOf(action));



        presenter.activityStarted(action);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loadFirstPage(products);
                presenter.defaultLoadProducts();
            }
        }, 1000);*/

    }



    private void initWidgets() {

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActionToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionToolbar);
        navigationView = findViewById(R.id.nav_view);
        //progressBar = findViewById(R.id.progress_bar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rv);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        addProduct = findViewById(R.id.add_prod);
        addProduct.setVisibility(View.GONE);

        addProduct.setOnClickListener(v -> {
            navigateToAddProduct();
        });

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
    public void loadNextPage(ArrayList<Product> products) {
        //List<Product> products = Product.Companion.createProducts(adapter.getItemCount());
        this.products = (ArrayList<Product>) products;
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(products);
        if (currentPage != TOTAL_PAGES)
            adapter.addLoadingFooter();
        else
            isLastPage = true;
    }
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void showItems(@NotNull ArrayList<Product> items) {
        this.products = items;
        Collections.sort(products,
                (product, t1) -> product.getId() > t1.getId() ? 1 : (product.getId() < t1.getId()) ? -1 : 0);

        Log.d(TAG,products.toString());
        adapter = new ProductListAdapter(products, this);
        Log.d("ProductListActivity", "items" + items.toString());
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(((position, v) -> bind(position)));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                //this.finish();
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case (R.id.cart):
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
                presenter.searchProducts(newText);
                if (newText.equals("")) {
                    presenter.defaultLoadProducts();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public int getShopOwnerId(){
        return Integer.parseInt(getIntent().getStringExtra("shop_id"));//shitcode
    }

    private void bind(int position) {
        System.out.println(position);
        Intent intent = new Intent(ProductListActivity.this, ProductActivity.class);

        intent.putExtra("product_id", String.valueOf(products.get(position).getId()));
        Log.d(TAG, String.valueOf(products.get(position).getId()));
        startActivity(intent);
    }

    private void navigateToAddProduct() {
        Intent intent = new Intent(this, AddProductActivity.class);
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
