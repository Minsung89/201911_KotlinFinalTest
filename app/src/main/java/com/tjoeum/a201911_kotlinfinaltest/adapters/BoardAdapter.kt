package com.tjoeum.a201911_kotlinfinaltest.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjoeum.a201911_kotlinfinaltest.R
import com.tjoeum.a201911_kotlinfinaltest.datas.Board
import com.tjoeum.a201911_kotlinfinaltest.datas.Notice
import java.text.SimpleDateFormat

class BoardAdapter(context: Context, res: Int, list: ArrayList<Board>) : ArrayAdapter<Board>(context,res, list){
    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list:ArrayList<Board>) : this(context, R.layout.board_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.board_list_item, null)
        }

        var row = tempRow!!


        var data = mList[position]

        var imgTxt = row.findViewById<TextView>(R.id.ImgTxt)
        var nameTxt = row.findViewById<TextView>(R.id.writeNameTxt)
        var titleTxt = row.findViewById<TextView>(R.id.writeTitleTxt)
        var contentTxt = row.findViewById<TextView>(R.id.writeContentTxt)

        imgTxt.text = data.writer.category.title.first().toString()

        var bgShape : GradientDrawable  = imgTxt.getBackground() as GradientDrawable
        bgShape.setColor(Color.parseColor(data.writer.category.color))


        nameTxt.text = data.writer.name
        titleTxt.text = data.title
        contentTxt.text = data.content


        return row
    }
}