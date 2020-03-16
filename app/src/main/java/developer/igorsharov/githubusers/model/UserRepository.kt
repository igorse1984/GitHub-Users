package developer.igorsharov.githubusers.model

import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import developer.igorsharov.githubusers.pojo.User
import developer.igorsharov.githubusers.AMOUNT_LOAD_USER
import io.reactivex.Single

class UserRepository(private val apiService: ApiServiceUsers) {

    fun getUsers(since: Int): Single<List<User>> = apiService.getUsers(since, AMOUNT_LOAD_USER)

    fun getUser(login: String): Single<User> = apiService.getUser(login)
}
