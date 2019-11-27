package com.tjoeum.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Notice :Serializable{

    var id = 0
    var title = ""
    var content  = ""
    var createdAt = Calendar.getInstance()

    var serverTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var sdf =  SimpleDateFormat("yyyy년 M월 d일 \n H시 m분")

    fun getFormattedCreatedAt() : String {
        return sdf.format(this.createdAt.time)
    }

    companion object{


        fun getNoticeFromJson(jsonObject: JSONObject) : Notice {
            val notice = Notice()
            notice.id = jsonObject.getInt("id")
            notice.title = jsonObject.getString("title")
            notice.content = jsonObject.getString("content")
            notice.createdAt.time = notice.serverTimeFormat.parse(jsonObject.getString("created_at"))

            return notice
        }
    }



}