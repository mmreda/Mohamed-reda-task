package com.mmreda.mohamedredatask.presentation.ui.main.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mmreda.mohamedredatask.base.BaseFragment
import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import com.mmreda.mohamedredatask.databinding.FragmentPostDetailsBinding

class PostDetailsFragment : BaseFragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!

    private var post = PostDto()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)

        post = PostDetailsFragmentArgs.fromBundle(requireArguments()).post

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.post = post

        setListeners()
    }

    private fun setListeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}