package com.example.allegrointernmobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.allegrointernmobile.R
import com.example.allegrointernmobile.databinding.FragmentLanguagesInfoBinding
import com.example.allegrointernmobile.model.GithubApiLanguages
import com.example.allegrointernmobile.viewmodel.LanguagesInfoViewModel

class LanguagesInfoFragment : Fragment() {

    private var _binding: FragmentLanguagesInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LanguagesInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.repoName = arguments?.getString("repoName").toString()
            viewModel.userName = arguments?.getString("userName").toString()
        }
        viewModel.getLanguages()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguagesInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            languagesInfoFragment = this@LanguagesInfoFragment
        }
        binding.viewModel = viewModel
        binding.repoName.text = viewModel.repoName

        binding.progressBar.visibility = View.VISIBLE

        val nameObserver = Observer<String> { viewModel.status
            println(viewModel.status.value)
            if (viewModel.status.value == "SUCCESS"){
                binding.progressBar.visibility = View.GONE

                if (viewModel.listOfLanguages == ""){
                    binding.listOfLanguages.text = resources.getString(R.string.empty_response_message)
                }
                else {
                    binding.listOfLanguages.text = viewModel.listOfLanguages
                }
                binding.listOfLanguages.visibility = View.VISIBLE
            }
            else {
                binding.progressBar.visibility = View.GONE
                binding.listOfLanguages.text = resources.getString(R.string.error_message_languages)
                binding.listOfLanguages.visibility = View.VISIBLE
            }
        }

        viewModel.status.observe(this, nameObserver)


    }

}