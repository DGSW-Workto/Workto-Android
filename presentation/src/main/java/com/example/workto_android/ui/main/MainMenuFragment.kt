package com.example.workto_android.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.workto_android.R
import com.example.workto_android.databinding.MainMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainMenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding: MainMenuBinding
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<MainMenuBinding>(inflater, R.layout.main_menu, container, false).apply {
            lifecycleOwner = this@MainMenuFragment.viewLifecycleOwner
            viewModel = this@MainMenuFragment.viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dialog?.setOnShowListener {
//            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }


    }

    fun check(str: String, d:Dialog?) {
        if (d == null) {
            Log.e(str,"null")
        } else {
            Log.e(str,"Not null")
        }
    }
}