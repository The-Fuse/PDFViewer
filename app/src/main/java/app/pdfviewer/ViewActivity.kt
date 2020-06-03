package app.pdfviewer

import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.krishna.fileloader.FileLoader
import com.krishna.fileloader.listener.FileRequestListener
import com.krishna.fileloader.pojo.FileResponse
import com.krishna.fileloader.request.FileLoadRequest
import kotlinx.android.synthetic.main.activity_view.*
import java.io.File

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
                else if (viewType.equals("storage")){
                    val selectedpdf = Uri.parse((intent.getStringExtra("FileUri")))
                    pdf_viewer.fromUri(selectedpdf)
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
                else if (viewType.equals("internet")){
                    progress_bar.visibility = View.VISIBLE
                    FileLoader.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/webappquery.appspot.com/o/RohitKumar.pdf?alt=media&token=f2cdfca5-4e30-4b5d-810b-97750f1d09f2",false)
                        .fromDirectory("PDFFile",FileLoader.DIR_INTERNAL)
                        .asFile(object:FileRequestListener<File>{
                            override fun onLoad(
                                request: FileLoadRequest?,
                                response: FileResponse<File>?
                            ) {
                                progress_bar.visibility = View.GONE
                                val pdffile = response!!.body

                                pdf_viewer.fromFile(pdffile)
                                    .password(null)
                                    .defaultPage(0)
                                    .enableSwipe(true)
                                    .swipeHorizontal(false)
                                    .enableDoubletap(true)
                                    .onDraw{ canvas, pageWidth, pageHeight, displayedPage ->

                                    }.onDrawAll{ canvas, pageWidth, pageHeight, displayedPage ->

                                    }.onPageChange{ page, pageCount ->

                                    }.onPageError{ page, t ->
                                        Toast.makeText(this@ViewActivity,"Error while opening the page"+ page,Toast.LENGTH_SHORT).show()

                                    }.onTap { false }
                                    .onRender { nbPages, pageWidth, pageHeight ->
                                        pdf_viewer.fitToWidth()
                                    }.enableAnnotationRendering(true)
                                    .invalidPageColor(Color.RED)
                                    .load()
                            }

                            override fun onError(request: FileLoadRequest?, t: Throwable?) {
                                Toast.makeText(this@ViewActivity,""+t!!.message, Toast.LENGTH_SHORT).show()
                                progress_bar.visibility = View.GONE
                            }

                        })
                }
            }
        }
    }
}