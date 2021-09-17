package com.giedrius.slikas.pizzaratings.ui.profile

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ProfileFragmentContent(
  viewModel: ProfileFragmentViewModel,
  logout: () -> Unit
) {
  Surface {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val logoUrl = viewModel.firebaseAuth.currentUser?.photoUrl.toString()

      Image(
        painter = rememberImagePainter(
          data = logoUrl,
          builder = {
            crossfade(true)
          }
        ),
        contentDescription = "Pizzeria logo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(100.dp)
          .clip(CircleShape)
          .border(2.dp, Color.Gray, CircleShape)
      )
      viewModel.firebaseAuth.currentUser?.displayName?.let { Text(it) }
      viewModel.firebaseAuth.currentUser?.email?.let { Text(it) }
      viewModel.firebaseAuth.currentUser?.uid?.let { Text(it) }
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(bottom = 60.dp),
        verticalArrangement = Arrangement.Bottom
      ) {
        Button(
          onClick = {
            viewModel.firebaseAuth.signOut()
            logout()
          },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Logout")
        }
      }
    }
  }
}