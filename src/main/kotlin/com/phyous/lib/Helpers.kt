package com.phyous.lib

import java.io.File

object Helpers {

    val INPUT_DIRECTORY = System.getProperty("user.dir") + "/inputs"

    fun readLines(path: String): List<String> = File(path).bufferedReader().readLines()

    fun getProblemData(inputName: String): List<String> {
        val filePath = "$INPUT_DIRECTORY/$inputName.input"
        return readLines(filePath)
    }
}