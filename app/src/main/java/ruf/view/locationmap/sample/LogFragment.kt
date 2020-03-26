package ruf.view.locationmap.sample

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.*

abstract class LogFragment : Fragment() {

    protected var logTag: String = this::class.java.name

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        log("$logTag onCreateContextMenu")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        log("$logTag onContextItemSelected")
        return super.onContextItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        log("$logTag onConfigurationChanged")
        super.onConfigurationChanged(newConfig)
    }

    override fun onAttach(context: Context?) {
        log("$logTag onAttach")
        super.onAttach(context)
    }

    override fun onPause() {
        log("$logTag onPause")
        super.onPause()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        log("$logTag onPrepareOptionsMenu")
        super.onPrepareOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        log("$logTag onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        log("$logTag onMultiWindowModeChanged")
        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onInflate(context: Context?, attrs: AttributeSet?, savedInstanceState: Bundle?) {
        log("$logTag onInflate")
        super.onInflate(context, attrs, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log("$logTag onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        log("$logTag onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log("$logTag onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onLowMemory() {
        log("$logTag onLowMemory")
        super.onLowMemory()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        log("$logTag onCreateOptionsMenu")
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        log("$logTag onStart")
        super.onStart()
    }

    override fun onDestroyOptionsMenu() {
        log("$logTag onDestroyOptionsMenu")
        super.onDestroyOptionsMenu()
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        log("$logTag onOptionsMenuClosed")
        super.onOptionsMenuClosed(menu)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        log("$logTag onRequestPermissionsResult")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        log("$logTag onResume")
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log("$logTag onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        log("$logTag onDetach")
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("$logTag onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        log("$logTag onAttachFragment")
        super.onAttachFragment(childFragment)
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        log("$logTag onGetLayoutInflater")
        return super.onGetLayoutInflater(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        log("$logTag onHiddenChanged")
        super.onHiddenChanged(hidden)
    }

    override fun onDestroyView() {
        log("$logTag onDestroyView")
        super.onDestroyView()
    }

    override fun onStop() {
        log("$logTag onStop")
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        log("$logTag onOptionsItemSelected")
        return super.onOptionsItemSelected(item)
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        log("$logTag onPictureInPictureModeChanged")
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        log("$logTag onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroy() {
        log("$logTag onDestroy")
        super.onDestroy()
    }
}
