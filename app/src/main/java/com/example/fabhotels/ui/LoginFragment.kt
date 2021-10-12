package com.example.fabhotels.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fabhotels.R
import com.example.fabhotels.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    private val emailLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private lateinit var viewModel: LoginViewModel
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false
        addSource(emailLiveData)
        { email ->
            val password = passwordLiveData.value
            this.value = validateForm(email, password)
        }
        addSource(passwordLiveData)
        { password ->
            val email = emailLiveData.value
            this.value = validateForm(email, password)
        }
    }

    private fun validateForm(email: String?, password: String?): Boolean {
        val isEmailValid = email != null && email.isNotBlank() && email.contains("@")
        val isPasswordValid = password != null && password.isNotBlank() && password.length >= 6
        return isEmailValid && isPasswordValid
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        checkLoginStatus()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val email = binding.emailLayout
        val password = binding.passwordLayout
        val loginButton = binding.loginButton

        email.editText?.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        password.editText?.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            loginButton.isEnabled = isValid
        }
        loginButton.setOnClickListener {
            loginButton.hideKeyboard()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getLoginData().observe(viewLifecycleOwner, {
                if (it?.token!!.isNotBlank() && it.token.isNotEmpty()) {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean(getString(R.string.preference_file_key), true)
                    editor.apply()
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.newsListFragment)
                } else {
                    Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        return binding.root
    }

    private fun checkLoginStatus() {
        val loginStatus =
            sharedPreferences.getBoolean(getString(R.string.preference_file_key), false)
        if (loginStatus)
            findNavController().navigate(R.id.newsListFragment)
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}