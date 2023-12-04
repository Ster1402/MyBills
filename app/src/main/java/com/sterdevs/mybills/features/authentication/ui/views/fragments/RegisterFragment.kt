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
import com.sterdevs.mybills.databinding.FragmentRegisterBinding
import com.sterdevs.mybills.features.authentication.ui.events.RegistrationFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.RegisterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(), ValidationEventListener, ScreenUtils {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var phoneNumberInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var repeatedPasswordInputLayout: TextInputLayout
    private lateinit var submitRegisterButton: Button
    private lateinit var goToLoginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Use the view model
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        getViews()
        initializeDefaultValues()
        addViewsEventsListeners()
        subscribeToObservables()

        return binding.root
    }


    override fun handleValidationEvent(event: ValidationEvent?) {
        // TODO: If the validation is successful then register the user
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

    override fun getViews() {
        // Get widgets
        nameInputLayout = binding.fragmentRegisterName
        usernameInputLayout = binding.fragmentRegisterUsername
        phoneNumberInputLayout = binding.fragmentRegisterPhoneNumber
        passwordInputLayout = binding.fragmentRegisterPassword
        repeatedPasswordInputLayout = binding.fragmentRegisterRepeatedPassword
        submitRegisterButton = binding.fragmentRegisterBtnConfirm
        goToLoginButton = binding.fragmentRegisterBtnLogin
    }

    override fun initializeDefaultValues() {
        nameInputLayout.editText?.setText(viewModel.state.value.name)
        usernameInputLayout.editText?.setText(viewModel.state.value.username)
        phoneNumberInputLayout.editText?.setText(viewModel.state.value.phoneNumber)
        passwordInputLayout.editText?.setText(viewModel.state.value.password)
        repeatedPasswordInputLayout.editText?.setText(viewModel.state.value.repeatedPassword)

        nameInputLayout.error = viewModel.state.value.nameError
        usernameInputLayout.error = viewModel.state.value.usernameError
        phoneNumberInputLayout.error = viewModel.state.value.phoneNumberError
        repeatedPasswordInputLayout.error = viewModel.state.value.repeatedPasswordError
        repeatedPasswordInputLayout.error = viewModel.state.value.repeatedPasswordError
    }

    override fun addViewsEventsListeners() {
        // Set up listeners for user interactions
        goToLoginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        submitRegisterButton.setOnClickListener {
            viewModel.onEvent(RegistrationFormEvent.Submit)
        }
        nameInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.NameChanged(text.toString()))
        }
        usernameInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.UsernameChanged(text.toString()))
        }
        phoneNumberInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.PhoneNumberChanged(text.toString()))
        }
        passwordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.PasswordChanged(text.toString()))
        }
        repeatedPasswordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(text.toString()))
        }
    }

    override fun subscribeToObservables() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe the ViewModel's state
                viewModel.state.collect { state ->
                    // Update UI based on state changes
                    nameInputLayout.error = state.nameError
                    usernameInputLayout.error = state.usernameError
                    phoneNumberInputLayout.error = state.phoneNumberError
                    passwordInputLayout.error = state.passwordError
                    repeatedPasswordInputLayout.error = state.repeatedPasswordError
                }
            }

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe the validation events
                viewModel.validationEvent.collectLatest { event ->
                    handleValidationEvent(event)
                }
            }
        }

    }

}