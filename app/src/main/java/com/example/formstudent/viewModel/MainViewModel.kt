package com.example.formstudent.viewModel

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formstudent.MainActivity
import java.util.Calendar

class MainViewModel : ViewModel() {

    private val mIsOverAge = MutableLiveData<Boolean>()


    fun hideKeyBoard(mainActivity: MainActivity){

        val imm: InputMethodManager =
            mainActivity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(mainActivity.currentFocus?.windowToken, 0)
        }
    }

    fun getIsOverAge(): LiveData<Boolean>{return mIsOverAge}

    fun setIsOverAge(year: Int){
        mIsOverAge.value = Calendar.getInstance().get(Calendar.YEAR) - year >= 18
    }

}