package com.tjoeum.a201911_kotlinfinaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var lastBackBtuttonPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvent()
        setValues()
    }

    override fun onBackPressed() {
//        super.onBackPressed()

        var currentTime = System.currentTimeMillis()

        if(currentTime - lastBackBtuttonPressedTime > 1500){
            Toast.makeText(mContext, "한번 더 뒤로 버튼을 누르면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show()
        }else{
            finish()
        }

        lastBackBtuttonPressedTime = currentTime

    }

    override fun setupEvent() {
    }

    override fun setValues() {
        lottoBtn.setOnClickListener {
            intent = Intent(mContext, LottoActivity::class.java)
            startActivity(intent)
        }
    }
}
