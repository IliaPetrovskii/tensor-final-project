package com.example.diplomproject.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomproject.R
import com.example.diplomproject.data.database.Movie
import com.example.diplomproject.data.network.ApiResult
import com.example.diplomproject.data.network.KPApi
import com.example.diplomproject.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var kpApi: KPApi

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private var movies: List<Movie>? = null

    @Inject
    lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = view.findViewById<SearchView>(R.id.simpleSearchView)
        val recycleView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recycleView.layoutManager = LinearLayoutManager(this.context)
        searchMovieAdapter = SearchMovieAdapter()
        recycleView.adapter = searchMovieAdapter
        recycleView.addItemDecoration(DividerItemDecoration(recycleView.context, LinearLayout.HORIZONTAL))

        viewModel.movies.observe(viewLifecycleOwner) {
            searchMovieAdapter.reload(it)
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("Movie", query!!)
                kpApi = KPApi.create()
                kpApi.getMoviesByName2(query).enqueue(
                    object : Callback<ApiResult> {
                        override fun onResponse(
                            call: Call<ApiResult>,
                            response: Response<ApiResult>
                        ) {
                            movies = response.body()?.items
                            if((movies?.size ?: 0) > 5 ){
                                movies = movies?.take(5)
                                }
                            movies?.let { searchMovieAdapter.reload(it) }
                        }

                        override fun onFailure(call: Call<ApiResult>, t: Throwable) {
                            t.message
                        }
                    }
                )
                Toast.makeText(this@SearchFragment.context, query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
