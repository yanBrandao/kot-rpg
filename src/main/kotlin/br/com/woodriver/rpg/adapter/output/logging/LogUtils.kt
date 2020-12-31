package br.com.woodriver.rpg.adapter.output.logging

import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java)

fun Any.objectToJson(): String = Gson().toJson(this)
