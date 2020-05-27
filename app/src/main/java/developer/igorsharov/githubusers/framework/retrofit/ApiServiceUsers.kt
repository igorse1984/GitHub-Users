package developer.igorsharov.githubusers.framework.retrofit

import igor.sharov.domain.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceUsers {
    @GET("users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Single<List<User>>

    @GET("users/{username}")
    fun getUser(
        @Path("username") userName: String
    ): Single<User>
}