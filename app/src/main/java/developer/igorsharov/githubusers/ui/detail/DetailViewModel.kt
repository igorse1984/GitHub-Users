package developer.igorsharov.githubusers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.model.UserRepository
import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repository = UserRepository(ApiServiceUsers.create())
    private lateinit var login: String

    private val urlAvatar: MutableLiveData<String> = MutableLiveData()
    private val nickname: MutableLiveData<String> = MutableLiveData()
    private val link: MutableLiveData<String> = MutableLiveData()
    private val location: MutableLiveData<String> = MutableLiveData()
    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun setData(login: String) {
        if (login.isEmpty()) return

        this.login = login
        loadUser()
    }

    fun getUrlAvatar(): LiveData<String> = urlAvatar

    fun getNickName(): LiveData<String> = nickname

    fun getHtmlUrl(): LiveData<String> = link

    fun getLocation(): LiveData<String> = location

    fun getShowProgress(): LiveData<Boolean> = showProgress

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun loadUser() {
        compositeDisposable.add(
            repository.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress.value = true }
                .doFinally { showProgress.value = false }
                .subscribe { user ->
                    urlAvatar.value = user.avatar_url
                    nickname.value = user.login
                    link.value = user.html_url
                    location.value = user.location
                })
    }
}
