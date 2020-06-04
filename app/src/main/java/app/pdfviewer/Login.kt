package app.pdfviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener {
            login()
        }
    }
    private fun login(){
        val email=login_email_edittext.text.toString()
        val password=login_password_edittext.text.toString()
        val etemail: EditText=findViewById(R.id.login_email_edittext)
        val etpassword: EditText =findViewById(R.id.login_password_edittext)
        if (email.isEmpty()){
            etemail.error="Required Email"
        }else if (password.isEmpty()){
            etpassword.error="Required Password"
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener
                    Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                    val intent =Intent(this,MainActivity::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}