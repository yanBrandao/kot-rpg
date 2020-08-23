package br.com.woodriver.blizzard.domains

import com.fasterxml.jackson.annotation.JsonProperty

class BlizzardItem(
        @JsonProperty("_links")
        val links: Any,
        @JsonProperty("assets")
        val assets: List<BlizzardItemDetail>
) {
}