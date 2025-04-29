package com.example.myapplication.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityLeaveBinding
import com.example.myapplication.databinding.CstmInfoDailogBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.CstmLeaveDailogBinding

class LeaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the ViewBinding for the activity
        binding = ActivityLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listeners for leave info
        binding.info1.setOnClickListener {
            showDialog(
                "Earn Leave",
                """
        1. Used for short-term, unplanned absences for personal reasons. Ideally, apply at least 2 weeks before day in advance.
        2. EL shall be applied in advance. However, in case of emergency, employee needs to take an approval on the same day by any mode of communication.
        3. EL shall not be clubbed with General Leave / Sick Leave.
        4. Any Earn leaves will be carried forward into the next year, allowing employees to use them in the future.
        """.trimIndent()
            )
        }

        binding.info2.setOnClickListener {
            showDialog(
                "Sick Leave",
                """
        1. SL is for personal illness only.
        2. SL shall not be clubbed with CL.
        3. If SL is for 3 consecutive days or more, it is mandatory to submit a doctor’s certificate/Prescription containing nature of ailment / relevant reports.
        
        Note: Accumulated sick leave balance, if any (applicable only to those employees who have joined before 1st April 2024), will be carried forward till it is fully utilized by the employee.
        """.trimIndent()
            )
        }

        binding.info3.setOnClickListener {
            showDialog(
                "Maternity Leave",
                """
        1. ML Eligibility: ML can be granted only to a female employee who has worked for a period of not less than 80 days in the 12 months immediately preceding the date of her expected delivery.
        2. ML Duration: The ML period shall not exceed 26 weeks (including holidays & weekly offs) for two surviving children and 12 weeks for more than two surviving children.
        3. Commissioning Mother & Adopting Mother: Commissioning mothers and adopting mothers (who legally adopt a child below the age of three months) shall be entitled to maternity benefit for a period of 12 weeks from the date the child is handed over.
        4. Application: Employees who need to avail ML will be required to make an application to their immediate Reporting Manager at least 2 months in advance to ensure proper handover of their current roles & responsibilities.
        5. Doctor's Certificate: The approved application along with the Doctor's certificate certifying pregnancy and the expected date of delivery should be submitted to the HR Department before the employee goes on leave.
        6. Leave Encashment: ML can be clubbed with any leave, and an employee on approved ML will receive full pay during the period.
        7. Miscarriage or Medical Termination: In case of miscarriage or medical termination of pregnancy subject to production of proofs as may be prescribed, an employee shall be entitled to take leave for a period of six weeks immediately following the days of her miscarriage or medical termination of pregnancy.
        8. ESIC Benefits: Employees governed by the ESIC Act shall be entitled to ESIC benefits as per the applicable rules and regulations.
        9. This policy will be governed by the regulatory requirements of Maternity Act, 1961 and read along with amendments thereof.
        """.trimIndent()
            )
        }

        binding.info4.setOnClickListener {
            showDialog(
                "Marriage Leave",
                """
        1. The company will provide 2 days of paid leave specifically for marriage purposes.
        2. Any additional leave required will be adjusted against the employee's existing leave balance (e.g., earned leave, casual leave).
        3. Approval of marriage leave and its duration is subject to management's discretion, based on workload and business requirements.
        """.trimIndent()
            )
        }

        // Handle clicks for days_btn1, days_btn2, days_btn3, days_btn4
        binding.daysBtn1.setOnClickListener {
            showDialogDay("Earn Leave")
        }

        binding.daysBtn2.setOnClickListener {
            showDialogDay("Sick Leave")
        }

        binding.daysBtn3.setOnClickListener {
            showDialogDay("Maternity Leave")
        }

        binding.daysBtn4.setOnClickListener {
            showDialogDay("Marriage Leave")
        }

        // Apply the "coming from below" animation to GridLayout
        animateGridLayout()
    }

    private fun animateGridLayout() {
        // Apply a TranslateAnimation to the GridLayout
        val animation = android.view.animation.TranslateAnimation(0f, 0f, 1000f, 0f) // Moves from 1000px (below the screen) to the original position
        animation.duration = 800 // Duration of the animation
        animation.fillAfter = true // Keep the final position after animation ends

        // Start the animation on the GridLayout
        binding.gridLayout.startAnimation(animation)
    }

    private fun showDialog(title: String, message: String) {
        // Inflate the custom dialog layout
        val dialogBinding = CstmInfoDailogBinding.inflate(LayoutInflater.from(this))
        val dialogTitle: TextView = dialogBinding.dialogTitle
        val dialogMessage: TextView = dialogBinding.dialogMessage
        val dialogCloseBtn: Button = dialogBinding.dialogCloseBtn

        // Set the title and message dynamically
        dialogTitle.text = title
        dialogMessage.text = message

        // Create the dialog with the custom layout
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root) // Use the root view of the dialog's binding
            .create()

        // Set close button listener
        dialogCloseBtn.setOnClickListener {
            dialog.dismiss()
        }




        // Show the dialog
        dialog.show()
    }

    private fun showDialogDay(title: String) {
        // Inflate the custom dialog layout
        val dialogBinding = CstmLeaveDailogBinding.inflate(LayoutInflater.from(this))
        val dialogTitle: TextView = dialogBinding.dialogTitle
        val dialogCloseBtn: Button = dialogBinding.dialogCloseBtn

        // Set the title and message dynamically
        dialogTitle.text = title

        // Create the dialog with the custom layout
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root) // Use the root view of the dialog's binding
            .create()

        // Set close button listener
        dialogCloseBtn.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }
}
