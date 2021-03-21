package com.geeksforgeeks.rvadapterviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeksforgeeks.rvadapterviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // get reference to the adapter class
    private var languageList = ArrayList<Language>()
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // define layout manager for the Recycler view
        binding.rvList.layoutManager = LinearLayoutManager(this)
        // attach adapter to the recycler view
        rvAdapter = RvAdapter(languageList , object : RvAdapter.OptionsMenuClickListener{
            override fun onOptionsMenuClicked(position: Int) {
                performOptionsMenuClick(position)
            }
        })
        binding.rvList.adapter = rvAdapter

        val language1 = Language("Java" , "1" )
        val language2 = Language("Java" , "1" )
        val language3 = Language("Java" , "1" )
        val language4 = Language("Java" , "1" )

        languageList.add(language1)
        languageList.add(language2)
        languageList.add(language3)
        languageList.add(language4)

        rvAdapter.notifyDataSetChanged()

    }

    private fun performOptionsMenuClick(position: Int) {
        val popupMenu = PopupMenu(this , binding.rvList[position].findViewById(R.id.textViewOptions))
        popupMenu.inflate(R.menu.options_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.delete -> {
                        val tempLang = languageList[position]
                        languageList.remove(tempLang)
                        rvAdapter.notifyDataSetChanged()
                        return true
                    }
                }
                return false
            }

        })
        popupMenu.show()
    }

    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}