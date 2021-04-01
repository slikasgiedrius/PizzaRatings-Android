package com.giedrius.slikas.pizzaratings.compose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.ui.theme.PizzaRatingsTheme
import com.google.accompanist.coil.CoilImage

class ComposeActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PizzaRatingsTheme {
        LayoutsCodelab()
//        Column {
//          NewsStory()
//          PhotographerCard()
//        }
      }
    }
  }


  @Preview
  @Composable
  fun LayoutsCodelabPreview() {
    PizzaRatingsTheme {
      LayoutsCodelab()
    }
  }

  @Composable
  fun LayoutsCodelab() {
    Scaffold(
      topBar = {
        TopAppBar(
          title = {
            Text(text = "AppBar")
          },
          navigationIcon = {
            IconButton(
              onClick = { closeActivity() }
            ) {
              Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    ) { innerPadding ->
      BodyContent(Modifier.padding(innerPadding))
    }
  }

  private fun closeActivity() = finish()
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Text(text = "Hi there!")
    Text(text = "Thanks for going through the Layouts codelab")
    LazyList()
  }
}

@Composable
fun LazyList() {
  // We save the scrolling position with this state that can also
  // be used to programmatically scroll the list
  val scrollState = rememberLazyListState()

  LazyColumn(state = scrollState) {
    items(20) {
      ImageListItem(it)
    }
  }
}

@Composable
fun ImageListItem(index: Int) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    CoilImage(
      data = "https://developer.android.com/images/brand/Android_Robot.png",
      contentDescription = "Android logo dude",
      modifier = Modifier.size(50.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = "Item #$index",
      style = MaterialTheme.typography.subtitle1
    )
  }
}


