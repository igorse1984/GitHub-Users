package developer.igorsharov.githubusers.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import developer.igorsharov.githubusers.AMOUNT_LOAD_USER
import developer.igorsharov.githubusers.PERCENT_LOAD

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    private var loading = false

    fun setLoadState(state: Boolean) {
        loading = state
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        checkScroll()
    }

    private fun checkScroll() {
        if (loading) return

        val lastVisibleItemPos = layoutManager.findLastCompletelyVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (lastVisibleItemPos != RecyclerView.NO_POSITION
            && (totalItemCount - lastVisibleItemPos) < AMOUNT_LOAD_USER * PERCENT_LOAD
        ) {
            loadMoreItems()
            loading = true
        }
    }

    protected abstract fun loadMoreItems()
}