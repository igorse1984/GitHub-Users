package developer.igorsharov.githubusers

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.ui.core.entity.User

class ActivityViewModel : ViewModel() {
    private val listUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also { loadUsers() }
    }
    private var n = 0

    fun getAllItems(): LiveData<List<User>> = listUsers

    fun updateData() {
        loadUsers()
    }

    private fun loadUsers() {
        //тестовые данные
        Handler().postDelayed({
            val users = mutableListOf<User>()

            for (i in n..n + 20) {
                users.add(User("User $i", "URL$i"))
            }

            n += 20

            listUsers.postValue(users)
        }, 1000)
    }
}