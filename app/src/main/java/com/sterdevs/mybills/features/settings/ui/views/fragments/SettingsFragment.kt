package com.sterdevs.mybills.features.settings.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.ui.events.validations.ValidationEventListener
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.states.AppGlobalStateObserver
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentSettingsBinding
import com.sterdevs.mybills.features.settings.ui.events.SettingsEvent
import com.sterdevs.mybills.features.settings.ui.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(), ScreenUtils, ValidationEventListener, AppGlobalStateObserver {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by viewModels<SettingsViewModel>()

    //Views
    private lateinit var nameTitleText: TextView
    private lateinit var usernameTitleText: TextView

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var phoneNumberInputLayout: TextInputLayout
    private lateinit var oldPasswordInputLayout: TextInputLayout
    private lateinit var newPasswordInputLayout: TextInputLayout
    private lateinit var repeatedPasswordInputLayout: TextInputLayout
    private lateinit var btnUpdatePersonalInfo: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        getViews()
        initializeDefaultValues()
        addViewsEventsListeners()
        subscribeToObservables()

        return binding.root
    }

    override fun getViews() {
        nameTitleText = binding.fragmentSettingsTitleName
        usernameTitleText = binding.fragmentSettingsTitleUsername

        nameInputLayout = binding.fragmentSettingsNameInputLayout
        usernameInputLayout = binding.fragmentSettingsUsernameInputLayout
        phoneNumberInputLayout = binding.fragmentSettingsPhoneNumberInputLayout
        oldPasswordInputLayout = binding.fragmentSettingsPasswordInputLayout
        newPasswordInputLayout = binding.fragmentSettingsNewPasswordInputLayout
        repeatedPasswordInputLayout = binding.fragmentSettingsRepeatedPasswordInputLayout

        btnUpdatePersonalInfo = binding.fragmentSettingsBtnUpdatePersonalInputLayout
    }

    override fun initializeDefaultValues() {
        nameTitleText.text = viewModel.personalInfoState.value.name
        usernameTitleText.text = viewModel.personalInfoState.value.username

        nameInputLayout.editText?.setText(viewModel.personalInfoState.value.name)
        usernameInputLayout.editText?.setText(viewModel.personalInfoState.value.username)
        phoneNumberInputLayout.editText?.setText(viewModel.personalInfoState.value.phoneNumber)
    }

    override fun addViewsEventsListeners() {
        addPersonalInfoEvents()
        addCaretakerEvents()
    }

    private fun addPersonalInfoEvents() {
        nameInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.NameChanged(text.toString().trim()))
        }
        usernameInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.UsernameChanged(text.toString().trim()))
        }
        phoneNumberInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.PhoneNumberChanged(text.toString().trim()))
        }
        oldPasswordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.OldPasswordChanged(text.toString().trim()))
        }
        newPasswordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.NewPasswordChanged(text.toString().trim()))
        }
        repeatedPasswordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(SettingsEvent.RepeatedPasswordChanged(text.toString().trim()))
        }
        btnUpdatePersonalInfo.setOnClickListener {
            viewModel.onEvent(SettingsEvent.PersonalInformationSubmit)
        }
    }

    private fun addCaretakerEvents() {

    }

    override fun subscribeToObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.personalInfoState.collect { state ->
                        // Update UI based on state changes
                        nameInputLayout.error = state.nameError
                        usernameInputLayout.error = state.usernameError
                        phoneNumberInputLayout.error = state.phoneNumberError
                        oldPasswordInputLayout.error = state.oldPasswordError
                        newPasswordInputLayout.error = state.newPasswordError
                        repeatedPasswordInputLayout.error = state.repeatedPasswordError
                    }
                }

                launch {
                    viewModel.validationEvent.collectLatest { event ->
                        handleValidationEvent(event)
                    }
                }

                launch {
                    observeUserStateChanged()
                }

            }
        }
    }

    override fun handleValidationEvent(event: ValidationEvent?) {
        when (event) {
            is ValidationEvent.Failed -> {
                context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setTitle(resources.getString(R.string.settings))
                        .setMessage(event.reason)
                        .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                            // Respond to positive button press
//                            viewModel.onEvent(SettingsEvent.PersonalInformationSubmit)
                        }
                        .show()
                }
            }

            is ValidationEvent.Pending -> {

            }

            is ValidationEvent.Success -> {
                context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setTitle(resources.getString(R.string.settings))
                        .setMessage("Information's has been successfully updated 🔥.")
                        .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                        }
                        .show()
                }
            }

            else -> {}
        }

        oldPasswordInputLayout.editText?.setText("")
        newPasswordInputLayout.editText?.setText("")
        repeatedPasswordInputLayout.editText?.setText("")
    }

    override suspend fun observeUserStateChanged() {
        AppGlobalState.userState.collect {
            it?.let {
                initializeDefaultValues()
            }
        }
    }
}