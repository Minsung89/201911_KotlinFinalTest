package com.tjoeum.a201911_kotlinfinaltest.fragments

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    var mContext : Context? = null


    abstract fun setupEvent()

    abstract fun setValues()

}