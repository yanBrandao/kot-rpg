package br.com.woodriver.rpg.domain

import com.fasterxml.jackson.annotation.JsonProperty

class BlizzardItem(
        @JsonProperty("_links")
        val links: Any,
        @JsonProperty("assets")
        val assets: List<BlizzardItemDetail>
) {
}