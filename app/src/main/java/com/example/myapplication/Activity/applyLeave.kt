package com.example.myapplication.Activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityApplyLeaveBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class applyLeave : AppCompatActivity() {

    private lateinit var binding: ActivityApplyLeaveBinding
    private lateinit var btShowDatePickerDialog: Button
    private lateinit var tvSelectDate : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyLeaveBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btShowDatePickerDialog = findViewById(R.id.bt_show_picker)
        tvSelectDate = findViewById(R.id.tv_select_date)






        btShowDatePickerDialog.setOnClickListener {
            val picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.ThemeMaterialCalendar)
                .setTitleText("Select Date Range")

                .setSelection(Pair(

                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()  )).build()

            picker.show(this.supportFragmentManager,"TAG")
            picker.addOnPositiveButtonClickListener {
                tvSelectDate.setText(convertTimeToDate(it.first) + " - " +convertTimeToDate(it.second))
            }
            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }

        }





    }




    private fun convertTimeToDate(time:Long): String {
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        utc.timeInMillis = time
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(utc.time)
    }
}
