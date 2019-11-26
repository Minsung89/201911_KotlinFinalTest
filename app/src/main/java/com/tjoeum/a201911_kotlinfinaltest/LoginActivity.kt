package com.tjoeum.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeum.a201911_kotlinfinaltest.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_login.*

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
            if(loginIdSaveCb.isChecked)
                ContextUtil.setUserId(mContext,loginIdEdt.text.toString())
        }

    }

    override fun setValues() {

        loginIdSaveCb.isChecked =  ContextUtil.getCheckedId(mContext)
        loginIdEdt.setText(ContextUtil.getUserId(mContext))



    }
}
