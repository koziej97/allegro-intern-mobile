package com.example.allegrointernmobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.allegrointernmobile.databinding.FragmentChooseNameBinding
import com.example.allegrointernmobile.viewmodel.SharedViewModel

class ChooseNameFragment : Fragment() {

    private var _binding: FragmentChooseNameBinding? = null
    private val binding get() = _binding!!
    private val mSharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            chooseNameFragment = this@ChooseNameFragment
            sharedViewModel = mSharedViewModel
        }

        binding.button.setOnClickListener {
            mSharedViewModel.username = binding.editText.text.toString()
            mSharedViewModel.getRepositories()

            findNavController().navigate(ChooseNameFragmentDirections.actionChooseNameFragmentToRepositoriesListFragment())
        }
    }
}