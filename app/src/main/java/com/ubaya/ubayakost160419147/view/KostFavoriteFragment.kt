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
import kotlinx.android.synthetic.main.fragment_kost_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostFavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostFavoriteFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val kostListAdapter  = KostListFavSayaAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recViewFav.layoutManager = LinearLayoutManager(context)
        recViewFav.adapter = kostListAdapter

        observeViewModel()

        refreshLayoutFav.setOnRefreshListener {
            recViewFav.visibility = View.GONE
            txtErrorFav.visibility = View.GONE
            progressLoadFav.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFav.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.kostLD.observe(viewLifecycleOwner){
            var arr: ArrayList<Kost> = arrayListOf()
            arr.add(it[0])
            kostListAdapter.updateKostListSaya(arr)
        }

        viewModel.kostLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorFav.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLD.observe(viewLifecycleOwner){
            if (it){
                recViewFav.visibility = View.GONE
                progressLoadFav.visibility = View.VISIBLE
            } else {
                recViewFav.visibility = View.VISIBLE
                progressLoadFav.visibility = View.GONE
            }
        }
    }
}