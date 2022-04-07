package com.ubaya.ubayakost160419147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakost160419147.R
import com.ubaya.ubayakost160419147.util.loadImage
import com.ubaya.ubayakost160419147.viewmodel.DetailViewModel
import com.ubaya.ubayakost160419147.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_kost_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var nama = ""
        arguments?.let {
            nama = KostDetailFragmentArgs.fromBundle(requireArguments()).namaKost
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(nama)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.kostDetailLD.observe(viewLifecycleOwner){
            txtNamaDetail.setText(it.nama)
            txtGenderDetail.setText(it.gender)
            txtAlamatKos.setText(it.alamat)
            txtInfoKos.setText(it.informasi)
            imgKostDetail.loadImage(it.photoURL, progressLoadingDetail)
        }
    }
}