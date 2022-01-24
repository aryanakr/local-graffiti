package com.aryanakbarpour.localgraffiti.arts

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aryanakbarpour.localgraffiti.LocalGraffitiApp
import com.aryanakbarpour.localgraffiti.R
import com.aryanakbarpour.localgraffiti.data.Art
import com.aryanakbarpour.localgraffiti.data.toParcelizableArt
import com.aryanakbarpour.localgraffiti.databinding.FragmentArtsListBinding
import java.lang.reflect.Modifier
import kotlin.math.min


class ArtsListFragment : Fragment() {

    private val navigationArgs: ArtsListFragmentArgs by navArgs()

    private val viewModel by viewModels<ArtsViewModel> {
        ArtsViewModelFactory(
            (requireContext().applicationContext as LocalGraffitiApp).appContainer,
            navigationArgs.cityName
        )
    }

    private var _binding: FragmentArtsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentArtsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalFoundationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val screenWidth = resources.displayMetrics.widthPixels

        viewModel.status.observe(this.viewLifecycleOwner){ status ->
            when (status) {
                null -> {

                }
                APIServiceStatus.ERROR -> {
                    Toast.makeText(requireContext(), "There have been an error fetching data!", Toast.LENGTH_LONG)
                    val action = ArtsListFragmentDirections.actionArtsListFragmentToCitySearchFragment()
                    findNavController().navigate(action)
                }
                APIServiceStatus.DONE -> {
                    viewModel.localArts.observe(this.viewLifecycleOwner){ arts ->
                        binding.composeView.setContent {
                            val screenBasedCols = (screenWidth/450).toInt()
                            println("screen cols is $screenBasedCols")
                            LocalArtsGrid(arts, min(screenBasedCols, arts.size),{art: Art ->
                                navigateToArtDetail(art)
                            })
                        }
                    }
                }
                APIServiceStatus.LOADING -> {

                }
            }
        }
    }

    fun navigateToArtDetail(art: Art) {
        val action = ArtsListFragmentDirections.actionArtsListFragmentToArtDetailFragment(art.toParcelizableArt())
        findNavController().navigate(action)
    }


}



