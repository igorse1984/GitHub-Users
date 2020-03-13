package developer.igorsharov.githubusers.ui.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import developer.igorsharov.githubusers.R
import developer.igorsharov.githubusers.ui.core.entity.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    private val listUser = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.setName(listUser[position].userName)
        holder.setPhoto(listUser[position].urlImage)
    }

    fun setData(listUser: List<User>) {
        this.listUser.clear()
        this.listUser.addAll(listUser)
        notifyDataSetChanged()
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val userPhoto: TextView = itemView.findViewById(R.id.user_photo)

        fun setName(name: String) {
            userName.text = name
        }

        fun setPhoto(url: String) {
            userPhoto.text = url
        }
    }
}