package xyz.ilyaxabibullin.onlinestore.view.order


import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem


import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class OrderActivity : BaseActivity() {
    lateinit var mActionToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        mActionToolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionToolbar)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
