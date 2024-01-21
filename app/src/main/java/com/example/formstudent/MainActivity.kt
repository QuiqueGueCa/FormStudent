package com.example.formstudent

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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
            hideKeyBoard()
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
        with(binding) {
            if (etName.text!!.trim().isEmpty() ||
                etLastName.text!!.trim().isEmpty() ||
                etBornDate.text!!.trim().isEmpty()
            ) {
                Snackbar.make(this.root, R.string.mustToCompleteFields, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (!mMainViewModel.getIsOverAge().value!! && etSchool.text!!.trim().isEmpty()) {
                Snackbar.make(this.root, R.string.mustToCompleteFields, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                if (mMainViewModel.saveUser(
                        etName.text.toString(),
                        etLastName.text.toString(),
                        etBornDate.text.toString(),
                        etSchool.text.toString(),
                        etObservations.text.toString()
                    )
                ) {
                    Snackbar.make(this.root, R.string.userSaved, Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(this.root, R.string.registerFailed, Snackbar.LENGTH_SHORT).show()
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

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etBornDate.setText(
            getString(
                R.string.dateToShow,
                day.toString(),
                month.toString(),
                year.toString()
            )
        )
        mMainViewModel.setIsOverAge(year)
        if (mMainViewModel.getIsOverAge().value == true) {
            binding.tilSchool.visibility = View.INVISIBLE
        } else {
            binding.tilSchool.visibility = View.VISIBLE
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

    private fun hideKeyBoard() {
        val imm: InputMethodManager =
            this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}