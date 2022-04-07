package com.ubaya.ubayakost160419147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.ubayakost160419147.R
/**
 * A simple [Fragment] subclass.
 * Use the [SyaratDanKetentuanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SyaratDanKetentuanFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_syarat_dan_ketentuan, container, false)
    }
}