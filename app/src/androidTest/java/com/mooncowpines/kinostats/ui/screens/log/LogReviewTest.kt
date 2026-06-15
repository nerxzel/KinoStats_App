package com.mooncowpines.kinostats.ui.screens.log
import androidx.compose.runtime.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mooncowpines.kinostats.domain.model.Movie
import com.mooncowpines.kinostats.ui.screens.logDetail.LogDetailContent
import com.mooncowpines.kinostats.ui.screens.logDetail.LogDetailScreenState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class LogDetailReviewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun logDetailScreen_reviewField_capturesInputAndSaves() {
        var currentState = LogDetailScreenState(logText = "")
        var capturedText = ""

        composeTestRule.setContent {
            var state by remember { mutableStateOf(currentState) }

            LogDetailContent(
                state = state,
                movie = Movie(
                    id = 1L,
                    title = "Test Movie",
                    posterUrl = "",
                    backDropUrl = "",
                    overview = "Resumen de prueba",
                    releaseDate = LocalDate.now(),
                    genres = emptyList(),
                    actors = "Actor 1, Actor 2",
                    duration = 120,
                    originCountry = "USA",
                    director = "Director X",
                    productionCompany = "Studio Y"
                ),
                onNavigateBack = { },
                onSaveReview = { },
                onRatingChange = { },
                onReviewTextChange = {
                    state = state.copy(logText = it)
                    capturedText = it
                },
                onShowCalendar = { },
                onDateSelected = { }
            )
        }

        val testInput = "Esta es una gran película."
        composeTestRule.onNodeWithText("Write a review...").performTextInput(testInput)


        assertEquals("El texto de la reseña no coincide", testInput, capturedText)
    }
}