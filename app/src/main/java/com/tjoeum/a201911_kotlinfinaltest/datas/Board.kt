package com.tjoeum.a201911_kotlinfinaltest.datas

import android.service.autofill.UserData
import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Board :Serializable{

    var id = 0
    var phoneNum = ""
    var title = ""
    var content  = ""
    var createdAt = Calendar.getInstance()
    var writer = User()

    var serverTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var sdf =  SimpleDateFormat("yyyy년 M월 d일 \n H시 m분")




    fun getFormattedCreatedAt() : String {
        return sdf.format(this.createdAt.time)
    }

    companion object{


        fun getBoardFromJson(jsonObject: JSONObject) : Board {

            val board = Board()
            board.id = jsonObject.getInt("id")
            board.title = jsonObject.getString("title")
            board.phoneNum = jsonObject.getString("phone_num")
            board.content = jsonObject.getString("content")
            board.createdAt.time = board.serverTimeFormat.parse(jsonObject.getString("created_at"))
            board.writer = User.getUserFromJson(jsonObject.getJSONObject("writer"))

            return board
        }
    }



}