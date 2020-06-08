package org.dripto.springy.web.controller

import io.undertow.util.Headers
import org.springframework.http.HttpHeaders
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/request")
class RequestMappingController {
    @GetMapping("/path-matching/{*path}")
    suspend fun pathMatching(@PathVariable path: String) = path

    @GetMapping("/regex-matching/{name:[a-z]+}")
    suspend fun regexMatching(@PathVariable name: String) = name

    // also MultiValueMap can be used
    @GetMapping("/queryparam-matching", params = ["myParam=myValue"])
    suspend fun queryParamMatching(@RequestParam params: Map<String, String>) = params

    @GetMapping("/header-matching", headers = ["myHeader=myValue"])
    suspend fun headerMatching(@RequestHeader headers: HttpHeaders) = headers.toSingleValueMap()
}
