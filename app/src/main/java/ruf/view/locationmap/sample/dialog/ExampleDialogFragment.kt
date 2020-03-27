package ruf.view.locationmap.sample.dialog

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment.*
import kotlinx.android.synthetic.main.dialog_fragment.view.*
import ruf.view.locationmap.library.module.FragmentModule.Companion.injectScope
import ruf.view.locationmap.sample.IView
import toothpick.ktp.delegate.inject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.File.separator
import java.io.FileOutputStream
import java.io.IOException


class ExampleDialogFragment : DialogFragment(), IView {

    private val presenter: ExampleDialogPresenter by inject()

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope<ExampleDialogModule>(arguments)
        super.onCreate(savedInstanceState)
        imageUri = savedInstanceState?.getParcelable("URI")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(ruf.view.locationmap.R.layout.dialog_fragment, container, false).apply {
            share.setOnClickListener { presenter.share() }
            share.isEnabled = false
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 21
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showImg()
        presenter.attachView(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("URI", imageUri)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 21) {
            if (permissions.isNotEmpty() && grantResults.isNotEmpty() &&
                permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && permissions[1] == Manifest.permission.READ_EXTERNAL_STORAGE && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                share.isEnabled = true
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
        }

        when (requestCode) {

            ExampleDialogPresenter.GALLERY_IMAGE_REQUEST_CODE -> {
                imageUri = data?.data ?: data?.extras?.getParcelable<Bitmap>("data")?.let { bitmap ->
                    try {
                        val bytes = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                        val f = File.createTempFile("photo_", ".jpg", getExternalStorageDirectory())
                        f.deleteOnExit()
                        val fo = FileOutputStream(f)
                        fo.write(bytes.toByteArray())

                        Uri.fromFile(f)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        null
                    }
                }
                showImg()
            }

            ExampleDialogPresenter.CAMERA_IMAGE_REQUEST_CODE -> {
                data?.extras?.getParcelable<Bitmap>("data")?.also { bitmap ->
                    try {
                        val bytes = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                        val f = File(getExternalStorageDirectory().toString() + separator + "temporary_file.jpg")
                        if (f.exists() && f.canWrite()) f.delete()
                        f.createNewFile()
                        val fo = FileOutputStream(f)
                        fo.write(bytes.toByteArray())

                        imageUri = Uri.parse("file:///sdcard/temporary_file.jpg")
                        showImg()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun showImg() {
        imageView ?: return
        Picasso.get()
            .load(imageUri)
            .placeholder(ruf.view.locationmap.R.drawable.ic_launcher_background)
            .into(imageView)
    }
}