package com.tjoeum.a201911_kotlinfinaltest

import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*

class LottoActivity : BaseActivity() {

    var thisWeekLottoNumArrayList = ArrayList<Int>()
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)

        setupEvent()
        setValues()

    }

    override fun setupEvent() {

        buyOneLottoBtn.setOnClickListener {
            setThisWeekLottoNum()
        }

    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)
    }


    fun setThisWeekLottoNum(){


        for (lottoNumTxt in thisWeekLottoNumTextViewArrayList){
            var isNumber = true
            var randomNum = 0
            while (isNumber){
                randomNum = (Math.random() * 45 +1).toInt()
                for (num in thisWeekLottoNumArrayList){
                    if(randomNum == num){
                        break
                    }
                    isNumber = false
                }
            }
            thisWeekLottoNumArrayList.add(randomNum)
            lottoNumTxt.text = randomNum.toString()
        }



    }


}
