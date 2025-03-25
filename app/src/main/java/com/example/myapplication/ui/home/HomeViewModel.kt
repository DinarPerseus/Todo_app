package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var todos = MutableLiveData<List<Todo>>(mutableListOf())

//    fun addTodo(todoTitle: String) {
//        val todo = Todo(todoTitle)
//        todos.value = todos.value?.plus(todo)
//    }
//
//    fun deleteDoneTodos() {
//        todos.value = todos.value?.filter { !it.isChecked }
//
//    }
}