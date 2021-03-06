package com.example.restaurent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.restaurent.util.LoadingState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginScreenViewModel : ViewModel() {

  val loadingState = MutableStateFlow(LoadingState.IDLE)

  fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
    try {
      loadingState.emit(LoadingState.LOADING)
      Firebase.auth.signInWithEmailAndPassword(email, password).await()
      loadingState.emit(LoadingState.LOADED)
    } catch (e: Exception) {
      loadingState.emit(LoadingState.error(e.localizedMessage))
    }
  }

  fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
    try {
      loadingState.emit(LoadingState.LOADING)
      Firebase.auth.createUserWithEmailAndPassword(email, password).await()
      loadingState.emit(LoadingState.LOADED)
    } catch (e: Exception) {
      loadingState.emit(LoadingState.error(e.localizedMessage))
    }
  }
  //.createUserWithEmailAndPassword

  fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
    try {
      loadingState.emit(LoadingState.LOADING)
      Firebase.auth.signInWithCredential(credential).await()
      loadingState.emit(LoadingState.LOADED)
    } catch (e: Exception) {
      loadingState.emit(LoadingState.error(e.localizedMessage))
    }
  }
}