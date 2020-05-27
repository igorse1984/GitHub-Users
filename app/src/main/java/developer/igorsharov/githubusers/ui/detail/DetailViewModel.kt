package developer.igorsharov.githubusers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.igorsharov.githubusers.App
import developer.igorsharov.githubusers.ui.pojo.User
import developer.igorsharov.githubusers.ui.pojo.mapToPresentation
import igor.sharov.usecases.GetUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel : ViewModel() {
    @Inject
    lateinit var usersUseCase: GetUsersUseCase

    private val _user = MutableLiveData<User>()
    private val _progress = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    init {
        App.appComponent.inject(this)
    }

    val user: LiveData<User>
        get() = _user

    val progress: LiveData<Boolean>
        get() = _progress

    fun setData(login: String) {
        if (login.isEmpty()) return

        loadUser(login)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun loadUser(login: String) {
        compositeDisposable.add(
            usersUseCase.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _progress.value = true }
                .doFinally { _progress.value = false }
                .subscribe { apiUser -> _user.value = apiUser.mapToPresentation() })
    }
}
