package com.giedrius.slikas.pizzaratings.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

class ComposeActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PizzaRatingsTheme {
        Column {
          NewsStory()
          PhotographerCard()
        }
      }
    }
  }
}

@Composable
fun NewsStory() {
  val typography = MaterialTheme.typography
  Column(
    modifier = Modifier
      .background(MaterialTheme.colors.surface)
      .padding(16.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.header),
      contentDescription = "Accessibility is extremely important",
      modifier = Modifier
        .height(180.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(4.dp)),
      contentScale = ContentScale.Crop
    )
    Spacer(Modifier.height(16.dp))

    Text(
      text = "A day wandering through the sandhills in Shark Fin Cove, and a few of the " +
          "sights I saw",
      style = typography.h6,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )
    Text(text = "Davenport, California", style = typography.body2)
    Text(text = "December 2018", style = typography.body2)
  }
}

@Composable
fun PhotographerCard() {
  Row(
    modifier = Modifier
      .clip(RoundedCornerShape(4.dp))
      .background(MaterialTheme.colors.surface)
      .clickable(onClick = { /* Ignoring onClick */ })
      .padding(16.dp)
  ) {
    Surface(
      modifier = Modifier.size(50.dp),
      shape = CircleShape,
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
      //Image
    }
    Column(
      modifier = Modifier
        .padding(start = 8.dp)
        .align(Alignment.CenterVertically)
    ) {
      Text("Alfred Sisley", fontWeight = FontWeight.Bold)
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text("3 minutes ago", style = MaterialTheme.typography.body2)
      }
    }
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
            onClick = { /* doSomething() */ }
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

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Text(text = "Hi there!")
    Text(text = "Thanks for going through the Layouts codelab")
  }
}


@Preview
@Composable
fun LayoutsCodelabPreview() {
  PizzaRatingsTheme {
    LayoutsCodelab()
  }
}

@Preview(name = "News story")
@Composable
fun PreviewNewsStory() {
  NewsStory()
}

@Preview()
@Composable
fun PreviewPhotoCard() {
  PhotographerCard()
}


