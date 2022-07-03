package com.alwi.labittp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alwi.labittp.databinding.SignupActivityBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.Instant

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignupActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth //Mengkoneksikan ke firebase
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

            //Jika nama lengkap kosong
            if (fullName.isEmpty()){
                binding.fullnameRgstr.error = "Nama Lengkap Harus Diisi!"
                binding.fullnameRgstr.requestFocus()
                return@setOnClickListener
            }

            //Jika Email Kosong
            if (email.isEmpty()){
                binding.email.error = "Email Harus Diisi!"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            //Jika username kosong
            if (username.isEmpty()){
                binding.usernameRgstr.error = "Username Harus Diisi!"
                binding.usernameRgstr.requestFocus()
                return@setOnClickListener
            }

            //Jika Password Kosong
            if (pass.isEmpty()){
                binding.passwordRgstr.error = "Password Harus Diisi!"
                binding.passwordRgstr.requestFocus()
                return@setOnClickListener
            }

            //Jika Password Kurang dari 8
            if (pass.length < 8){
                binding.passwordRgstr.error = "Password Minimal 8 Karakter!"
                binding.passwordRgstr.requestFocus()
                return@setOnClickListener
            }

            //Jika konfirmasi password kosong
            if (confirmPass.isEmpty()){
                binding.confirmPass.error = "Wajib Ulangi Password!"
                binding.confirmPass.requestFocus()
                return@setOnClickListener
            }

            //Jika semua kondisi diatas sudah terpenuhi
            if(fullName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Selamat! Anda Terdaftar", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    binding.confirmPass.error = "Password Tidak Sesuai!"
                    binding.confirmPass.requestFocus()
                    return@setOnClickListener
                }
            }
        }

    }
}