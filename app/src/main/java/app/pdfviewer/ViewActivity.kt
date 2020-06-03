package app.pdfviewer

import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view.*

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        if (intent != null){
            val viewType = intent.getStringExtra("ViewType")
            if (!TextUtils.isEmpty(viewType) || viewType != null){
                if (viewType.equals("assets"))
                {
                    pdf_viewer.fromAsset("RohitKumar.pdf")
                        .password(null)
                        .defaultPage(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .onDraw{ canvas, pageWidth, pageHeight, displayedPage ->

                        }.onDrawAll{ canvas, pageWidth, pageHeight, displayedPage ->

                        }.onPageChange{ page, pageCount ->

                        }.onPageError{ page, t ->
                            Toast.makeText(this,"Error while opening the page"+ page,Toast.LENGTH_SHORT).show()

                        }.onTap { false }
                        .onRender { nbPages, pageWidth, pageHeight ->
                            pdf_viewer.fitToWidth()
                        }.enableAnnotationRendering(true)
                        .invalidPageColor(Color.RED)
                        .load()
                }
            }
        }
    }
}