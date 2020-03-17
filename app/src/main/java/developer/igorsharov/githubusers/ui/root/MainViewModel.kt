package developer.igorsharov.githubusers.ui.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.App
import developer.igorsharov.githubusers.model.UserRepository
import developer.igorsharov.githubusers.pojo.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var repository: UserRepository

    private val _loadState: MutableLiveData<Boolean> = MutableLiveData()
    private val _userData: MutableLiveData<User> = MutableLiveData()
    private val _progressState: MutableLiveData<Boolean> = MutableLiveData()
    private val _usersData: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also { loadUsers() }
    }
    private var lastSince: Int = 0
    private val compositeDisposable = CompositeDisposable()
    private val users = mutableListOf<User>()

    init {
        App.appComponent.inject(this)
    }

    val usersData: LiveData<List<User>>
        get() = _usersData

    val loadState: LiveData<Boolean>
        get() = _loadState

    val userData: LiveData<User>
        get() = _userData

    val progressState: LiveData<Boolean>
        get() = _progressState

    fun loadNextData() {
        loadUsers()
    }

    fun onItemClick(position: Int) {
        _userData.value = users[position]
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
                .doOnSubscribe { _progressState.value = true }
                .doFinally {
                    _loadState.value = false
                    _progressState.value = false
                }
                .subscribe { users ->
                    this.users.addAll(users)
                    _usersData.value = this.users
                    lastSince = users.last().id
                })
    }
}