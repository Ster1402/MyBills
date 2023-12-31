package com.sterdevs.mybills.features.authentication.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.ui.events.validations.ValidationEventListener
import com.sterdevs.mybills.databinding.FragmentLoginBinding
import com.sterdevs.mybills.features.authentication.ui.events.LoginFormEvent
import com.sterdevs.mybills.features.authentication.ui.events.RegistrationFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(), ValidationEventListener, ScreenUtils {

    private val viewModel: LoginViewModel by viewModels()
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

        // Get widgets
        getViews()

        // Init default values
        initializeDefaultValues()

        // Add listener
        addViewsEventsListeners()

        // Add observers
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
        usernameLayout.editText?.setText(viewModel.state.value.username)
        passwordLayout.editText?.setText(viewModel.state.value.password)
        usernameLayout.error = viewModel.state.value.usernameError
        passwordLayout.error = viewModel.state.value.passwordError
    }

    override fun addViewsEventsListeners() {
        // Submit
        submitButton.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
        }
        // Go to register
        goToRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        // Login
        submitButton.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
        }
        //
        usernameLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.UsernameChanged(text.toString().trim()))
        }
        passwordLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.PasswordChanged(text.toString().trim()))
        }
    }

    override fun subscribeToObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    // Observe state changes
                    viewModel.state.collect { state ->
                        usernameLayout.error = state.usernameError
                        passwordLayout.error = state.passwordError
                    }
                }

                launch {
                    // Observe validation events
                    viewModel.validationEvent.collectLatest { event ->
                        handleValidationEvent(event)
                    }
                }
            }
        }
    }

    override fun handleValidationEvent(event: ValidationEvent?) {
        when (event) {
            is ValidationEvent.Success -> {
                findNavController().navigate(R.id.action_loginFragment_to_navigation_app_graph)
            }

            is ValidationEvent.Failed -> {
                context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setTitle(resources.getString(R.string.title_text_login))
                        .setMessage(event.reason)
                        .setNeutralButton(resources.getString(R.string.cancel)) { _, _ ->
                            // Respond to neutral button press
                        }
                        .setPositiveButton(resources.getString(R.string.try_again)) { _, _ ->
                            // Respond to positive button press
                            viewModel.onEvent(LoginFormEvent.Submit)
                        }
                        .show()
                }
            }

            is ValidationEvent.Pending -> {
                // TODO: Show loading screen
            }

            else -> {}
        }
    }
}