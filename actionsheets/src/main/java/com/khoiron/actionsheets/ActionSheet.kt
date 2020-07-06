package com.khoiron.actionsheets

import com.khoiron.actionsheets.ActionSheet.mData.titleDisable
import com.khoiron.actionsheets.`interface`.OnClickListener
import android.support.v7.widget.LinearLayoutManager
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.widget.TextView
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import com.khoiron.actionsheets.callback.ActionSheetCallBack
import java.util.*

/**
 * Created by khoiron on 01/06/18.
 */

class ActionSheet {

    lateinit var context :Context
    var data : MutableList<String> = ArrayList<String>()
    val actionSheet by lazy { ActionSheet(context, data) }

    var alertDialog: AlertDialog? = null
    lateinit var title : TextView
    lateinit var cancle : TextView
    lateinit var myRecyclerView: RecyclerView
    lateinit var line_title : LinearLayout
    val RecyclerviewAdapter by lazy { RecyclerviewAdapter(data, context) }

    constructor(context: Context,data :MutableList<String>)  {
        this.context = context
        this.data = data
    }

    fun setColorTitle(title: Int): ActionSheet {
        mData.colorTitle = title
        return actionSheet
    }

    fun hideTitle(): ActionSheet {
        titleDisable = true
        return actionSheet
    }


    fun setColorTitleCancel(title: Int): ActionSheet {
        mData.colorCancle = title
        return actionSheet
    }

    fun setColorData(title: Int): ActionSheet {
        mData.colorData = title
        return actionSheet
    }

    fun setColorSelected(title: Int): ActionSheet {
        mData.colorSelect = title
        return actionSheet
    }

    fun setTitle(title: String): ActionSheet {
        mData.title = title
        return actionSheet
    }

    fun setCancelTitle(title: String): ActionSheet {
        mData.titleCancel = title
        return actionSheet
    }

    // add new fitur

    fun setFontTitle(fontTitle: Int): ActionSheet {
        mData.fontTitle = fontTitle
        return actionSheet
    }

    fun setSizeTextTitle(sizeTitle:Float): ActionSheet {
        mData.sizeTextTitle = sizeTitle
        return actionSheet
    }

    fun setFontCancelTitle(fontCancle: Int): ActionSheet {
        mData.fontCancel = fontCancle
        return actionSheet
    }

    fun setSizeTextCancel(sizeCancel:Float): ActionSheet {
        mData.sizeTextCancel = sizeCancel
        return actionSheet
    }

    fun setFontData(fontData: Int): ActionSheet {
        mData.fontData = fontData
        return actionSheet
    }

    fun setSizeTextData(sizeTextData:Float): ActionSheet {
        mData.sizeTextData = sizeTextData
        return actionSheet
    }

    fun create( actionSheetCallBack: ActionSheetCallBack){
        val adb     = AlertDialog.Builder(context)
        val v       = LayoutInflater.from(context).inflate(R.layout.action_sheet,null)
        title       = v.findViewById(R.id.tvTitle)
        cancle      = v.findViewById(R.id.tvCancelAction)
        line_title  = v.findViewById(R.id.line_title)

        setData()
        setFontAndSizeText()

        myRecyclerView = v.findViewById(R.id.myRecyclerview)
        myRecyclerView.setLayoutManager(LinearLayoutManager(context))
        myRecyclerView.setAdapter(RecyclerviewAdapter)
        RecyclerviewAdapter.notifyDataSetChanged()

        adb.setView(v)
        alertDialog = adb.create()
        alertDialog?.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimations_SmileWindow;//R.style.DialogAnimations_SmileWindow;
        alertDialog?.setCancelable(false)
        alertDialog?.getWindow()?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        alertDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()

        RecyclerviewAdapter.onclikCallback(object : OnClickListener {
            override fun onclik(string: String, position: Int) {
                alertDialog?.dismiss()
                actionSheetCallBack.data(string,position)
            }
        })

        cancle.setOnClickListener { alertDialog?.dismiss() }

        if (titleDisable){
            line_title.visibility = View.GONE
        }
    }

    private fun setFontAndSizeText() {
        if (mData.fontTitle !=0){
            try {
                val typefaceTitle = ResourcesCompat.getFont(context, mData.fontTitle)
                title.setTypeface(typefaceTitle)
            }
            catch (e:Exception){
                Log.e("Error",e.message)
            }
        }

        if (mData.fontCancel !=0){
            try {
                val typefaceCancel = ResourcesCompat.getFont(context, mData.fontCancel)
                cancle.setTypeface(typefaceCancel)
            }catch (e:Exception){
                Log.e("Error",e.message)
            }
        }

        if (mData.sizeTextTitle !=0f){
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, mData.sizeTextTitle)
        }
        if (mData.sizeTextCancel !=0f){
            cancle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mData.sizeTextCancel)
        }
    }


    private fun setData() {
        title.text                      = mData.title
        cancle.text                     = mData.titleCancel
        title.setTextColor(mData.colorTitle)
        cancle.setTextColor(mData.colorCancle)
        RecyclerviewAdapter.color       = mData.colorData
        RecyclerviewAdapter.colorSelect = mData.colorSelect
        RecyclerviewAdapter.fontText    = mData.fontData
        RecyclerviewAdapter.sizeText    = mData.sizeTextData
    }

    object mData{
        var title          = ""
        var titleCancel    = ""
        var colorData  = Color.parseColor("#5EA1D6")
        var colorCancle= Color.parseColor("#5EA1D6")
        var colorTitle = Color.parseColor("#afafaf")
        var colorSelect= Color.parseColor("#FAFF1E1E")
        var titleDisable   = false
        var fontTitle      = 0
        var fontData       = 0
        var fontCancel     = 0
        var sizeTextTitle  = 0f
        var sizeTextCancel = 0f
        var sizeTextData   = 0f
    }

}