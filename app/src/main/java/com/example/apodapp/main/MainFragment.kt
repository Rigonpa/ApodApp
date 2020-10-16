package com.example.apodapp.main

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apodapp.R
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.detail.DetailActivity
import com.example.apodapp.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), ItemListInteractorListener {

    private val mViewModel: MainViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    companion object {
        fun getNewInstance () = MainFragment()
    }

    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var items = ArrayList<String>()
//        items.add("Title 1")
//        items.add("Title 2")
//        items.add("Title 3")
//        items.add("Title 4")
//        items.add("Title 5")
//        items.add("Title 6")

        mViewModel.getLocalApods().observe(viewLifecycleOwner, Observer { items ->
            mainAdapter = MainAdapter(requireContext(), items, this)
            recyclerView.adapter = mainAdapter
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(false)

    }


    override fun itemClicked(apodResponse: ApodResponse) {
        activity?.let {
            Intent(it, DetailActivity::class.java).apply {

                arguments = Bundle().apply {
                    this.putSerializable("LOCAL_APOD", apodResponse)
                }

                arguments?.let { bro ->
                    putExtras(bro)
                }
                putExtra("ORIGIN_TAG", "local_apod")
                startActivity(this)
            }
        }
    }
}