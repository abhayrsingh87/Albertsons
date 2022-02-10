package com.abhay.acronym.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.abhay.acronym.viewmodel.AcronymViewModel
import com.abhay.acronym.R
import com.abhay.acronym.databinding.FragmentAcronymBinding

class AcronymFragment : Fragment() {

    private val acronymViewModel: AcronymViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentAcronymBinding>(
            inflater,
            R.layout.fragment_acronym,
            container,
            false
        ).apply {
            viewModel = acronymViewModel
            acronymViewModel.error.observe(viewLifecycleOwner, {
                //TODO: show more meaningful dialog
                AlertDialog.Builder(requireContext()).setMessage(getString(R.string.server_error))
                    .show()
            })

        }.root
    }
}