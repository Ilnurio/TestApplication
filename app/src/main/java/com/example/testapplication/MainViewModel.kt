package com.example.testapplication

import androidx.lifecycle.*

class MainViewModel(lifecycle: Lifecycle) {

    private val _liveData = MutableLiveData<ApiResult>()
    val liveData: LiveData<ApiResult>
        get() = _liveData

    init {
        lifecycle.coroutineScope.launchWhenCreated {
            _liveData.value = ApiResult.Progress
            try {
                val pizzaServerResponse = NetworkTool.menuService.getPizzas()
                val menuItems = mapServerResponseToMenuItem(pizzaServerResponse)
                _liveData.value = ApiResult.Success(menuItems)
            } catch (e: Exception) {
                _liveData.value = ApiResult.Error(e)
            }
        }
    }

    private fun mapServerResponseToMenuItem(serverResponse: List<PizzaServerItem>): List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        serverResponse.forEach {
            val menuItem = MenuItem(
                name = it.name ?: "",
                description = it.description ?: "",
                price = it.price ?: 0,
                imageUrl =  it.img ?: ""
            )
            menuItems.add(menuItem)
        }
        return menuItems
    }

}

sealed class ApiResult {
    object Progress: ApiResult()
    class Success(val data: List<MenuItem>): ApiResult()
    class Error(val e: Exception): ApiResult()
}