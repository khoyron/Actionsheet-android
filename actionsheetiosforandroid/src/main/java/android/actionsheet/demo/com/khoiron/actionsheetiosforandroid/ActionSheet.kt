package android.actionsheet.demo.com.khoiron.actionsheetiosforandroid

import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.Interface.ActionSheetCallBack
import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.Interface.onclikListener
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import java.util.*


/**
 * Created by khoiron on 01/06/18.
 */

class ActionSheet {

    lateinit var context :Context
    var data : MutableList<String> = ArrayList<String>()
    val actionSheet by lazy { ActionSheet(context,data) }

    var alertDialog: AlertDialog? = null
    lateinit var title : TextView
    lateinit var cancle : TextView
    lateinit var myRecyclerView: RecyclerView
    val RecyclerviewAdapter by lazy { RecyclerviewAdapter(data) }

    constructor(context: Context,data :MutableList<String>)  {
        this.context = context
        this.data = data
    }

    fun setColorTitle(title: Int): ActionSheet {
        mData.colorTitle = title
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

    fun create( actionSheetCallBack: ActionSheetCallBack){
        val adb = AlertDialog.Builder(context)
        val v = LayoutInflater.from(context).inflate(R.layout.action_sheet,null)
        title = v.findViewById(R.id.tvTitle)
        cancle = v.findViewById(R.id.tvCancelAction)

        setData()

        myRecyclerView = v.findViewById(R.id.myRecyclerview)
        myRecyclerView.setLayoutManager(LinearLayoutManager(context))
        myRecyclerView.setAdapter(RecyclerviewAdapter)
        RecyclerviewAdapter.notifyDataSetChanged()

        adb.setView(v);
        alertDialog = adb.create();
        alertDialog?.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimations_SmileWindow;//R.style.DialogAnimations_SmileWindow;
        alertDialog?.setCancelable(false)
        alertDialog?.getWindow()?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM);
        alertDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        alertDialog?.show()

        RecyclerviewAdapter.onclikCallback(object : onclikListener {
            override fun onclik(string: String, position: Int) {
                alertDialog?.dismiss()
                actionSheetCallBack?.data(string,position)
            }
        })

        cancle.setOnClickListener { alertDialog?.dismiss() }

    }

    private fun setData() {
        title.text = mData.title
        cancle.text = mData.titleCancel
        title.setTextColor(mData.colorTitle)
        cancle.setTextColor(mData.colorCancle)
        RecyclerviewAdapter.color = mData.colorData
        RecyclerviewAdapter.colorSelect = mData.colorSelect
    }

    object mData{
        var title =""
        var titleCancel =""
        var colorData = Color.parseColor("#5EA1D6")
        var colorCancle = Color.parseColor("#5EA1D6")
        var colorTitle = Color.parseColor("#afafaf")
        var colorSelect = Color.parseColor("#FAFF1E1E")
    }


}