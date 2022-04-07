package com.ubaya.ubayakost160419147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakost160419147.R
import com.ubaya.ubayakost160419147.model.Kost
import com.ubaya.ubayakost160419147.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_favorite.*
import kotlinx.android.synthetic.main.fragment_kost_saya.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostSayaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostSayaFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val kostListAdapter  = KostListFavSayaAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_saya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recViewSaya.layoutManager = LinearLayoutManager(context)
        recViewSaya.adapter = kostListAdapter

        observeViewModel()

        refreshLayoutSaya.setOnRefreshListener {
            recViewSaya.visibility = View.GONE
            txtErrorSaya.visibility = View.GONE
            progressLoadSaya.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutSaya.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.kostLD.observe(viewLifecycleOwner){
            var arr: ArrayList<Kost> = arrayListOf()
            arr.add(it[1])
            kostListAdapter.updateKostListSaya(arr)
        }

        viewModel.kostLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorSaya.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLD.observe(viewLifecycleOwner){
            if (it){
                recViewSaya.visibility = View.GONE
                progressLoadSaya.visibility = View.VISIBLE
            } else {
                recViewSaya.visibility = View.VISIBLE
                progressLoadSaya.visibility = View.GONE
            }
        }
    }
}