package com.khoiron.actionsheets

import com.khoiron.actionsheets.`interface`.OnClickListener
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by khoiron on 01/06/18.
 */
class RecyclerviewAdapter( var data: MutableList<String>,var context: Context): RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {

    lateinit var onclik : OnClickListener

    var color       = Color.parseColor("#5EA1D6")
    var colorSelect = Color.parseColor("#FAFF1E1E")
    var fontText        = 0
    var sizeText        = 0f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val string = data.get(position)

        if (position==data.size-1){
            holder.linebottom.visibility = View.GONE
        }

        holder.textView.setText(string)
        holder.itemView.setOnClickListener {
            holder.textView.setTextColor(colorSelect)
            Handler().postDelayed({
                onclik.onclik(string,position)
            }, 10)
        }

        if (fontText!=0){
            try {
                val typefaceCancel = ResourcesCompat.getFont(context, fontText)
                holder.textView.setTypeface(typefaceCancel)
            }catch (e:Exception){
                Log.e("Error",e.message)
            }
        }

        if (sizeText!=0f){
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,sizeText)
        }

        holder.textView.setTextColor(color)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val textView = v.findViewById(R.id.tvName) as TextView
        val linebottom = v.findViewById(R.id.linebottom) as LinearLayout
    }

    fun onclikCallback(onclikListener: OnClickListener){
        this.onclik =  onclikListener
    }

}