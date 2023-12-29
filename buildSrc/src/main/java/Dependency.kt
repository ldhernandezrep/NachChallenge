sealed class Dependency(private val version: String, private val pakage: String) {
    fun full() = "$pakage:$version"

    sealed class AnnotationProcessor(private val version: String, private val pakage: String) :
        Dependency(version, pakage) {

    }

    sealed class Kapt(private val version: String, private val pakage: String) :
        Dependency(version, pakage) {
        object Hilt :
            Kapt(version = Versions.hilt, pakage = "com.google.dagger:hilt-android-compiler")
    }

    object Hilt : Dependency(version = Versions.hilt, "com.google.dagger:hilt-android")

    object Coroutines :
        Dependency(
            version = Versions.coroutines,
            pakage = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
        )

    object LiveData :
        Dependency(version = Versions.liveData, pakage = "androidx.lifecycle:lifecycle-livedata-ktx")

    object ViewModel :
        Dependency(version = Versions.viewModel, pakage = "androidx.lifecycle:lifecycle-viewmodel-ktx")

    object Paging :
        Dependency(version = Versions.paging, pakage = "androidx.paging:paging-runtime")

    object CoreKtx : Dependency(version = Versions.androidCore, pakage = "androidx.core:core-ktx")

    object AppCompat : Dependency(version = Versions.compat, pakage = "androidx.appcompat:appcompat")
    object ConstraintLayout : Dependency(version = Versions.constraint, pakage = "androidx.constraintlayout:constraintlayout")
    object Palette : Dependency(version = Versions.palette, pakage = "androidx.palette:palette")
    object NavigationFragment : Dependency(version = Versions.navigation, pakage = "androidx.navigation:navigation-fragment-ktx")
    object NavigationUI : Dependency(version = Versions.navigation, pakage = "androidx.navigation:navigation-ui-ktx")
    object Glide : Dependency(version = Versions.glide, pakage = "com.github.bumptech.glide:glide")

}