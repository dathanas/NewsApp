package com.dathanasleri.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dathanasleri.newsapp.adapter.ArticlesRecyclerViewAdapter
import com.dathanasleri.newsapp.viewmodel.ArticleActivityViewModel

class ArticlesListFragment : Fragment() {

    private lateinit var articlesRecyclerAdapter: ArticlesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_articles_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val articlesRecyclerView = view.findViewById<RecyclerView>(R.id.articlesRecyclerView)
        articlesRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        articlesRecyclerView.addItemDecoration(decoration)

        articlesRecyclerAdapter = ArticlesRecyclerViewAdapter()
        articlesRecyclerView.adapter = articlesRecyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[ArticleActivityViewModel::class.java]
        viewModel.getArticleListObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                articlesRecyclerAdapter.setUpdatedData(it.articles)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ArticlesListFragment()
    }
}