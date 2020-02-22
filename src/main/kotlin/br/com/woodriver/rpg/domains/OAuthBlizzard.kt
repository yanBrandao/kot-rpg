package br.com.woodriver.rpg.domains

import com.fasterxml.jackson.annotation.JsonProperty

class OAuthBlizzard(
        @JsonProperty("access_token")
        val accessToken: String,
        @JsonProperty("token_type")
        val tokenType: String,
        @JsonProperty("expires_in")
        val expiresIn: Int) {
}