package com.example.workto_android.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.result.EventObserver
import com.example.model.PostData

import com.example.workto_android.R
import com.example.workto_android.databinding.FragmentPostListBinding
import com.example.workto_android.ui.BaseFragment
import com.example.workto_android.ui.post.PostDetailActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostListFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentPostListBinding

    override val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPostListBinding>(
            inflater,
            R.layout.fragment_post_list,
            container,
            false
        ).apply {
            viewModel = this@PostListFragment.viewModel
            lifecycleOwner = this@PostListFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewPost.apply {
            setHasFixedSize(true)
            adapter = PostAdapter(viewModel)
        }

        initObserver()
    }

    private fun initObserver() {

        viewModel.navigateToPostDetail.observe(viewLifecycleOwner, EventObserver {
            activity?.startActivity(Intent(activity, PostDetailActivity::class.java).putExtra("id", it))
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                "network" -> Snackbar.make(
                    binding.holderLayout,
                    "네트워크 연결을 확인해주세요",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

}

@BindingAdapter("post_item")
fun setPostItem(recyclerView: RecyclerView, postData: PostData?) {
    val postAdapter: PostAdapter

    if (recyclerView.adapter == null)
        return
    else
        postAdapter = recyclerView.adapter as PostAdapter

    postData?.let {
        postAdapter.canLoadMore = postData.next_page != 0
        postAdapter.postList = postData.posts
        postAdapter.nextPage = postData.next_page
        postAdapter.notifyDataSetChanged()
    }
}
