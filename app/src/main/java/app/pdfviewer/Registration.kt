package app.pdfviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        registration.setOnClickListener {
            performregister()
        }
        alredy.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
    private fun performregister(){
        val email = email_registration.text.toString()
        val password= registration_password.text.toString()
        val etemail:EditText=findViewById(R.id.email_registration)
        val etpassword:EditText= findViewById(R.id.registration_password)
        if (email.isEmpty()){
            etemail.error="Required Email"
        }else if (password.isEmpty()){
            etpassword.error="Required Password"
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Register","Account created successfully.")
                    Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to create Account ${it.message}",Toast.LENGTH_SHORT).show()
                }
        }
    }
}