package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
import xyz.ilyaxabibullin.onlinestore.view.product.ProductActivity;

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

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        progressBar = findViewById(R.id.progress_bar);
        initWidgets();
        presenter = new ProductListPresenter(this);

        products = new ArrayList<>();
        //fakeData();

        adapter = new ProductListAdapter(products,this);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener((position, v) -> {
            /*Toast toast = Toast.makeText(ProductListActivity.this, String.valueOf(position), Toast.LENGTH_LONG);
            toast.show();*/
            System.out.println(position);
            Intent intent = new Intent(ProductListActivity.this, ProductActivity.class);
            intent.putExtra("name",products.get(position).getName());
            intent.putExtra("price",String.valueOf(products.get(position).getPrice()) );
            intent.putExtra("description",products.get(position).getDescription());
            intent.putExtra("link",products.get(position).getLink());
            intent.putExtra("number",String.valueOf(products.get(position).getNumber()));
            startActivity(intent);
        });

        rv.addOnScrollListener(new PaginationScrollListener(manager) {

            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1; //Increment page index to load the next one
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        }, 1000);
    }

    private void loadFirstPage() {
        List<Product> movies = Product.Companion.createProducts(adapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        adapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES)
            adapter.addLoadingFooter();
        else isLastPage = true;
    }
    private void loadNextPage(){
        List<Product> products = Product.Companion.createProducts(adapter.getItemCount());
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(products);
        if(currentPage !=TOTAL_PAGES)
            adapter.addLoadingFooter();
        else
            isLastPage = true;
    }

    private void initWidgets(){
        System.out.println("kek");
        mActionToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        rv = findViewById(R.id.rv);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
    }

    private void fakeData() {
        Product product = new Product();
        product.setPrice(99999.9);
        product.setName("Кот с ноутбуком");
        product.setDescription("Очень хороший кот, который пишет отменный код");
        product.setLink("https://sun9-8.userapi.com/c635104/v635104289/24d73/NpaOvn9JMUE.jpg");
        product.setNumber(10);
        products.add(product);
    }
    @Override
    public void showItems(@NotNull List<Product> items) {
        ProductListAdapter adapter = new ProductListAdapter(products,this);
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
