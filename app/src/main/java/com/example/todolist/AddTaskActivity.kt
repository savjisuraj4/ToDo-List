package com.example.todolist

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databasse.TasksModel
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
@Suppress("DEPRECATION")
class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private var startDate: Long = 0
    private var endDate: Long = 0
    private var taskID=0
    private var operation="ADD"
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras?.getString("TaskModelTOUpdate")
        if (bundle != null) {
            try {
                setValues(bundle)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }

        binding.startDateEditText.setOnClickListener {
            showDatePickerDialog {
                startDate = it
                binding.startDateEditText.text =
                    SimpleDateFormat("dd/MM/yyyy").format(Date(it)).toString()
            }
        }
        binding.endDateEditText.setOnClickListener {
            showDatePickerDialog {
                endDate = it
                binding.endDateEditText.text =
                    SimpleDateFormat("dd/MM/yyyy").format(Date(it)).toString()
            }
        }
        binding.operationButton.setOnClickListener {
            if (binding.startDateEditText.text.isNullOrEmpty() ||
                binding.endDateEditText.text.isNullOrEmpty() ||
                binding.headingEditText.text.isNullOrEmpty() ||
                binding.descriptionEditText.text.isNullOrEmpty() || startDate.toInt() == 0 || endDate.toInt() == 0
            ) {
                Toast.makeText(
                    applicationContext,
                    "All Fields Are Compulsory !!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if(startDate-endDate>0){
                Toast.makeText(
                    applicationContext,
                    "Invalid Start and End Date",
                    Toast.LENGTH_SHORT
                ).show()
        }
            else {
                makeOperation()
            }
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun setValues(bundle: String) {
        binding.toolbar2.title="Update Task"
        binding.operationButton.text = "Update"
        val data = Gson().fromJson(bundle, TasksModel::class.java)
        binding.headingEditText.setText(data.taskHeading)
        binding.descriptionEditText.setText(data.taskDescription)
        binding.startDateEditText.text =
            SimpleDateFormat("dd/MM/yyyy").format(Date(data.startDate)).toString()
        binding.endDateEditText.text =
            SimpleDateFormat("dd/MM/yyyy").format(Date(data.endDate)).toString()
        operation="UPDATE"
        startDate=data.startDate
        endDate=data.endDate
        taskID=data.id
    }

    private fun makeOperation() {
        try {
            val newTask = TasksModel(
                id = taskID,
                taskHeading = binding.headingEditText.text.toString(),
                taskDescription = binding.headingEditText.text.toString(),
                startDate = startDate,
                endDate = endDate
            )
            AsyncProcess().execute(Triple(applicationContext, newTask, operation.uppercase())).get()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }

    private fun showDatePickerDialog(onDateSelected: (Long) -> Unit) {
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                onDateSelected(cal.timeInMillis)
            }
        DatePickerDialog(
            this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}