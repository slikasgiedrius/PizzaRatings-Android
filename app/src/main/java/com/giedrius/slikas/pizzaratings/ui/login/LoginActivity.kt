package com.giedrius.slikas.pizzaratings.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.GoogleButton
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.ui.MainActivity
import com.giedrius.slikas.pizzaratings.utils.extensions.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

  private val viewModel: LoginActivityViewModel by viewModels()
  private lateinit var googleSignInClient: GoogleSignInClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initGoogleSignInClient()
    setContent {
      PizzaRatingsTheme {
        Column(modifier = Modifier.padding(16.dp)) {
          Text("User email ${viewModel.firebaseAuth.currentUser?.email}")
          GoogleButton(::signIn)
        }
      }
    }
  }

  override fun onStart() {
    super.onStart()
    if (viewModel.firebaseAuth.currentUser != null) {
      launchMainActivity()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      val task = GoogleSignIn.getSignedInAccountFromIntent(data)
      try {
        // Google Sign In was successful, authenticate with Firebase
        val account = task.getResult(ApiException::class.java)!!
        Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
        firebaseAuthWithGoogle(account.idToken!!)
      } catch (e: ApiException) {
        // Google Sign In failed, update UI appropriately
        Toast.makeText(this, "FAILED $e", Toast.LENGTH_LONG)
        Log.w("TAG", "Google sign in failed", e)
      }
    }
  }

  private fun updateUI() {
    this.toast("USER ${viewModel.firebaseAuth.currentUser?.email}")
  }

  private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    viewModel.firebaseAuth.signInWithCredential(credential)
      .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          // Sign in success, update UI with the signed-in user's information
          Log.d("TAG", "signInWithCredential:success")
          if (viewModel.firebaseAuth.currentUser != null) {
            launchMainActivity()
          }
        } else {
          // If sign in fails, display a message to the user.
          Log.w("TAG", "signInWithCredential:failure", task.exception)
          updateUI()
        }
      }
  }

  private fun signIn() {
    val signInIntent = googleSignInClient.signInIntent
    startActivityForResult(signInIntent, RC_SIGN_IN)
  }

  private fun logOut() {
    viewModel.firebaseAuth.signOut()
    this.toast("USER ${viewModel.firebaseAuth.currentUser?.email}")
  }

  private fun initGoogleSignInClient() {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken(getString(R.string.default_web_client_id))
      .requestEmail()
      .build()

    googleSignInClient = GoogleSignIn.getClient(this, gso)
  }

  private fun launchMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }

  companion object {
    private const val RC_SIGN_IN = 9001
  }
}