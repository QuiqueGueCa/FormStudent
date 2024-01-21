package com.example.formstudent.viewModel

import android.content.Context
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.formstudent.MainActivity

class MainViewModel : ViewModel() {


    fun hideKeyBoard(mainActivity: MainActivity){

        val imm: InputMethodManager =
            mainActivity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(mainActivity.currentFocus?.windowToken, 0)
        }
    }


}