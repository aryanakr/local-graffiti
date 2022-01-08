package com.aryanakbarpour.localgraffiti.citysearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aryanakbarpour.localgraffiti.R
import com.aryanakbarpour.localgraffiti.databinding.FragmentCitySearchBinding


class CitySearchFragment : Fragment() {

    private var _binding: FragmentCitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentCitySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            val cityNameInput = binding.cityEditText.text.toString().trim()
            if (cityNameInput.isNotEmpty()){
                navigateToLocalArtsList(cityNameInput)
            }
        }
    }

    private fun navigateToLocalArtsList(cityName: String){
        val action = CitySearchFragmentDirections.actionCitySearchFragmentToArtsListFragment(cityName)
        this.findNavController().navigate(action)
    }


}