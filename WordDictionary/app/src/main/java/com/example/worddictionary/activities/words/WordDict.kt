package com.example.worddictionary.activities.words

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.worddictionary.R

class WordDict : Fragment() {

    companion object {
        fun newInstance() = WordDict()
    }

    private lateinit var viewModel: WordDictViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.word_dict_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WordDictViewModel::class.java)
        // TODO: Use the ViewModel
    }

}