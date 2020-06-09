package com.example.workto_android.ui.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.workto_android.R
import com.example.workto_android.databinding.FragmentMainMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainMenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(
            inflater,
            R.layout.fragment_main_menu,
            container,
            false
        ).apply {
            lifecycleOwner = this@MainMenuFragment.viewLifecycleOwner
            viewModel = this@MainMenuFragment.viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewMenu.apply {
            setHasFixedSize(true)
            adapter = MainMenuAdapter(viewModel)
        }
    }

}