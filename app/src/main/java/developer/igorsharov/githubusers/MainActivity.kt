package developer.igorsharov.githubusers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import developer.igorsharov.githubusers.ui.core.adapter.UsersAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ActivityViewModel by viewModels()

        val recyclerAdapter = UsersAdapter()
        recycler.adapter = recyclerAdapter
        recycler.setHasFixedSize(true)

        viewModel.getAllItems().observe(this, Observer { userData ->
            recyclerAdapter.setData(userData)
        })

        button.setOnClickListener { viewModel.updateData() }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
    }
}
