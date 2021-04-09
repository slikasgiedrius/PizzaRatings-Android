package com.giedrius.slikas.pizzaratings.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun ProfileFragmentContent(
  viewModel: ProfileFragmentViewModel,
  logout: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    val logoUrl = viewModel.firebaseAuth.currentUser?.photoUrl.toString()

    CoilImage(
      data = logoUrl,
      contentDescription = "Pizzeria logo",
      fadeIn = true,
      contentScale = ContentScale.Crop,
      modifier = Modifier.size(100.dp)
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