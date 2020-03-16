package developer.igorsharov.githubusers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.model.UserRepository
import developer.igorsharov.githubusers.pojo.User
import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repository = UserRepository(ApiServiceUsers.create())
    private lateinit var login: String

    private val _user = MutableLiveData<User>()
    private val _progress = MutableLiveData<Boolean>()

    val user: LiveData<User>
        get() = _user

    val progress: LiveData<Boolean>
        get() = _progress

    fun setData(login: String) {
        if (login.isEmpty()) return

        this.login = login
        loadUser()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun loadUser() {
        compositeDisposable.add(
            repository.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _progress.value = true }
                .doFinally { _progress.value = false }
                .subscribe { user ->
                    _user.value = User(
                        0,
                        user.login,
                        user.avatar_url,
                        user.html_url,
                        user.location
                    )
                })
    }
}
