package com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.R
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.model.MoviesPogoResult
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.model.Result
import com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.presentation.viewModel.MoviesView
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), CellClickListener {
    private lateinit var result: List<Result>
    lateinit var moviesView: MoviesView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesView = ViewModelProvider(this).get(MoviesView::class.java)
    }

    override fun onStart() {
        super.onStart()
        fetchMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

     fun fetchMovies() {
        moviesView.fetchMovies().observe(this,
            Observer<MoviesPogoResult> {
                if (it.results != null) {
                    result = it.results
                    Log.i("TAG", "fetchMovies: $result")
                    rcvMain.apply {
                        this.layoutManager = LinearLayoutManager(activity)
                        this.adapter = ItemAdapter(context, result, this@MainFragment)
                    }
                }
            })
    }

    override fun onCellClickListener(data: Result) {
        Toast.makeText(context, "cell called ${data.original_title}", Toast.LENGTH_SHORT).show()
        arguments = Bundle().apply {
            putParcelable("films", data)
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_detailsFragment, arguments)
        }
    }
}

interface CellClickListener {
    fun onCellClickListener(data: Result)
}

