package com.ravichand.weatherapp.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.ravichand.weatherapp.R


class ProgressUtils {

    companion object {

        var dialog: Dialog? = null


        fun showProgress(context: Activity) {
            if(dialog == null){
                dialog = Dialog(context, R.style.Theme_Weatherapp)
            }
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setCancelable(false)
            dialog?.setContentView(R.layout.progress_bar)
            dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            dialog?.show()

        }

        fun hideProgress() {
            if(dialog != null){
                dialog?.dismiss()
                dialog = null

            }
        }
    }
}