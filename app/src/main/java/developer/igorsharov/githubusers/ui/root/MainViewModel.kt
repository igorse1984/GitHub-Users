package developer.igorsharov.githubusers.ui.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import developer.igorsharov.githubusers.pojo.User
import developer.igorsharov.githubusers.model.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val loadState: MutableLiveData<Boolean> = MutableLiveData()
    private val showDetailScreen: MutableLiveData<User> = MutableLiveData()
    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val liveDataUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also { loadUsers() }
    }

    private var lastSince: Int = 0
    private val compositeDisposable = CompositeDisposable()
    private val repository = UserRepository(ApiServiceUsers.create())
    private val users = mutableListOf<User>()

    fun getAllItems(): LiveData<List<User>> = liveDataUsers

    fun getLoadState(): LiveData<Boolean> = loadState

    fun getShowDetail(): LiveData<User> = showDetailScreen

    fun getShowProgress(): LiveData<Boolean> = showProgress

    fun loadNextData() {
        loadUsers()
    }

    fun onItemClick(position: Int) {
        showDetailScreen.value = users[position]
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun loadUsers() {
        compositeDisposable.add(
            repository.getUsers(lastSince)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress.value = true }
                .doFinally {
                    loadState.value = false
                    showProgress.value = false
                }
                .subscribe { users ->
                    this.users.addAll(users)
                    liveDataUsers.value = this.users
                    lastSince = users.last().id
                })
    }
}