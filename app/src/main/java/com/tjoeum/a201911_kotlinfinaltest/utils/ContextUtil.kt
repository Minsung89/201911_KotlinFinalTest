package com.tjoeum.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {

    companion object {

        val prefnName = "PracticePrefference"

        val USER_ID = "USER_ID"

        val USER_ID_CHECKED = "USER_ID_CHECKED"

        val USER_TOKEN = "USER_TOKEN"

        fun setUserId(context: Context, userId: String){

            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            pref.edit().putString(USER_ID, userId).apply()

        }

        fun getUserId(context: Context) : String{
            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            return pref.getString(USER_ID,"")!!

        }

        fun setCheckedId(context: Context, userChecked: Boolean){

            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            pref.edit().putBoolean(USER_ID_CHECKED, userChecked).apply()

        }

        fun getCheckedId(context: Context) : Boolean{
            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            return pref.getBoolean(USER_ID_CHECKED,false)!!

        }

        fun setToken(context: Context, token: String){

            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            pref.edit().putString(USER_TOKEN, token).apply()

        }

        fun getToken(context: Context) : String{
            var pref = context.getSharedPreferences(prefnName,Context.MODE_PRIVATE)

            return pref.getString(USER_TOKEN,"")!!

        }
    }
}