package developer.igorsharov.githubusers.ui.pojo

import igor.sharov.domain.User as DomainUser

data class User(
    val username: String,
    val avatarUrl: String,
    val repositoryUrl: String,
    val location: String?
)

fun DomainUser.mapToPresentation() = User(login, avatar_url, html_url ?: "", location)
