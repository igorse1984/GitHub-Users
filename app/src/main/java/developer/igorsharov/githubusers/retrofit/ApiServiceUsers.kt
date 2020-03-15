package developer.igorsharov.githubusers.retrofit

import developer.igorsharov.githubusers.pojo.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    companion object Factory {
        fun create(): ApiServiceUsers {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiServiceUsers::class.java);
        }
    }
}