package com.hynes.james.fibtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hynes.james.fibtest.ui.input.InputFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, InputFragment.newInstance())
                .commitNow()
        }
    }
}