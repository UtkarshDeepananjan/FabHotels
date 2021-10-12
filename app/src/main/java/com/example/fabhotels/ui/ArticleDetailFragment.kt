package com.example.fabhotels.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fabhotels.databinding.FragmentArticleDetailBinding
import com.example.fabhotels.utils.Utils


class ArticleDetailFragment : Fragment() {

    private lateinit var viewModel: NewsDashboardViewModel
    private lateinit var binding: FragmentArticleDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)
            .get(NewsDashboardViewModel::class.java)
        populateData()
        binding.backButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
        return binding.root
    }

    private fun populateData() {
        viewModel.selectedItem.observe(viewLifecycleOwner, {
            binding.sourceName.text = it.source.name
            binding.lastUpdated.text = Utils.getTime(it.publishedAt)
            binding.title.text = it.title
            binding.fullArticle.text = Html.fromHtml(it.content, Html.FROM_HTML_MODE_COMPACT)
            context?.let { it1 ->
                Glide.with(it1)
                    .load(it.urlToImage)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imageView)
            }

        })
    }

}