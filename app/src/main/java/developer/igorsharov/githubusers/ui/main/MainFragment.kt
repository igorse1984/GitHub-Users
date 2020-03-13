package developer.igorsharov.githubusers.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import developer.igorsharov.githubusers.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
