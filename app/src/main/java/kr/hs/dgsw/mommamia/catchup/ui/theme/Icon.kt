package kr.hs.dgsw.mommamia.catchup.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kr.hs.dgsw.mommamia.catchup.R

@Composable
fun CatchUpIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null
) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_catch_up),
        tint = tint,
        contentDescription = contentDescription
    )
}

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_add),
        contentDescription = contentDescription
    )
}
