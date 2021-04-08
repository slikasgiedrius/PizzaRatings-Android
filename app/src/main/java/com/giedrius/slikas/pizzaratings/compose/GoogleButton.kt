package com.giedrius.slikas.pizzaratings.compose

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.common.SignInButton

@Composable
fun GoogleButton(
  signIn: () -> Unit
) {
  AndroidView(
    factory = {
      SignInButton(it).apply {
        layoutParams = LinearLayout.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setOnClickListener { signIn() }
      }
    },
  )
}