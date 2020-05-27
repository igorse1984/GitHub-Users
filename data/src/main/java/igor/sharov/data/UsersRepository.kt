package igor.sharov.data

import igor.sharov.domain.User
import io.reactivex.Single

class UsersRepository(private val persistenceSource: PersistenceSourceUsers) {
    fun getUsers(since: Int, amount: Int) = persistenceSource.getUsers(since, amount)

    fun getUser(login: String) = persistenceSource.getUser(login)
}

interface PersistenceSourceUsers {
    fun getUser(login: String): Single<User>

    fun getUsers(since: Int, perPage: Int): Single<List<User>>
}
