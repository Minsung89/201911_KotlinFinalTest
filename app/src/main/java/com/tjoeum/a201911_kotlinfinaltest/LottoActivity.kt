package com.tjoeum.a201911_kotlinfinaltest

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {

    var mHandle = Handler()

    //사용금액
    var usedMoney = 0L
    //누적금액
    var luckMoney = 0L
    // 당첨 횟수
    var firstRankCount = 0L
    var secondRankCount = 0L
    var thirdRankCount = 0L
    var fourtRankCount = 0L
    var fifthRankCount = 0L
    var wrongRankCount = 0L
    var rankArrayList = ArrayList<TextView>()

    var bonusNum = 0

    var lottoNumArrayList = ArrayList<Int>()
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    var myNumArrayList = ArrayList<Int>()
    var myNumTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)

        setupEvent()
        setValues()

    }

    override fun setupEvent() {

        buyOneLottoBtn.setOnClickListener {
            setThisWeekLottoNum()

            checkLottoRank()

            usedMoney += 1000
            usedMoneyTxt.text = String.format("사용 금액 : %,d원", usedMoney)
        }

        autoLottoBtn.setOnClickListener {
            doLottoLoop()
        }

    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)

        myNumTextViewArrayList.add(myLottoNumTxt1)
        myNumTextViewArrayList.add(myLottoNumTxt2)
        myNumTextViewArrayList.add(myLottoNumTxt3)
        myNumTextViewArrayList.add(myLottoNumTxt4)
        myNumTextViewArrayList.add(myLottoNumTxt5)
        myNumTextViewArrayList.add(myLottoNumTxt6)

        rankArrayList.add(firstRankCountTxt)
        rankArrayList.add(secondRankCountTxt)
        rankArrayList.add(thirdRankCountTxt)
        rankArrayList.add(fourtRankCountTxt)
        rankArrayList.add(fifthRankCountTxt)
        rankArrayList.add(wrongRankCountTxt)

        for (myTv in myNumTextViewArrayList) {
            myNumArrayList.add(myTv.text.toString().toInt())
        }

    }


    fun checkLottoRank() {

        //6 : 1등 => 20억원
        //5 : 3등 => 150만원
        //4 : 4등 => 5만원
        //3 : 5등 => 5천원
        //그 이하 => 0원

        var correctCount = 0

        for (myNum in myNumArrayList) {

            if (lottoNumArrayList.contains(myNum)) {
                correctCount++
            }

        }

        when (correctCount) {
            3 -> {
                Toast.makeText(mContext, "5등 당첨!", Toast.LENGTH_SHORT).show()
                luckMoney += 5000
                fifthRankCount ++
                rankArrayList[4].text = String.format("5등 횟수 : %,d", fifthRankCount)

            }
            4 -> {
                Toast.makeText(mContext, "4등 당첨!", Toast.LENGTH_SHORT).show()
                luckMoney += 50000
                fourtRankCount ++
                rankArrayList[3].text = String.format("4등 횟수 : %,d", fourtRankCount)
            }
            5 -> {
                Toast.makeText(mContext, "3등 당첨!", Toast.LENGTH_SHORT).show()
                luckMoney += 1500000
                thirdRankCount ++
                rankArrayList[2].text = String.format("3등 횟수 : %,d", thirdRankCount)

            }
            6 -> {
                Toast.makeText(mContext, "1등 당첨!", Toast.LENGTH_SHORT).show()
                luckMoney += 2000000000
                firstRankCount ++
                rankArrayList[0].text = String.format("1등 횟수 : %,d", firstRankCount)


            }
            else -> {
                Toast.makeText(mContext, "꽝!", Toast.LENGTH_SHORT).show()
                luckMoney += 0
                wrongRankCount ++
                rankArrayList[5].text = String.format("꽝! 횟수 : %,d", wrongRankCount)
            }
        }
        luckMoneyTxt.text = String.format("누적 당첨 금액 : %,d원", luckMoney)

    }

    fun doLottoLoop(){
        mHandle.post {
            if(usedMoney < 100000000){
                setThisWeekLottoNum()
                checkLottoRank()
                usedMoney += 1000
                usedMoneyTxt.text = String.format("사용 금액 : %,d원", usedMoney)

                doLottoLoop()
            }
            else{
                runOnUiThread {
                    Toast.makeText(mContext,"로또 구매를 종료합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    fun setThisWeekLottoNum() {

//        당첨번호는 모두 날리고 다시 뽑자
        lottoNumArrayList.clear()

//        6개의 텍스트뷰에 들어갈 당첨번호를 뽑아내는 반복문
        for (lottoNumTxt in thisWeekLottoNumTextViewArrayList) {

//            선정된 랜덤값이 들어갈 변수
            var randomNum = 0

//            몇번을 반복해야 중복을 피할지 알 수 없다.
//            반복문 : 횟수가 명확하면 for. 언제까지 돌려야할지 모르면 while(true) => if(조건) break
            while (true) {

//                일단 중복되지 않는다.(괜찮다)라고 전제하고 검사 시작
                var isDuplOk = true

//                1~45 사이의 랜덤값을 뽑아서 변수에 임시 저장
                randomNum = (Math.random() * 45 + 1).toInt()

//                지금까지 만든 당첨번호를 모두 꺼내어보자.
                for (num in lottoNumArrayList){

//                    지금 만든 랜덤번호와 꺼내본 당첨번호가 같은가?
                    if(num == randomNum) {
//                        중복되는 숫자를 발견!
//                        더이상 중복검사를 통과할 수 없다.
                        isDuplOk = false
                        break
                    }
                }
//              중복검사를 통과했는지 확인.
                if(isDuplOk) {
//                    만약에 통과했다면 당첨번호로 넣어주자.
                    lottoNumArrayList.add(randomNum)
//                    올바른 번호를 뽑았으니 무한반복을 탈출
                    break
                }
            }
//              순서가 제멋대로여서 보기 안좋다.
//            lottoNumTxt.text = randomNum.toString()
        }
//      당첨번호 6개를 작은 숫자부터 큰 숫자 순서대로(정렬)!
        Collections.sort(lottoNumArrayList)

//        6개의 텍스트뷰 / 숫자를 뽑아내서 연결.
        for (i in 0..lottoNumArrayList.size-1) {
            var numTxt = thisWeekLottoNumTextViewArrayList.get(i)
            var number = lottoNumArrayList.get(i)

            numTxt.text = number.toString()
        }

    }


}
