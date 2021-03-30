package com.giedrius.slikas.pizzaratings.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
  private val firestore: FirebaseFirestore
) : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is dashboard Fragment"
  }
  val text: LiveData<String> = _text

  init {
    readData()
  }

  private fun saveData() {
    val user = hashMapOf(
      "name" to "Karolis",
      "age" to 26
    )

    firestore.collection("users")
      .document("kauz")
      .set(user)
      .addOnSuccessListener {
        Log.v("Add", "Success")
      }
      .addOnFailureListener {
        Log.v("Add", "Failure")
      }
  }

  private fun readData() {
    firestore.collection("users")
      .get()
      .addOnSuccessListener { result ->
        for (document in result) {
          Log.v("Tag", "${document.id} => ${document.data}")
        }
      }
      .addOnFailureListener { exception ->
        Log.v("Tag", "Error getting documents.", exception)
      }
  }
}