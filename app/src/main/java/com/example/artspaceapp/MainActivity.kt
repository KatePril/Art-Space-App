package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageChanger()
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(
    imageId: Int,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = Modifier
            .padding(20.dp)
            .shadow(10.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
    }


}

@Composable
fun ArtworkDescriptor(
    titleId: Int,
    artistId: Int,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .shadow(10.dp)
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = titleId),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = artistId),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )
        }
    }


}

@Composable
fun DisplayController(
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        ControllerButton(
            textId = R.string.next,
            onClickNext,
            modifier = Modifier.weight(1f)
        )
        ControllerButton(
            textId = R.string.previous,
            onClickPrevious,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ControllerButton(
    textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red = 9, green = 177, blue = 89, alpha = 255)
        )
    ) {
        Text(text = stringResource(textId))
    }
}

@Composable
fun Page(
    imageId: Int,
    titleId: Int,
    artistId: Int,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(red = 222, green = 255, blue = 244, alpha = 255)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ArtworkWall(imageId = imageId)
            ArtworkDescriptor(
                titleId = titleId,
                artistId = artistId
            )
            DisplayController(onClickNext, onClickPrevious)
        }
    }

}

@Composable
fun PageChanger(modifier: Modifier = Modifier) {
    var currentPage by remember { mutableStateOf(1) }

    when (currentPage) {
        1 -> {
            Page(
                imageId = R.drawable.rose,
                titleId = R.string.rose_title,
                artistId = R.string.rose_author,
                onClickNext = {currentPage = 2},
                onClickPrevious = {currentPage = 5}
            )
        }
        2 -> {
            Page(
                imageId = R.drawable.stack_of_rocks,
                titleId = R.string.stack_of_rocks_title,
                artistId = R.string.stack_of_rocks_author,
                onClickNext = { currentPage = 3 },
                onClickPrevious = { currentPage = 1 }
            )
        }
        3 -> {
            Page(
                imageId = R.drawable.valley_tree,
                titleId = R.string.valley_tree_title,
                artistId = R.string.valley_tree_author,
                onClickNext = { currentPage = 4 },
                onClickPrevious = { currentPage = 2 }
            )
        }
        4 -> {
            Page(
                imageId = R.drawable.lake_and_boats,
                titleId = R.string.lake_and_boats_title,
                artistId = R.string.lake_and_boats_author,
                onClickNext = { currentPage = 5 },
                onClickPrevious = { currentPage = 3 })
        }
        5 -> {
            Page(
                imageId = R.drawable.lake_and_valley,
                titleId = R.string.lake_and_valley_title,
                artistId = R.string.lake_and_valley_author,
                onClickNext = { currentPage = 1 },
                onClickPrevious = { currentPage = 4 })
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtSpaceApp() {
    ArtSpaceAppTheme {
        PageChanger()
    }
}