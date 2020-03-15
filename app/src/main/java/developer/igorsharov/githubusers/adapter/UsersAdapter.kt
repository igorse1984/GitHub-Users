package developer.igorsharov.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import developer.igorsharov.githubusers.R
import developer.igorsharov.githubusers.pojo.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    private val listUser = mutableListOf<User>()
    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = UserHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )

        listener?.run { view.setOnClickListener(this) }

        return view
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.setName(listUser[position].login)
        holder.setPhoto(listUser[position].avatar_url)
    }

    fun setData(listUser: List<User>) {
        this.listUser.clear()
        this.listUser.addAll(listUser)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val item: View = itemView.findViewById(R.id.item)
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val userPhoto: ImageView = itemView.findViewById(R.id.user_photo)

        fun setOnClickListener(listener: OnClickListener) {
            item.setOnClickListener { listener.onClick(adapterPosition) }
        }

        fun setName(name: String) {
            userName.text = name
        }

        fun setPhoto(url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.empty_avatar)
                .fit()
                .centerCrop()
                .into(userPhoto)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}