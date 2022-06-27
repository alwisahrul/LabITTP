package com.alwi.labittp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alwi.labittp.databinding.SignupActivityBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.Instant

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignupActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = SignupActivityBinding.inflate((layoutInflater))
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signup2.setOnClickListener{
            val fullName = binding.fullnameRgstr.text.toString()
            val username = binding.usernameRgstr.text.toString()
            val email = binding.email.text.toString()
            val pass = binding.passwordRgstr.text.toString()
            val confirmPass = binding.confirmPass.text.toString()

            if(fullName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Selamat! Anda Terdaftar", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}