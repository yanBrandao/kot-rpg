package br.com.woodriver.rpg.domains

import com.fasterxml.jackson.annotation.JsonProperty

class BlizzardItem(
        @JsonProperty("_links")
        val links: Any,
        @JsonProperty("assets")
        val assets: List<BlizzardItemDetail>
) {
}