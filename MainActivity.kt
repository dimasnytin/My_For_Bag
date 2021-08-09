package com.authform.my_form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.authform.my_form.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()

        binding.btLogin.setOnClickListener{
            registerUser()

        }

    }

    private fun registerUser(){

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){

            user.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity()) { task->

                    if (task.isSuccessful){
                        Toast.makeText(this,
                            "Вы успешно зарегестрированы",
                            Toast.LENGTH_LONG).show()


                        startActivity(Intent(this,
                            SecondActivity::class.java))
                        finish()

                    }else{
                        Toast.makeText(this,
                            task.exception!!.message,
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }

        }else{
            Toast.makeText(this,
                "Введите вашу почту и пароль",
                Toast.LENGTH_LONG)
                .show()
        }
    }
}