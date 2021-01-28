package com.chiper.testchiper.dialogs


import android.os.Handler
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.chiper.testchiper.App
import com.chiper.testchiper.R
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException

object SnackFactory {

    const val FIVE_SECONDS = 5000L

    fun showSuccessMessage(fragmentActivity: FragmentActivity, resource: Int, message: String) {
        Snackbar.make(fragmentActivity.findViewById<CoordinatorLayout>(resource), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(App.getAppContext(), R.color.colorPrimary))
            .setTextColor(ContextCompat.getColor(App.getAppContext(), android.R.color.white))
            .setDuration(Snackbar.LENGTH_LONG)
            .show()
    }

    fun showSuccessWithTiming(fragmentActivity: FragmentActivity, resource: Int, message: String, duration: Long) {
        val snackBar = Snackbar.make(fragmentActivity.findViewById<CoordinatorLayout>(resource), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(App.getAppContext(), R.color.colorPrimary))
            .setTextColor(ContextCompat.getColor(App.getAppContext(), android.R.color.white))
            .setDuration(Snackbar.LENGTH_INDEFINITE)
        snackBar.show()
        Handler().postDelayed({
            snackBar.dismiss()
        }, duration)
    }

    fun showWarningMessage(fragmentActivity: FragmentActivity, resource: Int, message: String) {
        Snackbar.make(fragmentActivity.findViewById<CoordinatorLayout>(resource), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(App.getAppContext(), R.color.statusYellowLabel))
            .setTextColor(ContextCompat.getColor(App.getAppContext(), android.R.color.white))
            .setDuration(Snackbar.LENGTH_LONG)
            .show()
    }

    fun showErrorMessage(
        httpException: HttpException? = null,
        nullPointerException: KotlinNullPointerException? = null,
        defaultMessage: String? = null,
        currentView: View? = null,
        fragmentActivity: FragmentActivity,
        resource: Int? = null,
        color: Int? = null
    ) {
        val finalMessage = getMessage(httpException, nullPointerException, defaultMessage, fragmentActivity)
        val finalView = getCurrentView(fragmentActivity, currentView, resource)
        finalView?.let {
            Snackbar.make(finalView, finalMessage, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(App.getAppContext(), color ?: R.color.statusRedLabel))
                .setTextColor(ContextCompat.getColor(App.getAppContext(), android.R.color.white))
                .setDuration(Snackbar.LENGTH_LONG)
                .show()
        }
    }

    fun showErrorMessageWithTiming(fragmentActivity: FragmentActivity, resource: Int, message: String, duration: Long) {
        val snackBar = Snackbar.make(fragmentActivity.findViewById<CoordinatorLayout>(resource), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(App.getAppContext(), R.color.statusRedLabel))
            .setTextColor(ContextCompat.getColor(App.getAppContext(), android.R.color.white))
            .setDuration(Snackbar.LENGTH_INDEFINITE)
        snackBar.show()
        Handler().postDelayed({
            snackBar.dismiss()
        }, duration)
    }

    private fun getMessage(
        httpException: HttpException?,
        nullPointerException: KotlinNullPointerException?,
        defaultMessage: String?,
        fragmentActivity: FragmentActivity
    ): String {
        var message = fragmentActivity.getString(R.string.something_went_wrong_retry)
        // HttpException has been sent
        httpException?.let { message = httpException.response()?.errorBody()?.string() ?: fragmentActivity.getString(R.string.something_went_wrong_retry) }
        // NullPointerException has been sent
        nullPointerException?.let { message = it.message ?: fragmentActivity.getString(R.string.something_went_wrong_retry) }
        // Default message has been sent
        defaultMessage?.let { message = it }
        return message
    }

    private fun getCurrentView(fragmentActivity: FragmentActivity, currentView: View?, resource: Int?): View? {
        var finalView: View? = null
        currentView?.let { finalView = currentView } ?: run {
            resource?.let {
                finalView = fragmentActivity.findViewById<CoordinatorLayout>(resource)
            }
        }
        return finalView
    }
}