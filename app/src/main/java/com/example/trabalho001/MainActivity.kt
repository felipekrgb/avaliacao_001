package com.example.trabalho001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.trabalho001.ui.main.MainFragment
import com.example.trabalho001.ui.main.UsersListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        replaceFrag(MainFragment.newInstance())

        findViewById<BottomNavigationView>(R.id.bottomNav).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.ic_github -> replaceFrag(MainFragment.newInstance())
                    R.id.ic_people -> replaceFrag(UsersListFragment.newInstance())
                }
                true
            }
        }

    }

    private fun replaceFrag(fragment: Fragment): Unit {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).commit()
    }
}