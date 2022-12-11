package com.example.movies.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.ActivitySignUpBinding
import com.example.movies.repository.AuthRepository
import com.example.movies.viewmodel.MainViewModel
import com.example.movies.viewmodel.MainViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseViewModel = ViewModelProvider(this,
            MainViewModelFactory(AuthRepository())
        )[MainViewModel::class.java]

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button3.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val email = binding.editText1.text.toString()
            val pass = binding.editText2.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SignIn::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else if (email.isEmpty() && pass.isEmpty()){
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
            else{
                firebaseViewModel.loginUserInFirebase(email , pass)
            }
        }
    }
}
