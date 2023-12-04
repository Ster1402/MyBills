package com.sterdevs.mybills.features.authentication.ui.views.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.domain.models.ui.ScreenUtils
import com.sterdevs.mybills.core.domain.models.validations.ValidationEvent
import com.sterdevs.mybills.core.domain.models.validations.ValidationEventListener
import com.sterdevs.mybills.databinding.FragmentLoginBinding
import com.sterdevs.mybills.features.authentication.ui.events.LoginFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment(), ValidationEventListener, ScreenUtils {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var submitButton: Button
    private lateinit var goToRegisterButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get the binding
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Use view model
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Get widgets
        getViews()

        // Init default values
        initializeDefaultValues()

        // Add listener
        addViewsEventsListeners()

        // Add live data observers
        subscribeToObservables()

        return binding.root
    }

    override fun getViews() {
        usernameLayout = binding.fragmentLoginUsername
        passwordLayout = binding.fragmentLoginPassword
        submitButton = binding.fragmentLoginBtnLogin
        goToRegisterButton = binding.fragmentLoginBtnRegister
    }

    override fun initializeDefaultValues() {
        usernameLayout.editText?.setText(viewModel.state.value?.username)
        passwordLayout.editText?.setText(viewModel.state.value?.password)
        usernameLayout.error = viewModel.state.value?.usernameError
        passwordLayout.error = viewModel.state.value?.passwordError
    }

    override fun addViewsEventsListeners() {
        // Submit
        submitButton.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
        }
        // Go to register
        goToRegisterButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        // Login
        submitButton.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
        }
        //
        usernameLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.UsernameChanged(text.toString()))
        }
        passwordLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.PasswordChanged(text.toString()))
        }
    }

    override fun subscribeToObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe state changes
                viewModel.state.collect { state ->
                    usernameLayout.error = state.usernameError
                    passwordLayout.error = state.passwordError
                }
            }

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe validation events
                viewModel.validationEvent.collectLatest { event ->
                    handleValidationEvent(event)
                }
            }
        }
    }

    override fun handleValidationEvent(event: ValidationEvent?) {
        // TODO: If the validation is successful then login the user
        if (event !is ValidationEvent) return

        when (event) {
            is ValidationEvent.Success -> {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }

            is ValidationEvent.Failed -> {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }

            is ValidationEvent.Pending -> {
                // TODO: Show loading screen
                Toast.makeText(context, "Pending", Toast.LENGTH_SHORT).show()
            }
        }
    }
}