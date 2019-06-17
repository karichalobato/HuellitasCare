package com.karichalobato.huellitascare.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.karichalobato.huellitascare.R

class LogInActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBarLogIn: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)

        progressBarLogIn=findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    fun forgotPassword(view: View){
        startActivity(Intent(this, ForgotPassActivity::class.java))

    }

    fun register(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
            progressBarLogIn.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                        task ->

                    if (task.isSuccessful){
                        action()

                    }else{
                        Toast.makeText(this,"Error en la autentificación", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
