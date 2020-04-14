package ruf.view.feature_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.view.*
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope


internal class ExampleDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fragment, container, false).apply {
            toast.setOnClickListener { Toast.makeText(context, "Hello. I am toast. =)", Toast.LENGTH_LONG).show() }
        }
}
