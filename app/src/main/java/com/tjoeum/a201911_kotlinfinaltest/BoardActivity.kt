package com.tjoeum.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeum.a201911_kotlinfinaltest.datas.User
import com.tjoeum.a201911_kotlinfinaltest.utils.GlobalData

class BoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        setupEvent()
        setValues()

    }

    override fun setupEvent() {
    }

    override fun setValues() {
        Log.d("가져온값",GlobalData.loginUserData.toString())
    }
}
