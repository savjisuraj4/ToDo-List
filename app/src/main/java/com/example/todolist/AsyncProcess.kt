package com.example.todolist

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.example.todolist.databasse.AppDatabase
import com.example.todolist.databasse.TasksModel
@Suppress("DEPRECATION")
class AsyncProcess:AsyncTask<Triple<Context,TasksModel?,String>,Int,List<TasksModel>>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Triple<Context, TasksModel?,String>): List<TasksModel> {

        val db =
            Room.databaseBuilder(params[0].first, AppDatabase::class.java, "Tasks").build()
        val taskDuo = db.tasksDuo()

        if(params[0].second==null || params[0].third.uppercase() == "GET"){
            return taskDuo.getAllData()
        }

        if(params[0].second!=null && params[0].third.uppercase() == "UPDATE"){
            taskDuo.updateTask(params[0].second!!).toString()
        }
        if(params[0].second!=null && params[0].third.uppercase() == "DELETE"){
            taskDuo.deleteTask(params[0].second!!).toString()
        }

        if(params[0].second!=null && params[0].third.uppercase() == "ADD"){
            taskDuo.addTask(params[0].second!!)
        }
        return taskDuo.getAllData()
    }

}