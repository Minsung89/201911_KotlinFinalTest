package com.tjoeum.a201911_kotlinfinaltest.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjoeum.a201911_kotlinfinaltest.R
import com.tjoeum.a201911_kotlinfinaltest.datas.Notice
import java.text.SimpleDateFormat

class NoticeAdapter(context: Context, res: Int, list: ArrayList<Notice>) : ArrayAdapter<Notice>(context,res, list){
    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list:ArrayList<Notice>) : this(context, R.layout.notice_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.notice_list_item, null)
        }

        var row = tempRow!!

        Log.d("목록들(제목)",mList[position].title)

        var noticeTitle = row.findViewById<TextView>(R.id.noticeTitle)
        var noticeContent =  row.findViewById<TextView>(R.id.noticeContent)
        var noticeDate = row.findViewById<TextView>(R.id.noticeDate)

        noticeTitle.text = mList[position].title
        noticeContent.text = mList[position].content
        noticeDate.text =  mList[position].getFormattedCreatedAt()




        return row
    }
}