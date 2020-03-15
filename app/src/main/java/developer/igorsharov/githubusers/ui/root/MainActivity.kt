package developer.igorsharov.githubusers.ui.root

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import developer.igorsharov.githubusers.R
import developer.igorsharov.githubusers.adapter.UsersAdapter
import developer.igorsharov.githubusers.pojo.User
import developer.igorsharov.githubusers.ui.detail.DetailsFragment
import developer.igorsharov.githubusers.ui.PaginationScrollListener
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity(R.layout.main_activity), ProgressBar {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()

        val recyclerAdapter = UsersAdapter().apply {
            setOnClickListener(object : UsersAdapter.OnClickListener {
                override fun onClick(position: Int) {
                    viewModel.onItemClick(position)
                }
            })
        }

        val layoutManager = recycler.layoutManager!! as LinearLayoutManager
        val scrollListener = object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.loadNextData()
            }
        }

        recycler.run {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addOnScrollListener(scrollListener)
        }

        viewModel.run {
            getLoadState().observe(this@MainActivity, Observer {
                scrollListener.setLoadState(it)
            })

            getAllItems().observe(this@MainActivity, Observer {
                recyclerAdapter.setData(it)
            })

            getShowDetail().observe(this@MainActivity, Observer {
                showDetailScreen(it)
            })

            getShowProgress().observe(this@MainActivity, Observer {
                showProgress(it)
            })
        }
    }

    private fun showDetailScreen(user: User) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(user.login))
            .addToBackStack(null)
            .commit()
    }

    override fun showProgress(visibility: Boolean) {
        progressbar.visibility = when (visibility) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}

interface ProgressBar {
    fun showProgress(visibility: Boolean)
}
