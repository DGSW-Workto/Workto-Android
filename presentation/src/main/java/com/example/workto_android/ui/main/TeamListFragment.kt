package com.example.workto_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.workto_android.R
import com.example.workto_android.databinding.FragmentTeamListBinding
import com.example.workto_android.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TeamListFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentTeamListBinding

    override val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentTeamListBinding>(
            inflater,
            R.layout.fragment_team_list,
            container,
            false
        ).apply {
            viewModel = this@TeamListFragment.viewModel
            lifecycleOwner = this@TeamListFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {}


}
