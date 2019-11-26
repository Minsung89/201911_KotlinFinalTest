package com.tjoeum.a201911_kotlinfinaltest.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class ServerUtil {

    interface JsonResponseHandler{
        fun onResponse(json: JSONObject)
    }

    companion object{

        val BASE_URL = "http://192.168.0.26:5000"

        fun postRequestLogin(context: Context, userId: String , userPw: String , handler: JsonResponseHandler){

            var client = OkHttpClient()

            var url = "${BASE_URL}/auth"

            var formBody = FormBody.Builder()
                .add("login_id",userId)
                .add("password",userPw)
                .build()


            var request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()


            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(context,"연결 실패",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call, response: Response) {


                    var body = response.body!!.string()
                    Log.d("data",body)

                    var json  = JSONObject(body)
                    handler?.onResponse(json)


                }
            })




        }




    }


}