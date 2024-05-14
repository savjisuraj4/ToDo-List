package com.example.todolist.databasse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TasksModel::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun tasksDuo():TasksDuo

}