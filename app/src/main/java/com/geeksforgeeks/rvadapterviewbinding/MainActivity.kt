package com.geeksforgeeks.rvadapterviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeksforgeeks.rvadapterviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // view binding for the activity
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    // get reference to the adapter class
    private val rvAdapter by lazy { RvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // define layout manager for the Recycler view
        binding.rvList.layoutManager = LinearLayoutManager(this)
        // attach adapter to the recycler view
        binding.rvList.adapter = rvAdapter

        // create new objects
        val language1= Language(1,"Java" , "Exp : 3 years")
        val language2=Language(2,"Kotlin" , "Exp : 2 years")
        val language3=Language(3,"Python" , "Exp : 4 years")

        // call set data method of adapter class and the list data
        rvAdapter.setData(listOf(language1, language2, language3))
        binding.btnNewLanguage.setOnClickListener {
            // on click of button add one more item to the list
            val language4= Language(4,"CPP" , "Exp : 5 years")
            rvAdapter.setData(listOf(language1,language2,language3,language4))

        }
    }

    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}