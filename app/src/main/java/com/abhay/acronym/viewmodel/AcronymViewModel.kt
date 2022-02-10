package com.abhay.acronym.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhay.acronym.model.Meanings
import com.abhay.acronym.adapter.AcronymsListAdapter
import com.abhay.acronym.repository.AcronymRepository
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class AcronymViewModel : ViewModel(), KoinComponent {

    private val acronymRepositoryImplementation: AcronymRepository by inject()
    val showNoResultFound = ObservableBoolean()
    val adapter = AcronymsListAdapter(arrayListOf())
    val error = MutableLiveData<Boolean>()

    fun onQueryTextSubmit(query: String?): Boolean {
        return if (query.isNullOrBlank()) {
            false
        } else {
            getAcronyms(query)
            true
        }
    }

    fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) clearList()
        return true
    }

    private fun clearList() {
        showNoResultFound.set(false)
        adapter.clearList()
    }

    private fun getAcronyms(shortForm: String) {
        viewModelScope.launch {
            var result: Response<List<Meanings>>
            withContext(Dispatchers.IO) {
                result = acronymRepositoryImplementation.getAcronyms(shortForm)
            }
            if (result.isSuccessful) {
                //Handle success response
                val meaning = result.body()
                if (meaning != null && meaning.isNotEmpty()) {
                    showNoResultFound.set(false)
                    adapter.updateList(meaning[0].lfs)
                    adapter.notifyDataSetChanged()
                } else {
                    //Handle error response
                    showNoResultFound.set(true)
                }
            } else {
                error.value = true
            }
        }
    }
}