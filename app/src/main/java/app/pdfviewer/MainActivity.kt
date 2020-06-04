package app.pdfviewer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object{
        private val PICK_PDF_CODE = 1000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyuserloggedin()
        var url = "https://firebasestorage.googleapis.com/v0/b/webappquery.appspot.com/o/RohitKumar.pdf?alt=media&token=f2cdfca5-4e30-4b5d-810b-97750f1d09f2"
        Dexter.withActivity(this)
            .withPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object:BaseMultiplePermissionsListener(){
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    super.onPermissionsChecked(report)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    super.onPermissionRationaleShouldBeShown(permissions, token)
                }
            })
        btn_view_assets.setOnClickListener {
            val intent = Intent(this,ViewActivity::class.java)
            intent.putExtra("ViewType","assets")
            startActivity(intent)
        }
        btn_view_storage.setOnClickListener {
            val pdfintent = Intent(Intent.ACTION_GET_CONTENT)
            pdfintent.type = "application/pdf"
            pdfintent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(pdfintent,"Select PDF"), PICK_PDF_CODE)
        }
        btn_view_internet.setOnClickListener {
            val intent = Intent(this,ViewActivity::class.java)
            intent.putExtra("ViewType","internet")
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PICK_PDF_CODE && resultCode == Activity.RESULT_OK && data!=null){
            val selectedpdf = data.data
            val intent = Intent(this,ViewActivity::class.java)
            intent.putExtra("ViewType","storage")
            intent.putExtra("FileUri",selectedpdf.toString())
            startActivity(intent)
        }
    }
    private fun verifyuserloggedin(){
        val uid = FirebaseAuth.getInstance().uid
        if (uid== null){
            val intent = Intent(this,Registration::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)}
    }
}
