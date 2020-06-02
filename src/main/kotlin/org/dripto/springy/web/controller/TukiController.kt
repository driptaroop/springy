package org.dripto.springy.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/tuki")
@RestController
class TukiController {
    @GetMapping
    suspend fun callTuki() = "Hello, Dripto!!"
}
