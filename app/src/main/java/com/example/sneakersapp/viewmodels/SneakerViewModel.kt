package com.example.sneakersapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.ApiResponse
import com.example.sneakersapp.model.Result
import com.example.sneakersapp.model.SneakerResponse
import com.example.sneakersapp.repository.SneakerRepo
import com.example.sneakersapp.repository.SneakersCartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerViewModel @Inject constructor(private val sneakerRepo: SneakerRepo,private val cartRepo: SneakersCartRepo) : ViewModel() {
    private val sneakers:MutableLiveData<List<Result>> = MutableLiveData()
    val _sneakers = sneakers as LiveData<List<Result>>
    private val subTotal:MutableLiveData<Int> = MutableLiveData()
    val _subTotal = subTotal as LiveData<Int>
    private val itemsInCart:MutableLiveData<List<Result>> = MutableLiveData()
    val _itemsInCart = itemsInCart as LiveData<List<Result>>
    var selectedSneaker : Result? = null
    private val isItemRemoved:MutableLiveData<Boolean> = MutableLiveData()
    val _isItemRemoved = isItemRemoved as LiveData<Boolean>
    init {
        viewModelScope.launch {
            getSneakers()
//            getSneakersFromCart()
        }
    }
    private suspend fun getSneakers() {
        val data = sneakerRepo.getAllSneakers()
        sneakers.value = data.data?.results
        Log.e("Sneakers","${data.data}")
    }

    fun selectSneaker(result: Result){
        selectedSneaker = result
    }

    suspend fun addToCart(result: Result){
        isItemRemoved.value = true
        cartRepo.addToCart(result)
    }

    suspend fun getSneakersFromCart(){
       itemsInCart.value = cartRepo.getSneakersFromCart()
        Log.e("Data from Cart","${itemsInCart.value}")
        subTotal.value = itemsInCart.value?.filter { it.retailPrice > 0 }?.sumOf { it.retailPrice }
        Log.e("SubTotal","${subTotal.value}")
    }

    suspend fun removeItemFromCart(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            isItemRemoved.postValue(false)
            cartRepo.removeFromCart(id)
        }
    }

    private suspend fun getSneakerById(id: String): Result? {
        return cartRepo.getSneakerById(id)
    }

    suspend fun isSneakerStoredInDb(){
        val sneaker = selectedSneaker?.id?.let { getSneakerById(it) }
        isItemRemoved.value = sneaker!=null
    }
}