package developer.igorsharov.githubusers.pojo

data class User(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val html_url: String,
    val location: String?
)
