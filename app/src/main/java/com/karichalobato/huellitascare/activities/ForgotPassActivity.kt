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

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBarForgotPass: ProgressBar
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        txtEmail=findViewById(R.id.txtEmail)
        auth=FirebaseAuth.getInstance()
        progressBarForgotPass=findViewById(R.id.progressBarForgotPass)
    }

    fun send(view: View){
        val email=txtEmail.text.toString()

        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener (this){
                        task ->

                    if (task.isSuccessful){
                        progressBarForgotPass.visibility= View.VISIBLE
                        startActivity(Intent(this, LogInActivity::class.java))
                    }else{
                        Toast.makeText(this,"Error al enviar el email", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
