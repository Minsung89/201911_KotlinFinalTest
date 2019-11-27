package com.tjoeum.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeum.a201911_kotlinfinaltest.adapters.ViewPagerAdapter
import com.tjoeum.a201911_kotlinfinaltest.datas.User
import com.tjoeum.a201911_kotlinfinaltest.utils.GlobalData
import kotlinx.android.synthetic.main.activity_board.*

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

        myVP.adapter = ViewPagerAdapter(supportFragmentManager)


    }
}
