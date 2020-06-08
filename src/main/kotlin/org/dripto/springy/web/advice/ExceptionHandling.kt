package org.dripto.springy.web.advice

import org.springframework.web.bind.annotation.ControllerAdvice
import org.zalando.problem.spring.webflux.advice.ProblemHandling


@ControllerAdvice
internal class ExceptionHandling : ProblemHandling

