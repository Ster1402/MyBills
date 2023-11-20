package com.sterdevs.mybills.ui.views.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.FragmentRegisterBinding
import com.sterdevs.mybills.ui.viewmodels.RegisterViewModel
import com.sterdevs.mybills.ui.views.activities.AuthActivity

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        // TODO: Use the ViewModel
    }


}