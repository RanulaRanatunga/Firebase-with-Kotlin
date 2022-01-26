package com.ranula.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.ranula.kotlin_app.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {

     private lateinit var binding: ActivityProfileBinding

     private lateinit var  actionBar: ActionBar

     private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title="Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutButton.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
      val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null) {
                val email = firebaseUser.email
            binding.emailTextField.text = email
        }else {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}