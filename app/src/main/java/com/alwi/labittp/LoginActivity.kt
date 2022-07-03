package com.alwi.labittp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.alwi.labittp.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.Instant
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:LoginActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signupBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener{
            val email = binding.emailLogin.text.toString()
            val pass = binding.passwordLogin.text.toString()

            //Jika Email Kosong
            if (email.isEmpty()){
                binding.emailLogin.error = "Email Harus Diisi!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            //Memastikan bahwa email sudah sesuai dengan di daftarkan
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailLogin.error = "Email Tidak Sesuai!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            //Jika Password Kosong
            if (pass.isEmpty()){
                binding.passwordLogin.error = "Password Harus Diisi!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            //Jika Password Kurang dari 8
            if (pass.length < 8){
                binding.passwordLogin.error = "Password Minimal 8 Karakter!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            //Jika semua kondisi diatas sudah terpenuhi
            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}