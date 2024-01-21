package com.example.formstudent.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formstudent.model.MainInteractor
import java.util.Calendar

class MainViewModel : ViewModel() {

    private val mIsOverAge = MutableLiveData<Boolean>()
    private val interactor: MainInteractor = MainInteractor()

    fun getIsOverAge(): LiveData<Boolean> {
        return mIsOverAge
    }

    fun setIsOverAge(year: Int) {
        mIsOverAge.value = Calendar.getInstance().get(Calendar.YEAR) - year >= 18
    }

    fun saveUser(
        name: String,
        lastName: String,
        bornDate: String,
        school: String,
        observations: String
    ): Boolean {

        return interactor.addUser(name, lastName, bornDate, school, observations)

    }

}