package org.dripto.springy.extension

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.Color.*

inline fun printlnWithColor(message: Any?, color: Ansi.Color) = println(Ansi.ansi().fg(color).a(message).reset())

inline fun pr(message: Any?) = printlnWithColor(message, RED)
inline fun pg(message: Any?) = printlnWithColor(message, GREEN)
inline fun pblk(message: Any?) = printlnWithColor(message, BLACK)
inline fun py(message: Any?) = printlnWithColor(message, YELLOW)
inline fun pblu(message: Any?) = printlnWithColor(message, BLUE)
inline fun pm(message: Any?) = printlnWithColor(message, MAGENTA)
inline fun pc(message: Any?) = printlnWithColor(message, CYAN)
inline fun pw(message: Any?) = printlnWithColor(message, WHITE)
inline fun pd(message: Any?) = printlnWithColor(message, DEFAULT)
