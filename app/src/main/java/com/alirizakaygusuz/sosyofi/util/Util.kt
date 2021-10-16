package com.alirizakaygusuz.sosyofi

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidTextControl(string: String): Boolean {
    val ps: Pattern = Pattern.compile("^[a-zA-Z ]+$")
    val ms: Matcher = ps.matcher(string)
    val bs: Boolean = ms.matches()
    return bs
}
