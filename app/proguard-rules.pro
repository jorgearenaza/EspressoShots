# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# Keep the line number information, useful for Stack Trace
-renamesourcefileattribute SourceFile

# Preserve all annotations
-keepattributes *Annotation*

# For native methods, keep the names
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep all custom application classes
-keep class com.jorgearenaza.espressoshots.** { *; }

# Keep Room entities
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }

# Keep DataStore classes
-keep class androidx.datastore.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.** class * { *; }
-keep interface dagger.hilt.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-keep interface kotlinx.coroutines.** { *; }

# Retrofit and OkHttp (if used)
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class retrofit2.** { *; }

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
