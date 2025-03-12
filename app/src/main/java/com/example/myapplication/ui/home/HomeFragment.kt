package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var edtext: EditText
    private lateinit var btnAddTodo: TextView
    private lateinit var del: TextView
    private lateinit var myPreferences: MyPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rvTodoItems = view.findViewById(R.id.rvTodoItems)
        edtext = view.findViewById(R.id.edtext)
        btnAddTodo = view.findViewById(R.id.btnAddTodo)
        del = view.findViewById(R.id.del)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(requireContext())

        btnAddTodo.setOnClickListener {
            val todoTitle = edtext.text.toString()

            if (todoTitle.isNotEmpty()) {
                //saveTodos()
                homeViewModel.addTodo(todoTitle)
                edtext.text.clear()
            }
        }

        del.setOnClickListener {
            homeViewModel.deleteDoneTodos()
            //saveTodos()
        }

        //saveTodos()

        observeLiveData()

        return view
    }

    private fun observeLiveData() {
        myPreferences = MyPreferences(requireContext())

        homeViewModel.todos.observe(viewLifecycleOwner) { todos ->
            todoAdapter.todos = todos.toMutableList()
            todoAdapter.notifyDataSetChanged()
        }


        CoroutineScope(Dispatchers.Main).launch {
            myPreferences.getTodos().collect { it ->
                homeViewModel.todos.value = it
            }

        }


    }




    override fun onStop() {
        super.onStop()
        saveTodos()
        //val toast = Toast.makeText(context, "onStop Called", Toast.LENGTH_LONG).show()

    }

//    override fun onPause() {
//        super.onPause()
//        saveTodos()
//        val toast = Toast.makeText(context, "onPause Called", Toast.LENGTH_LONG).show()
//    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun saveTodos() {

        GlobalScope.launch {
            val list = mutableListOf<Todo>()
            homeViewModel.todos.value?.forEach {
                list.add(it)
            }
            myPreferences.updateTodos(list)
        }

    }


}