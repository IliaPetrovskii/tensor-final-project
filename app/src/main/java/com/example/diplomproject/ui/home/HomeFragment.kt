package com.example.diplomproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomproject.KpApplication
import com.example.diplomproject.R
import com.example.diplomproject.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = view.findViewById<RecyclerView>(R.id.recycle_view)

        recycleView.layoutManager = LinearLayoutManager(this.context)
        movieAdapter = MovieAdapter(viewModel::deleteMovie)
        recycleView.adapter = movieAdapter

        MutableLiveData(KpApplication.database.movieDao().getAlphabetizedMovieList()).observe(this.viewLifecycleOwner){
            movieAdapter.reload(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}