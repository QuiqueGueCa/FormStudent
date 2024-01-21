package com.example.formstudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.formstudent.databinding.ActivityMainBinding
import com.example.formstudent.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMainViewModel: MainViewModel
    private var mIsOverAge: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        binding.root.setOnClickListener {
            mMainViewModel.hideKeyBoard(this)
            clearFocus()
        }

        binding.etBornDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePickerDialog()
            }
        }


        binding.btnReset.setOnClickListener { resetAllFileds() }

        binding.btnAccept.setOnClickListener { saveStudent() }

    }

    private fun saveStudent() {

        if (!mIsOverAge) {
            with(binding) {
                if (etName.text!!.isEmpty() ||
                    etLastName.text!!.isEmpty() ||
                    etBornDate.text!!.isEmpty() ||
                    etSchool.text!!.isEmpty()
                ) {

                    Snackbar.make(this.root, "No se puede", Snackbar.LENGTH_SHORT).show()

                } else {

                    Snackbar.make(this.root, "Guardado!", Snackbar.LENGTH_SHORT).show()
                }
            }

        }else{
            with(binding) {
                if (etName.text!!.isEmpty() ||
                    etLastName.text!!.isEmpty() ||
                    etBornDate.text!!.isEmpty()
                ) {

                    Snackbar.make(this.root, "No se puede", Snackbar.LENGTH_SHORT).show()

                } else {

                    Snackbar.make(this.root, "Guardado!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun resetAllFileds() {
        with(binding) {
            etName.text?.clear()
            etLastName.text?.clear()
            etBornDate.text?.clear()
            etSchool.text?.clear()
            etObservations.text?.clear()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etBornDate.setText(getString(R.string.dateToShow, day.toString(), month.toString(), year.toString()))

        if (Calendar.getInstance().get(Calendar.YEAR) - year < 18) {
            binding.tilSchool.isVisible = true
            mIsOverAge = false

        } else {
            binding.tilSchool.visibility = android.view.View.INVISIBLE
            mIsOverAge = true
        }
    }

    private fun clearFocus() {
        with(binding) {
            etName.clearFocus()
            etLastName.clearFocus()
            etBornDate.clearFocus()
            etSchool.clearFocus()
            etObservations.clearFocus()
        }
    }
}