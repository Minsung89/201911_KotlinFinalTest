package com.tjoeum.a201911_kotlinfinaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeum.a201911_kotlinfinaltest.datas.User
import com.tjoeum.a201911_kotlinfinaltest.utils.ContextUtil
import com.tjoeum.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvent()
        setValues()


    }
    override fun setupEvent() {
        loginIdSaveCb.setOnCheckedChangeListener { buttonView, isChecked ->
            ContextUtil.setCheckedId(mContext,isChecked)

            if(!isChecked){
                ContextUtil.setUserId(mContext,"")
            }

        }

        loginButton.setOnClickListener {
            ServerUtil.postRequestLogin(mContext,loginIdEdt.text.toString(),loginPwEdt.text.toString(),object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    var code = json.getInt("code")
                    var message = json.getString("message")

                    if(code == 200){
                        Log.d("로그인", message)
                        var data  = json.getJSONObject("data")
                        var token = data.getString("token")
                        ContextUtil.setToken(mContext,token)

                        val userData = User.getUserFromJson(data.getJSONObject("user"))

                        if(loginIdSaveCb.isChecked)
                            ContextUtil.setUserId(mContext,loginIdEdt.text.toString())

                        val intent = Intent(mContext, BoardActivity::class.java)
                        intent.putExtra("user",userData)
                        startActivity(intent)
                        finish()
                    }
                    else if(code == 400){
                        Toast.makeText(mContext,"로그인실패",Toast.LENGTH_SHORT).show()
                    }
                    else{

                        Toast.makeText(mContext,"연결실패",Toast.LENGTH_SHORT).show()
                    }

                }
            })

        }

    }

    override fun setValues() {

        loginIdSaveCb.isChecked =  ContextUtil.getCheckedId(mContext)

        loginIdEdt.setText(ContextUtil.getUserId(mContext))



    }
}
