package com.giedrius.slikas.pizzaratings.ui.profile

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.giedrius.slikas.pizzaratings.data.model.UserData
import com.giedrius.slikas.pizzaratings.utils.mocks.getMockedUserData

@Composable
fun ProfileFragmentContent(
  userData: UserData,
  logout: () -> Unit
) {
  Surface {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(all = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val logoUrl = userData.photoUrl?.toString()

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
      userData.name?.let { Text(it) }
      userData.email?.let { Text(it) }
      userData.uid?.let { Text(it) }
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(bottom = 60.dp),
        verticalArrangement = Arrangement.Bottom
      ) {
        Button(
          onClick = {
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

@Preview(showSystemUi = true)
@Composable
fun  PreviewProfileFragmentContent() {
  ProfileFragmentContent(
    userData = getMockedUserData(),
    logout = {}
  )
}