package com.example.myapplication.Activity


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment

import com.example.myapplication.R

import com.example.myapplication.databinding.ActivityDashboradBinding


class Dashborad : AppCompatActivity() {

    private lateinit var binding: ActivityDashboradBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboradBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replace(homef())

        binding.BNV.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->replace(homef())
                R.id.Documents->replace(DocfFragment())
                R.id.PunchImg->replace(PunchInOut())
                R.id.Attendence->replace(attendencef())
                R.id.More->replace(moref())
            }
            true
        }
    }


    private fun replace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }



}
