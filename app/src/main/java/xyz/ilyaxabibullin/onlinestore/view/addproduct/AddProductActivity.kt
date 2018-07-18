package xyz.ilyaxabibullin.onlinestore.view.addproduct

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_product.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class AddProductActivity : BaseActivity(),AddProductContract.View {

    var presenter: AddProductContract.Presenter = AddProductPresenter(this)

    override fun navigateToProduct() {

    }

    override fun message(message:String) {
        var toast = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        add_product.setOnClickListener{
            presenter.buttonWasClicked(product_name_edit.text.toString(),
                    product_description_edit.text.toString(),
                    Integer.parseInt(amount.text.toString()),
                    product_name_edit.text.toString().toDouble(),
                    category_edit.text.toString())

        }
    }
}
