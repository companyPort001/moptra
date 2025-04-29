package com.example.myapplication.Repository

import com.example.myapplication.Domain.OngoingDomain

class MainRepository {
    val item = listOf(
        OngoingDomain("Apply Leave","aleave"),
        OngoingDomain("Leaves","leave_icon"),
        OngoingDomain("Attendance","atten"),
        OngoingDomain("Trainings","train"),
        OngoingDomain("Pending Request","preq"),
        OngoingDomain("Policies","pol")


    )
    
    fun getAnnouncement(): String {
        return "🔔 Welcome! Don't forget to submit your timesheet by Friday."
    }
}