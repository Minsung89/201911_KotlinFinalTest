package com.tjoeum.a201911_kotlinfinaltest.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tjoeum.a201911_kotlinfinaltest.EditBlackListActivity
import com.tjoeum.a201911_kotlinfinaltest.R
import com.tjoeum.a201911_kotlinfinaltest.adapters.BoardAdapter
import com.tjoeum.a201911_kotlinfinaltest.datas.Board
import com.tjoeum.a201911_kotlinfinaltest.datas.Notice
import com.tjoeum.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.fragment_board_list.*
import org.json.JSONObject

class BoardListFragment : BaseFragment(){

    var boardList = ArrayList<Board>()

    var boardAdapter : BoardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = activity

        setupEvent()
        setValues()

    }

    override fun setupEvent() {
        writeBlackListBtn.setOnClickListener {

            val intent = Intent(mContext,EditBlackListActivity::class.java)
            startActivity(intent)

        }
    }

    override fun setValues() {

        boardAdapter = BoardAdapter(mContext!!, boardList)
        boardListView.adapter = boardAdapter
        getBlackListFromServer()

















































































































































































































































































































































































    }

    fun getBlackListFromServer(){

        ServerUtil.getRequestBlackList(mContext!!, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("공지목록응답", json.toString())

                var code = json.getInt("code")
                if(code == 200) {
                    val data = json.getJSONObject("data")
                    val blackLists = data.getJSONArray("black_lists")

                    boardList.clear()
                    for(i in 0..blackLists.length()-1) {
                        boardList.add(Board.getBoardFromJson(blackLists.getJSONObject(i)))

                    }

                    activity!!.runOnUiThread {
                        boardAdapter?.notifyDataSetChanged()

                    }


                } else {
                    Toast.makeText(mContext!!, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

}