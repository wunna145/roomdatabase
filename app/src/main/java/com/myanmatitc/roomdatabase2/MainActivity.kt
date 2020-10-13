package com.myanmatitc.roomdatabase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmatitc.roomdatabase2.adapter.WordAdapter
import com.myanmatitc.roomdatabase2.entity.Word
import com.myanmatitc.roomdatabase2.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , WordAdapter.ClickListener {
    private lateinit var wordViewModel: WordViewModel
    private lateinit var wordAdapter: WordAdapter
    private lateinit var word_one: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordAdapter = WordAdapter()
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer {
            wordAdapter.setWord(it)
        })
        recyclerWord.layoutManager = LinearLayoutManager(this)
        recyclerWord.adapter = wordAdapter
        recyclerWord.visibility = View.VISIBLE
        btn_save.setOnClickListener{
            val word= Word(edt_word.text.toString())
            wordViewModel.insert(word)
        }
        wordViewModel.allWords.observe(this,Observer{
            txt_result.text = it.size.toString()
        })
        btn_delete.setOnClickListener{
            var delete_word = edt_word.text.toString()
            wordViewModel.deleteWord(delete_word)
        }
        btn_update.setOnClickListener{
            var update_word =edt_word.text.toString()
            wordViewModel.updateWord(word_one,update_word)
        }
        wordAdapter.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search Any"
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var searchQuery ="%newText%"
                wordViewModel.getSearchWord(searchQuery).observe(this@MainActivity,Observer{wordAdapter.setWord(it as List<Word>)})
                recyclerWord.layoutManager= LinearLayoutManager(applicationContext)
                recyclerWord.adapter = wordAdapter
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(word: Word) {
        edt_word.setText(word.word.toString())
        word_one = word.word.toString()
    }
}