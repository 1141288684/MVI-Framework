package com.example.mvi.activity

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mvi.R
import com.koader.jrouter.Just

class SecFragment :Fragment(R.layout.fragment_sec){

    @Just("id")
    lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view?.findViewById<TextView>(R.id.tv_test)?.text=id
    }
}