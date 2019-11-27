package com.tjoeum.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable

class User :Serializable{

    var id = 0
    var loginId = ""
    var name  = ""
    var phone = ""
    var memo = ""
    var category = Category()

    companion object{
        fun getUserFromJson(jsonObject: JSONObject) : User{

            val user = User()
            user.id = jsonObject.getInt("id")
            user.loginId = jsonObject.getString("login_id")
            user.name = jsonObject.getString("name")
            user.phone = jsonObject.getString("phone")
            user.memo = jsonObject.getString("memo")

            user.category = Category.getCategoryFromJson(jsonObject.getJSONObject("category"))

            return user
        }

    }

    override fun toString(): String {
        return "User(id=$id, loginId='$loginId', name='$name', phone='$phone', memo='$memo', category=$category)"
    }


}