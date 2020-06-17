package org.dripto.springy.extension

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.Color.*
fun printlnWithColor(message: Any?, color: Ansi.Color) = println(Ansi.ansi().fg(color).a(message).reset())

fun pr(message: Any?) = printlnWithColor(message, RED)
fun pg(message: Any?) = printlnWithColor(message, GREEN)
fun pblk(message: Any?) = printlnWithColor(message, BLACK)
fun py(message: Any?) = printlnWithColor(message, YELLOW)
fun pblu(message: Any?) = printlnWithColor(message, BLUE)
fun pm(message: Any?) = printlnWithColor(message, MAGENTA)
fun pc(message: Any?) = printlnWithColor(message, CYAN)
fun pw(message: Any?) = printlnWithColor(message, WHITE)
fun pd(message: Any?) = printlnWithColor(message, DEFAULT)
