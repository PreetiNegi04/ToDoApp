package com.preeti.inventory.ui

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.*
import com.preeti.inventory.data.Task
import com.preeti.inventory.data.TaskDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val taskList : LiveData<List<Task>> = taskDao.getAll().asLiveData()

    //insert
    private fun insert(task: Task){
        viewModelScope.launch { taskDao.insert(task) }
    }

    //delete
    fun delete(task: Task){
        viewModelScope.launch { taskDao.delete(task) }
    }

    //update
    private fun update(task: Task){
        viewModelScope.launch { taskDao.update(task) }
    }

    fun addItem(title:String,isComplete:Boolean = false,rank : Int = 0):Boolean{
        val task = Task(title = title, isComplete = isComplete, rank = rank)
        if(isTaskValid(task)) {
            insert(task)
            return true
        }
        return false
    }

    fun isTaskValid(Task:Task):Boolean{
        return Task.title.isNotBlank()
    }

    fun get(id:Int):LiveData<Task>{
        return taskDao.getTask(id).asLiveData()
    }
}

class InventoryViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}