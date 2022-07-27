package com.giedrius.slikas.pizzaratings.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.GoogleButton
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.MainActivity
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
          Text("User email ${viewModel.getCurrentUser()?.email}")
          GoogleButton(::signIn)
        }
      }
    }
  }

  override fun onStart() {
    super.onStart()
    if (viewModel.getCurrentUser() != null) {
      launchMainActivity()
    }
  }

  @Deprecated("Deprecated in Java")
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
        Toast.makeText(this, "FAILED $e", Toast.LENGTH_LONG).show()
        Log.w("TAG", "Google sign in failed", e)
      }
    }
  }

  private fun updateUI() {
    this.toast("USER ${viewModel.getCurrentUser()?.email}")
  }

  private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    viewModel.firebaseAuth.signInWithCredential(credential)
      .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          // Sign in success, update UI with the signed-in user's information
          Log.d("TAG", "signInWithCredential:success")
          if (viewModel.getCurrentUser() != null) {
            viewModel.saveUser()
            launchMainActivity()
          } else {
            userIdentificationProblem()
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

  private fun userIdentificationProblem() = this.toast("User identification problem!")

  companion object {
    private const val RC_SIGN_IN = 9001
  }
}