package com.ubaya.ubayakost160419147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakost160419147.R
import com.ubaya.ubayakost160419147.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val kostListAdapter  = KostListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = kostListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.kostLD.observe(viewLifecycleOwner){
            kostListAdapter.updateKostList(it)
        }

        viewModel.kostLoadErrorLD.observe(viewLifecycleOwner){
            txtError.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLD.observe(viewLifecycleOwner){
            if (it){
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}