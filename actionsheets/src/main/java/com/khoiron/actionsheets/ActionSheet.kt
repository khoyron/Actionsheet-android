package com.khoiron.actionsheets

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khoiron.actionsheets.callback.ActionSheetCallBack
import com.khoiron.actionsheets.callback.OnClickListener
import java.util.*


/**
 * Created by khoiron on 01/06/18.
 */

class ActionSheet {

    private lateinit var context: Context
    private var data: MutableList<String> = ArrayList()
    private val actionSheet by lazy { ActionSheet(context, data) }

    private var alertDialog: AlertDialog? = null
    private lateinit var title: AppCompatTextView
    private lateinit var cancel: AppCompatTextView
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var lineTitle: LinearLayoutCompat
    private lateinit var lineContent: LinearLayoutCompat
    private lateinit var lineCancel: LinearLayoutCompat
    private val recyclerviewAdapter by lazy { RecyclerviewAdapter(data, context) }

    constructor(context: Context, data: MutableList<String>) {
        this.context = context
        this.data = data
    }

    fun setColorTitle(title: Int): ActionSheet {
        ActionSheetData.colorTitle = title
        return actionSheet
    }

    fun hideTitle(): ActionSheet {
        ActionSheetData.titleDisable = true
        return actionSheet
    }

    fun setColorTitleCancel(colorCancel: Int): ActionSheet {
        ActionSheetData.colorCancel = colorCancel
        return actionSheet
    }

    fun setColorBackground(colorBackground: Int): ActionSheet {
        ActionSheetData.colorBackground = colorBackground
        return actionSheet
    }

    fun setColorData(colorData: Int): ActionSheet {
        ActionSheetData.colorData = colorData
        return actionSheet
    }

    fun setColorSelected(colorSelect: Int): ActionSheet {
        ActionSheetData.colorSelect = colorSelect
        return actionSheet
    }

    fun setTitle(title: String): ActionSheet {
        ActionSheetData.title = title
        return actionSheet
    }

    fun setCancelTitle(titleCancel: String): ActionSheet {
        ActionSheetData.titleCancel = titleCancel
        return actionSheet
    }

    // add new feature

    fun setFontTitle(fontTitle: Int): ActionSheet {
        ActionSheetData.fontTitle = fontTitle
        return actionSheet
    }

    fun setSizeTextTitle(sizeTitle: Float): ActionSheet {
        ActionSheetData.sizeTextTitle = sizeTitle
        return actionSheet
    }

    fun setFontCancelTitle(fontCancel: Int): ActionSheet {
        ActionSheetData.fontCancel = fontCancel
        return actionSheet
    }

    fun setSizeTextCancel(sizeCancel: Float): ActionSheet {
        ActionSheetData.sizeTextCancel = sizeCancel
        return actionSheet
    }

    fun setFontData(fontData: Int): ActionSheet {
        ActionSheetData.fontData = fontData
        return actionSheet
    }

    fun setSizeTextData(sizeTextData: Float): ActionSheet {
        ActionSheetData.sizeTextData = sizeTextData
        return actionSheet
    }

    fun create(actionSheetCallBack: ActionSheetCallBack) {
        val adb = AlertDialog.Builder(context)
        val v = LayoutInflater.from(context).inflate(R.layout.action_sheet, null)

        title = v.findViewById(R.id.tvTitle)
        cancel = v.findViewById(R.id.tvCancelAction)
        lineTitle = v.findViewById(R.id.line_title)
        lineContent = v.findViewById(R.id.line_content)
        lineCancel = v.findViewById(R.id.line_cancel)

        setData()
        setFontAndSizeText()

        myRecyclerView = v.findViewById(R.id.myRecyclerview)
        myRecyclerView.layoutManager = LinearLayoutManager(context)
        myRecyclerView.adapter = recyclerviewAdapter
        recyclerviewAdapter.notifyDataSetChanged()

        adb.setView(v)
        alertDialog = adb.create()
        alertDialog?.window?.attributes?.windowAnimations = R.style.DialogAnimations_SmileWindow
        alertDialog?.setCancelable(false)
        alertDialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()

        recyclerviewAdapter.onclickCallback(object : OnClickListener {
            override fun onclick(string: String, position: Int) {
                alertDialog?.dismiss()
                actionSheetCallBack.data(string, position)
            }
        })

        cancel.setOnClickListener { alertDialog?.dismiss() }

        if (ActionSheetData.titleDisable) {
            lineTitle.visibility = View.GONE
        }
    }

    private fun setFontAndSizeText() {
        if (ActionSheetData.fontTitle != 0) {
            try {
                val typefaceTitle = ResourcesCompat.getFont(context, ActionSheetData.fontTitle)
                title.typeface = typefaceTitle
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }

        if (ActionSheetData.fontCancel != 0) {
            try {
                val typefaceCancel = ResourcesCompat.getFont(context, ActionSheetData.fontCancel)
                cancel.typeface = typefaceCancel
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }

        if (ActionSheetData.sizeTextTitle != 0f) {
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ActionSheetData.sizeTextTitle)
        }
        if (ActionSheetData.sizeTextCancel != 0f) {
            cancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, ActionSheetData.sizeTextCancel)
        }
    }


    private fun setData() {
        title.text = ActionSheetData.title
        cancel.text = ActionSheetData.titleCancel
        title.setTextColor(ActionSheetData.colorTitle)
        cancel.setTextColor(ActionSheetData.colorCancel)
        recyclerviewAdapter.color = ActionSheetData.colorData
        recyclerviewAdapter.colorSelect = ActionSheetData.colorSelect
        recyclerviewAdapter.fontText = ActionSheetData.fontData
        recyclerviewAdapter.sizeText = ActionSheetData.sizeTextData
        // Set background color
        val drawableContent: GradientDrawable = lineContent.background as GradientDrawable
        drawableContent.setStroke(3, ActionSheetData.colorBackground)
        drawableContent.setColor(ActionSheetData.colorBackground)
        lineContent.background = drawableContent
        val drawableCancel: GradientDrawable = lineCancel.background as GradientDrawable
        drawableCancel.setStroke(3, ActionSheetData.colorBackground)
        drawableCancel.setColor(ActionSheetData.colorBackground)
        lineCancel.background = drawableCancel
    }

    object ActionSheetData {
        var title = ""
        var titleCancel = ""
        var colorBackground = Color.parseColor("#FFFFFF")
        var colorData = Color.parseColor("#5EA1D6")
        var colorCancel = Color.parseColor("#5EA1D6")
        var colorTitle = Color.parseColor("#AFAFAF")
        var colorSelect = Color.parseColor("#FAFF1E1E")
        var titleDisable = false
        var fontTitle = 0
        var fontData = 0
        var fontCancel = 0
        var sizeTextTitle = 0f
        var sizeTextCancel = 0f
        var sizeTextData = 0f
    }
}
