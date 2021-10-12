package com.example.fabhotels.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.fabhotels.R
import com.example.fabhotels.data.model.Article
import com.example.fabhotels.databinding.NewsDashboardFragmentBinding
import com.example.fabhotels.utils.ArticleItemClickListener


class NewsDashboardFragment : Fragment(), ArticleItemClickListener {

    private lateinit var viewModel: NewsDashboardViewModel
    private lateinit var binding: NewsDashboardFragmentBinding
    private lateinit var adapter: NewsItemRecyclerViewAdapter
    private lateinit var itemClickListener: ArticleItemClickListener
    private lateinit var sharedPreferences: SharedPreferences
    private var list: MutableList<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsDashboardFragmentBinding.inflate(inflater, container, false)
        itemClickListener = this
        adapter =
            activity?.let {
                NewsItemRecyclerViewAdapter(
                    it.baseContext,
                    mutableListOf(),
                    itemClickListener
                )
            }!!
        viewModel = ViewModelProvider(this)
            .get(NewsDashboardViewModel::class.java)
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.list.adapter = adapter
        viewModel.mArticles?.observe(viewLifecycleOwner, {
            if (it?.status.equals("ok", ignoreCase = false)) {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
                binding.shimmer.stopShimmer()
                list = it?.articles
                adapter.setArticlesList(list)
            }
        })
        binding.logout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(getString(R.string.preference_file_key), false)
            editor.apply()
            findNavController(this).navigate(R.id.loginFragment)
        }

        return binding.root
    }

    override fun onClick(position: Int) {
        list?.get(position)?.let { viewModel.setSelectArticle(it) }
        findNavController(this).navigate(R.id.articleDetailFragment)
    }


}