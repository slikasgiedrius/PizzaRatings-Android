package com.giedrius.slikas.pizzaratings.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.utils.extensions.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

  private lateinit var auth: FirebaseAuth
  private lateinit var googleSignInClient: GoogleSignInClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initGoogleLogin()

    setContent {
      Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { signIn() }) {
          Text("Login")
        }
        Button(onClick = { logOut() }) {
          Text("Logout")
        }
      }
    }

  }

  override fun onStart() {
    super.onStart()
    // Check if user is signed in (non-null) and update UI accordingly.
    updateUI()
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
    this.toast("USER ${auth.currentUser?.email}")
  }

  private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
      .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          // Sign in success, update UI with the signed-in user's information
          Log.d("TAG", "signInWithCredential:success")
          updateUI()
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
    Firebase.auth.signOut()
    this.toast("USER ${auth.currentUser?.email}")
  }

    private fun initGoogleLogin() {
      val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

      googleSignInClient = GoogleSignIn.getClient(this, gso)
      auth = Firebase.auth
    }

    companion object {
    private const val RC_SIGN_IN = 9001
  }
}