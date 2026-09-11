# AXION Calc

Kalkulator Android sederhana berbasis Kotlin + Jetpack Compose.

## Spesifikasi
- Min SDK: Android 8 (API 26)
- Target SDK: Android 14 (API 34)
- Compile SDK: API 37
- Gradle 9.5.0
- Android Gradle Plugin 9.3.0
- Kotlin 2.4.20 (built-in via AGP 9)
- Jetpack Compose BOM 2026.08.00

## Fitur
- Operasi `+`, `−`, `×`, `÷`, `%`, desimal, dan tanda kurung.
- Tombol AC, backspace, dan evaluasi `=`.
- Menjadi aplikasi kalkulator default melalui Android RoleManager (API 29+).
- Bahasa: Follow phone, English, Indonesia.
- Tampilan: Follow system, Light, Dark.
- Dynamic Color pada Android 12+ dengan fallback untuk versi lama.
- Privacy Policy lengkap (data tidak dikirim ke server).
- Feedback via email.
- Banner promosi produk AXION.
- Unit test untuk engine kalkulator dan smoke UI test.

## Build lokal
```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

APK debug akan tersedia di `app/build/outputs/apk/debug/app-debug.apk`.

## CI / CD
Build dan test otomatis via **GitHub Actions** (`.github/workflows/android.yml`).
