package com.example.allegrointernmobile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.allegrointernmobile.R
import com.example.allegrointernmobile.adapter.ReposItemClickListener
import com.example.allegrointernmobile.adapter.RepositoryListAdapter
import com.example.allegrointernmobile.databinding.FragmentRepositoriesListBinding
import com.example.allegrointernmobile.model.RepositoryInfo
import com.example.allegrointernmobile.viewmodel.RepositoriesListViewModel


class RepositoriesListFragment : Fragment(), ReposItemClickListener {

    private var _binding: FragmentRepositoriesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RepositoriesListViewModel by viewModels()
    lateinit var mAdapter : RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.userName = arguments?.getString("userName").toString()
        }
        viewModel.getRepositories()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesListBinding.inflate(inflater, container, false)

        mAdapter = RepositoryListAdapter(this)

        binding.lifecycleOwner = this
        binding.repositoriesListViewModel = viewModel
        binding.repositoriesRecyclerview.adapter = mAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            repositoriesListFragment = this@RepositoriesListFragment
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.errorMessage.visibility = View.GONE

        val nameObserver = Observer<String> { viewModel.status
            if (viewModel.status.value == "SUCCESS"){
                binding.progressBar.visibility = View.GONE
            }
            else {
                setErrorMessage(viewModel)
                binding.progressBar.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
            }
        }

        viewModel.status.observe(viewLifecycleOwner, nameObserver)

    }

    override fun chooseRepo(repo: RepositoryInfo) {
        val bundle = Bundle()
        bundle.putString("repoName", repo.name)
        bundle.putString("userName", viewModel.userName)
        findNavController().navigate(R.id.action_repositoriesListFragment_to_languagesInfoFragment, bundle)
    }

    private fun setErrorMessage(viewModel: RepositoriesListViewModel) {
        if (viewModel.errorCode.value == 404){
            binding.errorMessage.text = getString(R.string.error404, viewModel.userName)
        }
        else {
            binding.errorMessage.text = getString(R.string.error_message)
        }
    }
}