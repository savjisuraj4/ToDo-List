package com.example.todolist.databasse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TasksDuo {
    @Query("SELECT * FROM Tasks")
    fun getAllData():List<TasksModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addTask(vararg tasksModel: TasksModel)

     @Update(onConflict = OnConflictStrategy.REPLACE)
     fun updateTask(vararg tasksModel: TasksModel)

     @Delete
     fun deleteTask(vararg tasksModel: TasksModel)
}