package com.example.formstudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.formstudent.databinding.ActivityMainBinding
import com.example.formstudent.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

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

        binding.etBornDate.setOnFocusChangeListener { _, hasFocus -> if(hasFocus){showDatePickerDialog()} }

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int){
        binding.etBornDate.setText("$day/$month/$year")


        if(Calendar.getInstance().get(Calendar.YEAR) - year < 18){
            binding.tilSchool.isVisible = true

        }else {binding.tilSchool.visibility = android.view.View.INVISIBLE}
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