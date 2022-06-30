package io.moatwel.android.sample.uistatesample.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.moatwel.android.sample.uistatesample.model.User
import io.moatwel.android.sample.uistatesample.uistate.UserListUiState
import java.util.UUID


val purple200 = Color(0xffbb86fc)
val purple500 = Color(0xff6200ee)
val purple700 = Color(0xff3700b3)
val teal200 = Color(0xff03dac5)
val teal700 = Color(0xff018786)
val black = Color(0xff000000)
val white = Color(0xffffffff)

val lightColors = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    onPrimary = white,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = black,
)
val darkColors = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    onPrimary = black,
    secondary = teal200,
    secondaryVariant = teal200,
    onSecondary = black,
)

@Composable
fun UiStateSampleTheme(
    composable: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors
    MaterialTheme(
        colors = colors
    ) {
        composable.invoke()
    }
}


@Composable
fun UserListScreen(
    uiState: UserListUiState,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onSnackBarShown: (UUID) -> Unit = {},
) {
    println("Hoge: UserListScreen: $uiState")
    uiState.flashMessage?.let { flashMessage ->
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = flashMessage.message,
            )
            onSnackBarShown.invoke(flashMessage.id)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(uiState.userList) { item ->
                    UserItem(user = item)
                }
            }
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun UserItem(
    user: User,
    onClickUser: (User) -> Unit = {},
) {
    println("Hoge: UserItem: $user")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClickUser.invoke(user) }
    ) {
        AsyncImage(
            model = user.avatarUrl,
            modifier = Modifier
                .size(80.dp)
                .padding(16.dp)
                .clip(CircleShape),
            contentDescription = null,
        )
        Text(
            text = user.name,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 17.sp
        )
    }
}

@Preview
@Composable
fun PreviewUserListScreen() {
    val uiState = UserListUiState(
        isLoading = true,
        userList = arrayListOf(),
        flashMessage = null,
    )

    UserListScreen(
        uiState,
    )
}

@Preview
@Composable
fun PreviewUserItem() {
    val user = User(
        id = 1,
        name = "mojombo",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
    )

    UiStateSampleTheme {
        UserItem(user = user)
    }
}
