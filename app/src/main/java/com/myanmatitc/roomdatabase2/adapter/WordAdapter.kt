package com.myanmatitc.roomdatabase2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmatitc.roomdatabase2.R
import com.myanmatitc.roomdatabase2.entity.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter: RecyclerView.Adapter<WordAdapter.WordViewHolder>(){
    private var wordList: List<Word> = ArrayList()
    var clickListener: ClickListener? = null
    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        lateinit var word:Word
        fun bind(word: Word){
            itemView.txt_item.text = word.word
        }

        override fun onClick(view: View?) {
            clickListener?.onClick(word)
        }
    }

    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_word,parent,false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int =wordList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun setWord(wordList: List<Word>) {
        this.wordList = wordList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(word: Word)
    }

}