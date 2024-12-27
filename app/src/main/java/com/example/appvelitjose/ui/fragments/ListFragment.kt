package com.example.appvelitjose.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appvelitjose.adapters.PostAdapter
import com.example.appvelitjose.databinding.FragmentListBinding
import com.example.appvelitjose.viewmodels.PostViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var postViewModel: PostViewModel
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        adapter = PostAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        postViewModel.fetchPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}