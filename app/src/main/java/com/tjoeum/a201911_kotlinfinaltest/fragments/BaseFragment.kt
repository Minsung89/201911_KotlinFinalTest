package com.tjoeum.a201911_kotlinfinaltest.fragments

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    var mContext = activity

    abstract fun setupEvent()

    abstract fun setValues()

}