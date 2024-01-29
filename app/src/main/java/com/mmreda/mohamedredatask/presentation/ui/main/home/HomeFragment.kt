package com.mmreda.mohamedredatask.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmreda.mohamedredatask.base.BaseFragment
import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import com.mmreda.mohamedredatask.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator

@AndroidEntryPoint
class HomeFragment : BaseFragment(), OnItemPostClicked {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val postsAdapter by lazy { PostsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        handleError(viewModel)
        handleProgress(viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPostsAdapter()
        getAllPosts()
    }

    private fun initPostsAdapter() {
        binding.recyclerPosts.adapter = postsAdapter
        binding.recyclerPosts.itemAnimator = SlideInRightAnimator().apply {
            addDuration = 500
        }
    }

    private fun getAllPosts() {
        viewModel.getAllPostsUseCase().observe(viewLifecycleOwner) { posList ->
            postsAdapter.saveData(posList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(postDto: PostDto) {
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToPostDetailsFragment(postDto)
        )
    }
}