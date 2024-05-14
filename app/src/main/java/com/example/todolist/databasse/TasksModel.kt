package com.example.todolist.databasse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class TasksModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "task_heading") val taskHeading: String,
    @ColumnInfo(name = "task_description") val taskDescription: String,
    @ColumnInfo(name = "start_date") val startDate: Long,
    @ColumnInfo(name = "end_date") val endDate: Long,
)
