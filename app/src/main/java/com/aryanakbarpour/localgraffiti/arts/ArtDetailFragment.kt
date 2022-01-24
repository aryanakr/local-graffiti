package com.aryanakbarpour.localgraffiti.arts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.aryanakbarpour.localgraffiti.R
import com.aryanakbarpour.localgraffiti.databinding.FragmentArtDetailBinding
import com.aryanakbarpour.localgraffiti.databinding.FragmentArtsListBinding


class ArtDetailFragment : Fragment() {

    private val navigationArgs: ArtDetailFragmentArgs by navArgs()

    private var _binding: FragmentArtDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentArtDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artDetail = navigationArgs.artDetail
        binding.artImage.load(artDetail.pictLink)
        binding.artistTxt.text = artDetail.writersNames
    }

}