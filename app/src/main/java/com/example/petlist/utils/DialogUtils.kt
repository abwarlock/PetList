package com.example.petlist.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.petlist.R


class DialogUtils {

    companion object {
        private var isDialogDisplayed: Boolean = false

        fun showConfirmDialog(lifecycle: Lifecycle, context: Context, listener: DialogInterface.OnClickListener) {
            if (isDialogDisplayed) {
                return
            }
            val dialog: AlertDialog = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.str_error))
                .setMessage(context.getString(R.string.str_error_message))
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.str_close), listener)
                .setOnDismissListener {
                    isDialogDisplayed = false
                }
                .show()
            dialog.show()
            isDialogDisplayed = dialog.isShowing
            lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onPause(owner: LifecycleOwner) {
                    if (dialog.isShowing) {
                        dialog.cancel()
                    }
                }

            })
        }
    }
}