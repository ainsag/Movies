package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class MainViewModel  (private var repository: AuthRepository): ViewModel(){
    var firebaseSignUp: MutableLiveData<FirebaseUser> = MutableLiveData()
    var firebaseSignIn: MutableLiveData<FirebaseUser> = MutableLiveData()

    fun registerUserInFirebase(email:String , password:String , name:String, surname:String ){
        viewModelScope.launch {
            repository.registerUser(email , password, name, surname)
            firebaseSignUp =  repository.firebaseSignUpUser
        }
    }

    fun loginUserInFirebase(email:String , password : String){
        viewModelScope.launch {
            repository.loginUser(email , password)
            firebaseSignIn = repository.firebaseSignInUser
        }
    }
}