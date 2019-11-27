package com.tjoeum.a201911_kotlinfinaltest.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
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


        fun getRequestNoticeList(context:Context, handler:JsonResponseHandler?) {

            val client = OkHttpClient()

            var urlBuilder = "${BASE_URL}/notice".toHttpUrlOrNull()!!.newBuilder()

            var requestUrl = urlBuilder.build().toString()

            Log.d("가공된GETURL", requestUrl)

            val request = Request.Builder()
                .url(requestUrl)
                .header("X-Http-Token",ContextUtil.getToken(context))
//                    만약 헤더가 필요하면 header() 함수 사용
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val body = response.body!!.string()
                    val jsonObject = JSONObject(body)
                    handler?.onResponse(jsonObject)

                }

            })




        }

        fun getRequestBlackList(context:Context, handler:JsonResponseHandler?) {

            val client = OkHttpClient()

            var urlBuilder = "${BASE_URL}/black_list".toHttpUrlOrNull()!!.newBuilder()

            var requestUrl = urlBuilder.build().toString()

            Log.d("가공된GETURL", requestUrl)

            val request = Request.Builder()
                .url(requestUrl)
                .header("X-Http-Token",ContextUtil.getToken(context))
//                    만약 헤더가 필요하면 header() 함수 사용
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val body = response.body!!.string()
                    val jsonObject = JSONObject(body)
                    handler?.onResponse(jsonObject)

                }

            })




        }

    }


}