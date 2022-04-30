package com.example.worddictionary.activities.words

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase

class WordDict : Fragment() {

    private lateinit var viewModel: WordDictViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

       viewModel = ViewModelProvider(this).get(WordDictViewModel::class.java)
        val layout = inflater.inflate(R.layout.fragment_word_dict, container, false)

        setHasOptionsMenu(true)
        return layout
}




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //when (item.itemId) {
            //R.id.show_active_menu //show active
            //R.id.show_inactive_menu // show inactive
            //R.id.add_word_menu -> //navigate
            //else ->
        //}
        //)
        return true
    }

}