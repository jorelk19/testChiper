package com.chiper.testchiper

import android.animation.AnimatorInflater
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.util.*

class Navigation {
    companion object{
        private lateinit var currentActivity : FragmentActivity
        private var loaderDialogView: Dialog? = null

        fun setCurrentActivity(fragmentActivity: FragmentActivity){
            currentActivity = fragmentActivity
        }

        fun getCurrentActivity() : FragmentActivity{
            return currentActivity
        }

        fun <T> goTo(
            classTo: Class<T>,
            bundle: Bundle? = null,
            flags: IntArray = intArrayOf()
        ) {
            val intent = Intent(currentActivity, classTo)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            bundle?.let { intent.putExtras(it) }
            if (flags.isNotEmpty())
                for (flag in flags) {
                    intent.addFlags(flag)
                }
            currentActivity.startActivity(intent)
        }

        fun attachFragment(fragmentTo: Fragment, container: Int, bundle: Bundle? = null, addNewTransaction: Boolean = false, addToBackStack: Boolean = true) {
            bundle?.let { fragmentTo.arguments = it }

            val currentFragmentTransaction = currentActivity.supportFragmentManager
                .beginTransaction()
            // Check if backStack is required
            if (addToBackStack) {
                currentFragmentTransaction.addToBackStack(fragmentTo.tag)
            }
            // Check if add / replace case
            if (addNewTransaction)
                currentFragmentTransaction.add(container, fragmentTo)
            else
                currentFragmentTransaction.replace(container, fragmentTo)
            // Verify stateSaved for supportFragmentManager
            if (currentActivity.supportFragmentManager.isStateSaved) {
                currentFragmentTransaction.commitAllowingStateLoss()
            } else {
                currentFragmentTransaction.commit()
            }
        }

        fun showLoader() {
            if (loaderDialogView == null) {
                loaderDialogView = Dialog(currentActivity)
                loaderDialogView!!.setContentView(R.layout.layout_loader)
                AnimatorInflater.loadAnimator(currentActivity, R.animator.flipping).apply {
                    setTarget(loaderDialogView?.findViewById(R.id.imgSpinnerIcon))
                    duration = 500
                }.start()
                loaderDialogView?.setCancelable(false)
                loaderDialogView?.show()
                loaderDialogView?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                loaderDialogView?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }

        fun hideLoader() {
            loaderDialogView?.let { dialog ->
                if (dialog.isShowing) {
                    dialog.dismiss()
                }
                loaderDialogView = null
            }
        }

        fun onBack() {
            if (currentActivity.supportFragmentManager.backStackEntryCount > 1) {
                currentActivity.supportFragmentManager.popBackStack()
            }
        }
    }
}