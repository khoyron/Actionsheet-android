package com.khoiron.actionsheets

import com.khoiron.actionsheets.callback.OnClickListener
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by khoiron on 01/06/18.
 */

class RecyclerviewAdapter(private var data: MutableList<String>, private var context: Context) : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {

    private lateinit var onclick: OnClickListener

    var color = Color.parseColor("#5EA1D6")
    var colorSelect = Color.parseColor("#FAFF1E1E")
    var fontText = 0
    var sizeText = 0f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val string = data[position]

        if (position == data.size - 1) {
            holder.lineBottom.visibility = View.GONE
        }

        holder.textView.text = string
        holder.itemView.setOnClickListener {
            holder.textView.setTextColor(colorSelect)
            Handler(Looper.getMainLooper()).postDelayed({
                onclick.onclick(string, position)
            }, 10)
        }

        if (fontText != 0) {
            try {
                val typefaceCancel = ResourcesCompat.getFont(context, fontText)
                holder.textView.typeface = typefaceCancel
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }

        if (sizeText != 0f) {
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeText)
        }

        holder.textView.setTextColor(color)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val textView = v.findViewById(R.id.tvName) as AppCompatTextView
        val lineBottom = v.findViewById(R.id.line_bottom) as LinearLayoutCompat
    }

    fun onclickCallback(onclickListener: OnClickListener) {
        this.onclick = onclickListener
    }

}