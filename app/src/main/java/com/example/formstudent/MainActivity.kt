package com.example.formstudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.formstudent.databinding.ActivityMainBinding
import com.example.formstudent.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        binding.root.setOnClickListener {
            hideKeyboard()
            clearFocus()
        }

    }

    private fun clearFocus() {
        with(binding){
            etName.clearFocus()
            etLastName.clearFocus()
            etBornDate.clearFocus()
            etSchool.clearFocus()
            etObservations.clearFocus()
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}




/*private fun showMessage(msgResource: Int){
    Snackbar.make(binding.root,
        msgResource,
        Snackbar.LENGTH_SHORT)
        .show()
}*/