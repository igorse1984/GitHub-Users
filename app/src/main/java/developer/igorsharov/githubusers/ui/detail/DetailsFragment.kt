package developer.igorsharov.githubusers.ui.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import developer.igorsharov.githubusers.R
import developer.igorsharov.githubusers.getClickableUrlText
import developer.igorsharov.githubusers.ui.pojo.User
import developer.igorsharov.githubusers.ui.root.ProgressBar
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailsFragment : Fragment(R.layout.detail_fragment) {
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        private const val USER_NAME = "user_name"

        fun newInstance(userName: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(USER_NAME, userName)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener { activity?.onBackPressed() }

        viewModel.run {
            setData(arguments?.getString(USER_NAME) ?: "")

            user.observe(viewLifecycleOwner, Observer { setUser(it) })

            progress.observe(viewLifecycleOwner, Observer {
                (activity as ProgressBar).showProgress(it)
            })
        }
    }

    private fun setUser(user: User) {
        Picasso.get()
            .load(user.avatarUrl)
            .fit()
            .centerCrop()
            .into(user_image)

        user_image_layout.visibility = View.VISIBLE
        user_name.text = user.username

        link.run {
            text = getClickableUrlText(requireActivity(), user.repositoryUrl)
            movementMethod = LinkMovementMethod.getInstance()
        }

        location.text = user.location
    }
}
