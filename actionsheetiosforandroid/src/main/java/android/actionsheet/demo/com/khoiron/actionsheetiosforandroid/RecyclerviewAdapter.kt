package android.actionsheet.demo.com.khoiron.actionsheetiosforandroid

import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.Interface.onclikListener
import android.graphics.Color
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by khoiron on 01/06/18.
 */
class RecyclerviewAdapter( var data: MutableList<String>): RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {

    lateinit var onclik :onclikListener

    var color = Color.parseColor("#5EA1D6")
    var colorSelect = Color.parseColor("#FAFF1E1E")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent!!.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false)

        return MyViewHolder(itemView)
//        return  MyViewHolder(RecyclerviewAdapterUi().createView(AnkoContext.create(parent!!.context, parent)))
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
        holder.textView.setTextColor(color)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val textView = v.findViewById(R.id.tvName) as TextView
        val linebottom = v.findViewById(R.id.linebottom) as LinearLayout
    }

    fun onclikCallback(onclikListener: onclikListener){
        this.onclik =  onclikListener
    }

}