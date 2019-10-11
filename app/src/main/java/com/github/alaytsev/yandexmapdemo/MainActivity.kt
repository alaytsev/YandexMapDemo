package com.github.alaytsev.yandexmapdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isShow = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if (!isShow)
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, MainFragment.newInstance())
                .commitNow()
        isShow = true
    }
}
