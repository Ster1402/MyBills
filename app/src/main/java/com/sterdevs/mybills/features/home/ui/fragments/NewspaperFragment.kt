package com.sterdevs.mybills.features.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sterdevs.mybills.R

class NewspaperFragment : Fragment() {
    companion object {
        fun newInstance(): NewspaperFragment {
            return NewspaperFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newspaper, container, false)
    }
}