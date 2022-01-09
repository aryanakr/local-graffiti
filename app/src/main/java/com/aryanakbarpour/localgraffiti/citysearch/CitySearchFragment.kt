package com.aryanakbarpour.localgraffiti.citysearch

import android.annotation.SuppressLint
import android.content.ContentProviderClient
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.aryanakbarpour.localgraffiti.R
import com.aryanakbarpour.localgraffiti.databinding.FragmentCitySearchBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.util.*
import java.util.jar.Manifest


class CitySearchFragment : Fragment() {

    private val PERMISSION_ID = 84

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    private var _binding: FragmentCitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

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

        binding.coverImage.load("https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixlib=rb-1.2.1&w=1000&q=8")

        binding.getLocationButton.setOnClickListener {
            getLastLocation()
        }
    }

    private fun navigateToLocalArtsList(cityName: String){
        val action = CitySearchFragmentDirections.actionCitySearchFragmentToArtsListFragment(cityName)
        this.findNavController().navigate(action)
    }

    // Get current city methods

    private fun getLastLocation() {
        if (checkPermission()){
            if(isLocationEnabled()){
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    var location = task.result
                    if(location != null) {
                        binding.cityEditText.setText(getCityName(location.latitude, location.longitude))
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enable your location service!", Toast.LENGTH_SHORT).show()
            }
        }else {
            requestPermission()
        }
    }

    private fun getCityName(lat: Double, lon: Double) : String {
        val addresses = Geocoder(requireContext(), Locale.getDefault()).getFromLocation(lat, lon, 10)
        if (!addresses.isNullOrEmpty()){
            for (adr in addresses) {
                if (!adr.locality.isNullOrEmpty()){
                    return adr.locality
                }
            }
        }
        return ""
    }

    private fun checkPermission():Boolean{
        if (
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean {
        var locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}