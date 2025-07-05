package com.drm.to.ssy.digitalletter.ui.mr

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.ui.theme.ColorBlue
import com.drm.to.ssy.digitalletter.ui.theme.ColorBlueBg
import com.drm.to.ssy.digitalletter.ui.theme.ColorDark
import com.drm.to.ssy.digitalletter.ui.theme.ColorDarkBlue
import com.drm.to.ssy.digitalletter.ui.theme.ColorDarkGray
import com.drm.to.ssy.digitalletter.ui.theme.ColorGray
import com.drm.to.ssy.digitalletter.ui.theme.ColorDarkGreen
import com.drm.to.ssy.digitalletter.ui.theme.ColorGreen
import com.drm.to.ssy.digitalletter.ui.theme.ColorGreenBg
import com.drm.to.ssy.digitalletter.ui.theme.ColorLightGray
import com.drm.to.ssy.digitalletter.ui.theme.FontBold
import com.drm.to.ssy.digitalletter.ui.theme.FontMonospace
import com.drm.to.ssy.digitalletter.ui.theme.FontRegular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val APPROVE_STATUS_PENDING = 0
private const val APPROVE_STATUS_APPROVED = 1
private const val APPROVE_STATUS_REVERTED = 2

@Composable
fun MergeRequestScreen(onActivityJump: () -> Unit) {
    val context = LocalContext.current
    val audioPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }
    var comment by remember { mutableStateOf("") }
    var approveComment by remember { mutableStateOf("") }
    var approveStatus by remember { mutableIntStateOf(APPROVE_STATUS_PENDING) }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .statusBarsPadding()
    ) {
        item {
            TopBar()
        }

        item {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        item {
            TitleArea(modifier = Modifier.padding(horizontal = 24.dp))
        }

        item {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp))
        }

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                CommentsArea(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(start = 24.dp),
                    approveStatus = approveStatus,
                    approveComment = approveComment
                )

                Spacer(modifier = Modifier.width(16.dp))

                ReviewersArea(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 24.dp),
                    approveStatus = approveStatus
                )
            }
        }

        item {
            TextField(
                value = comment,
                onValueChange = {
                    comment = it
                },
                enabled = approveStatus == APPROVE_STATUS_PENDING,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedTextColor = ColorDark,
                    unfocusedTextColor = ColorDark,
                    disabledTextColor = ColorDark,
                    errorTextColor = ColorDark,
                    cursorColor = ColorBlue
                ),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(192.dp)
                    .padding(start = 24.dp, top = 16.dp)
                    .border(width = 1.dp, color = ColorDarkGray, shape = RoundedCornerShape(8.dp))
            )
        }

        item {
            Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                Spacer(modifier = Modifier.width(24.dp))

                ApproveButton(
                    isEnabled = comment.isNotEmpty() && approveStatus == APPROVE_STATUS_PENDING,
                    onClick = {
                        Log.i("FinalResult", "Approve button clicked with comment: $comment")
                        approveStatus = APPROVE_STATUS_APPROVED
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))

                RevertButton(
                    isEnabled = comment.isNotEmpty() && approveStatus == APPROVE_STATUS_PENDING,
                    onClick = {
                        Log.e("FinalResult", "Revert button clicked with comment: $comment")
                        approveStatus = APPROVE_STATUS_REVERTED
                    }
                )
            }
        }
    }

    if (approveStatus != APPROVE_STATUS_PENDING) {
        Dialog(
            onDismissRequest = {}
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    color = ColorBlue
                )

                when (approveStatus) {
                    APPROVE_STATUS_APPROVED -> {
                        Text(
                            text = stringResource(R.string.loading_msg_approved),
                            style = FontRegular.copy(color = ColorBlue, fontSize = 14.sp),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    APPROVE_STATUS_REVERTED -> {
                        Text(
                            text = stringResource(R.string.loading_msg_reverted),
                            style = FontRegular.copy(color = ColorBlue, fontSize = 14.sp),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val audioUri = "android.resource://${context.packageName}/${R.raw.dancehall}".toUri()
        audioPlayer.setMediaItem(MediaItem.fromUri(audioUri))
        audioPlayer.prepare()
        audioPlayer.play()
    }

    LaunchedEffect(approveStatus) {
        if (approveStatus != APPROVE_STATUS_PENDING) {
            approveComment = comment
            comment = ""
            delay(5000)
            withContext(Dispatchers.Main) {
                onActivityJump()
            }
        }
    }
}

@Composable
private fun ApproveButton(isEnabled: Boolean = true, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .background(if (isEnabled) ColorBlue else ColorLightGray)
        .clickable(enabled = isEnabled) {
            onClick()
        }
    ) {
        Text(
            text = "Approve",
            style = FontBold.copy(
                color = if (isEnabled) Color.White else ColorDarkGray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun RevertButton(isEnabled: Boolean = true, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .border(
            width = 1.dp,
            color = if (isEnabled) ColorBlue else ColorLightGray,
            shape = RoundedCornerShape(4.dp)
        )
        .clickable(enabled = isEnabled) {
            onClick()
        }
    ) {
        Text(
            text = "Revert",
            style = FontBold.copy(
                color = if (isEnabled) ColorBlue else ColorDarkGray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sidebar),
            contentDescription = null,
            tint = ColorGray,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(16.dp)
        )

        val annotatedString = buildAnnotatedString {
            withStyle(SpanStyle(color = ColorGray, fontSize = 12.sp)) {
                append("DigitalLetter / Merge requests / ")
            }

            withStyle(SpanStyle(color = ColorDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)) {
                append("Confession Request")
            }
        }

        Text(text = annotatedString)
    }
}

@Composable
private fun TitleArea(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(
                text = stringResource(R.string.mr_title),
                style = FontBold.copy(color = ColorDark, fontSize = 20.sp),
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier.background(ColorGreenBg, shape = RoundedCornerShape(1000.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_branch),
                        contentDescription = null,
                        tint = ColorDarkGreen,
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(16.dp)
                    )

                    Text(
                        text = "Open",
                        style = FontRegular.copy(color = ColorDarkGreen, fontSize = 10.sp),
                        modifier = Modifier.padding(end = 6.dp, top = 4.dp, bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                val annotatedString = buildAnnotatedString {
                    withStyle(SpanStyle(color = ColorDark, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 0.sp)) {
                        append("Runming ")
                    }

                    withStyle(SpanStyle(color = ColorDark, fontWeight = FontWeight.Normal, fontSize = 12.sp, letterSpacing = 0.sp)) {
                        append("requested to merge ")
                    }
                }

                Text(text = annotatedString)

                Box(modifier = Modifier
                    .background(ColorBlueBg, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = "feature/dev_run",
                        style = FontMonospace.copy(color = ColorDarkBlue, fontSize = 10.sp),
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = " into ",
                    style = FontRegular.copy(color = ColorDark, fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )

                Box(modifier = Modifier
                    .background(ColorBlueBg, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = "master/ssy",
                        style = FontMonospace.copy(color = ColorDarkBlue, fontSize = 10.sp),
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = " 1 month ago",
                    style = FontRegular.copy(color = ColorDark, fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )
            }
        }
    }
}

@Composable
private fun CommentsArea(modifier: Modifier = Modifier, approveStatus: Int, approveComment: String) {
    Column(modifier = modifier) {
        Text(
            text = "Comments",
            style = FontBold.copy(color = ColorDark, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.img_mr_assignee_1),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            CommentContent(
                modifier = Modifier.weight(1f),
                nickName = stringResource(R.string.mr_assignee_1),
                id = stringResource(R.string.mr_assignee_id_1),
                content = stringResource(R.string.mr_comment_1),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Image(
                painter = painterResource(R.drawable.img_mr_assignee_2),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            CommentContent(
                modifier = Modifier.weight(1f),
                nickName = stringResource(R.string.mr_assignee_2),
                id = stringResource(R.string.mr_assignee_id_2),
                content = stringResource(R.string.mr_comment_2),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Image(
                painter = painterResource(R.drawable.img_mr_assignee_3),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            CommentContent(
                modifier = Modifier.weight(1f),
                nickName = stringResource(R.string.mr_assignee_3),
                id = stringResource(R.string.mr_assignee_id_3),
                content = stringResource(R.string.mr_comment_3),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Image(
                painter = painterResource(R.drawable.img_mr_assignee_4),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            CommentContent(
                modifier = Modifier.weight(1f),
                nickName = stringResource(R.string.mr_assignee_4),
                id = stringResource(R.string.mr_assignee_id_4),
                content = stringResource(R.string.mr_comment_4),
            )
        }

        if (approveStatus != APPROVE_STATUS_PENDING) {
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Image(
                    painter = painterResource(R.drawable.img_mr_assignee_final),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                CommentContent(
                    modifier = Modifier.weight(1f),
                    nickName = stringResource(R.string.character_1_state_1),
                    id = stringResource(R.string.mr_assignee_id_final),
                    content = approveComment
                )
            }
        }
    }
}

@Composable
private fun ReviewersArea(modifier: Modifier = Modifier, approveStatus: Int) {
    Column(modifier = modifier) {
        Text(
            text = "Reviewers",
            style = FontBold.copy(color = ColorDark, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = ColorLightGray, shape = RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = ColorGreen, fontWeight = FontWeight.Bold, fontSize = 24.sp)) {
                    append(if (approveStatus == APPROVE_STATUS_APPROVED) "5" else "4")
                }

                withStyle(SpanStyle(color = ColorGreen, fontWeight = FontWeight.Normal, fontSize = 12.sp)) {
                    append(" / ")
                }

                withStyle(SpanStyle(color = ColorGreen, fontWeight = FontWeight.Bold, fontSize = 24.sp)) {
                    append("5")
                }

                withStyle(SpanStyle(color = ColorGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp)) {
                    append("\nReviewers Approved")
                }
            }

            Text(
                text = annotatedString,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Box {
                    Image(
                        painter = painterResource(R.drawable.img_mr_assignee_1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    TickIcon()
                }

                Spacer(modifier = Modifier.width(4.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.img_mr_assignee_2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    TickIcon()
                }

                Spacer(modifier = Modifier.width(4.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.img_mr_assignee_3),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    TickIcon()
                }

                Spacer(modifier = Modifier.width(4.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.img_mr_assignee_4),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    TickIcon()
                }

                Spacer(modifier = Modifier.width(4.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.img_mr_assignee_final),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )

                    if (approveStatus == APPROVE_STATUS_APPROVED) {
                        TickIcon()
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun BoxScope.TickIcon() {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .size(12.dp)
            .background(color = ColorGreen, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_tick),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(1.dp)
        )
    }
}

@Composable
private fun CommentContent(
    modifier: Modifier = Modifier,
    nickName: String,
    id: String,
    content: String
) {
    Column(modifier = modifier
        .border(width = 1.dp, color = ColorLightGray, shape = RoundedCornerShape(8.dp))
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        val annotatedString = buildAnnotatedString {
            withStyle(SpanStyle(color = ColorDark, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 0.sp)) {
                append("$nickName ")
            }

            withStyle(SpanStyle(color = ColorGray, fontWeight = FontWeight.Normal, fontSize = 10.sp, letterSpacing = 0.sp, fontFamily = FontFamily.Monospace)) {
                append("@$id ")
            }
        }
        Text(
            text = annotatedString,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Text(
            text = content,
            style = FontRegular.copy(color = ColorDark, fontSize = 12.sp),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun HorizontalDivider(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(ColorLightGray)
    )
}