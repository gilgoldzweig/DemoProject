package com.example.gilgoldzweig.juul.demo.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections.emptyList

data class PodsResponse(@JsonProperty("pods")
                        var pods: List<Pod> = emptyList())