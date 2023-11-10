import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bignerdranch.android.criminalintent.R
import java.io.File


class PictureDialogFragment : DialogFragment() {

    private var photoFileName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.zoom_in_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoFileName = arguments?.getString(ARG_PHOTO_FILE_NAME)
        updatePhoto()
    }

    private fun updatePhoto() {
        val photoFile = photoFileName?.let {
            File(requireContext().applicationContext.filesDir, it)
        }

        if (photoFile?.exists() == true) {
            val imageView = view?.findViewById<ImageView>(R.id.crimePicture)
            val bitmap = BitmapFactory.decodeFile(photoFile.path)
            imageView?.setImageBitmap(bitmap)
        }
    }

    companion object {
        const val ARG_PHOTO_FILE_NAME = "photoFileName"

        fun newInstance(photoFileName: String): PictureDialogFragment {
            val args = Bundle().apply {
                putString(ARG_PHOTO_FILE_NAME, photoFileName)
            }
            return PictureDialogFragment().apply {
                arguments = args
            }
        }
    }
}




