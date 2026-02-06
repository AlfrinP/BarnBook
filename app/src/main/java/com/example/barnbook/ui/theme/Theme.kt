package com.example.barnbook.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

// Farm-themed Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = DarkGreenLight,
    onPrimary = Color.White,
    primaryContainer = DarkGreen,
    onPrimaryContainer = LightCreamText,
    
    secondary = WarmBrownLight,
    onSecondary = Color.White,
    secondaryContainer = WarmBrownDark,
    onSecondaryContainer = LightCreamText,
    
    tertiary = SkyBlueLight,
    onTertiary = Color.White,
    
    background = DarkBrownBackground,
    onBackground = LightCreamText,
    
    surface = DarkSurface,
    onSurface = LightCreamText,
    surfaceVariant = DarkSurface,
    onSurfaceVariant = LightCreamText.copy(alpha = 0.7f),
    
    error = ErrorRed,
    onError = Color.White
)

// Farm-themed Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = FreshGreen,
    onPrimary = Color.White,
    primaryContainer = FreshGreenLight,
    onPrimaryContainer = DarkBrownText,
    
    secondary = WarmBrown,
    onSecondary = Color.White,
    secondaryContainer = WarmBrownLight,
    onSecondaryContainer = DarkBrownText,
    
    tertiary = SkyBlue,
    onTertiary = Color.White,
    
    background = CreamBackground,
    onBackground = DarkBrownText,
    
    surface = SurfaceWhite,
    onSurface = DarkBrownText,
    surfaceVariant = CreamBackgroundLight,
    onSurfaceVariant = DarkBrownText.copy(alpha = 0.7f),
    
    error = ErrorRed,
    onError = Color.White
)

// Custom shapes with rounded corners
private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun BarnBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to use farm theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}