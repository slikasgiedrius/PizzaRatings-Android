package com.giedrius.slikas.pizzaratings.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.ComposeActivity
import com.giedrius.slikas.pizzaratings.compose.ui.theme.PizzaRatingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

  private lateinit var viewModel: NotificationsFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      //Set compose here
      setContent {
        PizzaRatingsTheme {
          navigationButton(::navigateToComposeActivity)
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(NotificationsFragmentViewModel::class.java)

    handleObservers()
  }

  private fun handleObservers() {
    viewModel.text.observe(viewLifecycleOwner, Observer {
//      binding.textNotifications.text = it
    })
  }

  fun navigateToComposeActivity() {
    val intent = Intent(context, ComposeActivity::class.java)
    startActivity(intent)
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
fun navigationButton(
  onClick: () -> Unit
) {
  Surface {
    Column {
      NewsStory()
      Button(
        onClick = { onClick() },
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        Text("Welcome")
      }
    }
  }
}

@Preview(
  showSystemUi = true,
)
@Composable
fun PreviewNavigationButton() {
  PizzaRatingsTheme {
    navigationButton(onClick = {  })
  }
}