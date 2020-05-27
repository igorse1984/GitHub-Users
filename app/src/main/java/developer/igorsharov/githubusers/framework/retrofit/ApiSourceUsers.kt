package developer.igorsharov.githubusers.framework.retrofit

import igor.sharov.data.PersistenceSourceUsers
import igor.sharov.domain.User
import io.reactivex.Single

class ApiSourceUsers(private val apiServiceUsers: ApiServiceUsers) :
    PersistenceSourceUsers {

    override fun getUser(login: String): Single<User> = apiServiceUsers.getUser(login)

    override fun getUsers(since: Int, perPage: Int): Single<List<User>> =
        apiServiceUsers.getUsers(since, perPage)
}