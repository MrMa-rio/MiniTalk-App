package com.marsn.minitalk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.marsn.minitalk.R

// Set of Material typography styles to start with

val SairaSemiCondensed = FontFamily(
    Font(R.font.saira_semi_condensed_medium, FontWeight.Medium),
    Font(R.font.saira_semi_condensed_regular, FontWeight.Normal),
    Font(R.font.saira_semi_condensed_bold, FontWeight.Bold),
    Font(R.font.saira_semi_condensed_extra_bold, FontWeight.ExtraBold)
)

val SairaSemiExpanded = FontFamily(
    Font(R.font.saira_semi_expanded_regular, FontWeight.Medium),
    Font(R.font.saira_semi_expanded_regular, FontWeight.Normal),
    Font(R.font.saira_semi_expanded_bold, FontWeight.Bold),
    Font(R.font.saira_semi_expanded_extra_bold, FontWeight.ExtraBold)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = SairaSemiExpanded,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    ),




    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)