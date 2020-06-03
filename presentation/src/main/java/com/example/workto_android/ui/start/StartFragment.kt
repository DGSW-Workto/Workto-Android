package com.example.workto_android.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.workto_android.R
import com.example.workto_android.databinding.FragmentStartBinding
import com.example.workto_android.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StartFragment : BaseFragment<StartViewModel>() {
    override val viewModel: StartViewModel by sharedViewModel()

    private lateinit var binding : FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        return binding.root
    }


}
