package io.moatwel.android.sample.uistatesample.repository

import io.moatwel.android.sample.uistatesample.model.Result
import io.moatwel.android.sample.uistatesample.model.User
import kotlinx.coroutines.delay
import java.lang.IllegalStateException
import kotlin.random.Random.Default.nextBoolean


class UserRepository {

    private val userList = listOf(
        User(
            id = 1,
            name = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        User(
            id = 2,
            name = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4"
        ),
        User(
            id = 3,
            name = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4"
        ),
        User(
            id = 4,
            name = "wycats",
            avatarUrl = "https://avatars.githubusercontent.com/u/4?v=4"
        ),
        User(
            id = 5,
            name = "ezmobius",
            avatarUrl = "https://avatars.githubusercontent.com/u/5?v=4"
        ),
        User(
            id = 6,
            name = "ivey",
            avatarUrl = "https://avatars.githubusercontent.com/u/6?v=4"
        ),
        User(
            id = 7,
            name = "evanphx",
            avatarUrl = "https://avatars.githubusercontent.com/u/7?v=4"
        ),
        User(
            id = 8,
            name = "vanpelt",
            avatarUrl = "https://avatars.githubusercontent.com/u/8?v=4"
        ),
        User(
            id = 9,
            name = "wayneeseguin",
            avatarUrl = "https://avatars.githubusercontent.com/u/9?v=4"
        ),
    )

    suspend fun getUserList(): Result<List<User>> {
        delay(1000)

        return if (nextBoolean()) {
            Result.Success(userList)
        } else {
            Result.Failure(IllegalStateException("Network Error Occur"))
        }
    }
}
