package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databasse.TasksModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
@Suppress("DEPRECATION")
class AdapterClass(private val context: Context, list: List<TasksModel>) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {
    private var taskList = ArrayList<TasksModel>()

    init {
        taskList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskHeading.apply {
            this.typeface = ResourcesCompat.getFont(context, R.font.acme)
            this.text = taskList[position].taskHeading
        }
        holder.deleteTask.setOnClickListener {
            try {
                val ss = AsyncProcess().execute(Triple(context, taskList[position], "DELETE")).get()
                taskList.clear()
                taskList.addAll(ss)
                notifyDataSetChanged()
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
        holder.updateTask.setOnClickListener {
            try {
                val intent = Intent(context, AddTaskActivity::class.java)
                intent.putExtra("TaskModelTOUpdate", Gson().toJson(taskList[position]))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.d("Error123", e.toString())
            }
        }
        holder.startDateTextView.text =
            "Start Date:  ${SimpleDateFormat("dd/MM/yyyy").format(Date(taskList[position].startDate))}"
        holder.endDateTextView.text =
            "End Date: ${SimpleDateFormat("dd/MM/yyyy").format(Date(taskList[position].endDate))}"
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskHeading: TextView = itemView.findViewById(R.id.task_heading)
        val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
        val startDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        val updateTask: ImageView = itemView.findViewById(R.id.updateTask)
        val deleteTask: ImageView = itemView.findViewById(R.id.deleteTask)
    }
}