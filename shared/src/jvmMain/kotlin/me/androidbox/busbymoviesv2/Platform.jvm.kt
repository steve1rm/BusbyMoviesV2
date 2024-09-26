package me.androidbox.busbymoviesv2

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun isDebug(): Boolean {
    return System.getProperty("DEBUG") != null
}