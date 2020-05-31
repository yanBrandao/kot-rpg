package br.com.woodriver.rpg.gateway.client

class ErrorClass(){
    var code: String = ""
    var message: String = ""

    constructor(code: String, message: String): this(){
        this.code = code
        this.message = message
    }
}