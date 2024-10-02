package me.androidbox.busbymoviesv2.core.presentation.utils

actual fun getScreenDensity(): String {
  let screenScale = UIScreen.main.scale
  switch screenScale {
      case 1.0:
      return "LDPI"
      case 2.0:
      return "XHDPI"
      case 3.0
      return "XXHDPI"
      default:
      return "Unknown"
  }
}