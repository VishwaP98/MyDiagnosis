package com.example.mydiagnosis.viewmodel

import android.content.Context
import android.content.SharedPreferences
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserViewModelTest {

    @MockK(relaxed = true)
    private lateinit var context : Context

    @MockK(relaxed = true)
    private lateinit var sharedPreferences: SharedPreferences

    @MockK(relaxed = true)
    private lateinit var sharedPreferencesEditor : SharedPreferences.Editor

    private lateinit var userViewModel: UserViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        userViewModel = UserViewModel()

        every { context.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.edit() } returns sharedPreferencesEditor

    }

    @Test
    fun `saveUserInfo - when given name, age, and gender - correctly stores name`() {

        userViewModel.saveUserInfo(context, "name", 10, "gender")

        verify(exactly = 1) {
            sharedPreferencesEditor.putString(any(), "name")
        }

    }

    @Test
    fun `saveUserInfo - when given name, age, and gender - correctly stores age`() {

        userViewModel.saveUserInfo(context, "name", 10, "gender")

        verify(exactly = 1) {
            sharedPreferencesEditor.putInt(any(), 10)
        }

    }

    @Test
    fun `saveUserInfo - when given name, age, and gender - correctly stores gender`() {

        userViewModel.saveUserInfo(context, "name", 10, "gender")

        verify(exactly = 1) {
            sharedPreferencesEditor.putString(any(), "gender")
        }

    }


}