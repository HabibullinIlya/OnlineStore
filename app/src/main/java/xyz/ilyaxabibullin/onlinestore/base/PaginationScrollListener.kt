package xyz.ilyaxabibullin.onlinestore.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class PaginationScrollListener(var layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var visibleItemCount = layoutManager.childCount
        var totalItemCount = layoutManager.itemCount
        var firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if(!isLoading()&&!isLastPage()){
            if((visibleItemCount+firstVisibleItemPosition)>=totalItemCount&&firstVisibleItemPosition>=0){
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    abstract fun getTotalPageCount(): Int
}