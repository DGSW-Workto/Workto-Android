package com.example.workto_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.result.EventObserver
import com.example.model.TeamData

import com.example.workto_android.R
import com.example.workto_android.databinding.FragmentTeamListBinding
import com.example.workto_android.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TeamListFragment : BaseFragment<MainViewModel>() {

    override val viewModel: MainViewModel by sharedViewModel()
    lateinit var binding: FragmentTeamListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTeamListBinding>(inflater, R.layout.fragment_team_list, container,false).apply {
            viewModel = this@TeamListFragment.viewModel
            lifecycleOwner = this@TeamListFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewTeam.apply {
            setHasFixedSize(true)
            adapter = TeamAdapter(viewModel)
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.error.observe(viewLifecycleOwner, EventObserver{
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

@BindingAdapter("team_item")
fun setTeamItem(recyclerView: RecyclerView, postData: TeamData?) {
    val teamAdapter: TeamAdapter

    if (recyclerView.adapter == null)
        return
    else
        teamAdapter = recyclerView.adapter as TeamAdapter

    postData?.let {
        teamAdapter.canLoadMore = postData.next_page != 0
        teamAdapter.postList = postData.rows
        teamAdapter.nextPage = postData.next_page
        teamAdapter.notifyDataSetChanged()
    }
}
