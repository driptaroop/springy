package org.dripto.springy.web.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.collect
import org.dripto.springy.core.model.Simple
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.http.codec.multipart.Part
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.File
import java.io.InputStream
import java.io.SequenceInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.security.Principal
import java.time.ZoneId
import java.util.*
import java.util.function.BiFunction


@RestController
@RequestMapping("/v1/method")
@SessionAttributes("pet")
class MethodController{
    val dbf = DefaultDataBufferFactory()
    @PostMapping("/arguments/{path}")
    suspend fun accessMethodArguments(
            /**
             * Access to the full ServerWebExchange — container for the HTTP request
             * and response, request and session attributes, checkNotModified methods,
             * and others.
             */
            serverWebExchange: ServerWebExchange,
            /**
             * Access to the HTTP request or response.
             */
            httpRequest: ServerHttpRequest,
            httpResponse: ServerHttpResponse,
            /**
             * Access to the session. This does not force the start of a new session
             * unless attributes are added. Supports reactive types.
             */
            webSession: WebSession,
            /**
             * The currently authenticated user — possibly a specific
             * "Principal" implementation class if known. Supports reactive types.
             */
            principal: Principal,
            /**
             * The HTTP method of the request.
             * in case multiple methods are supported.
             */
            httpMethod: HttpMethod,
            /**
             * The current request locale, determined by the most specific
             * LocaleResolver available — in effect, the configured
             * LocaleResolver/LocaleContextResolver
             */
            locale: Locale,
            /**
             * The time zone associated with the current request,
             * as determined by a LocaleContextResolver.
             */
            zoneId: ZoneId,
            /**
             * For access to URI template variables.
             */
            @PathVariable(name = "path") path: String,
            /**
             * For access to name-value pairs in URI path segments.
             * If the method parameter type is Map and a matrix variable name
             * is specified, then the matrix variable value is converted to a
             * Map assuming an appropriate conversion strategy is available.
             * If the method parameter is Map<String, String> or
             * MultiValueMap<String, String> and a variable name is not
             * specified, then the map is populated with all matrix variable
             * names and values.
             */
            @MatrixVariable(name = "keyA") keyA: String,
            /**
             * For access to Servlet request parameters.
             * Parameter values are converted to the declared method argument type.
             * Note that use of @RequestParam is optional — for example, to set its
             * attributes. See “Any other argument” later in this table.
             * In Spring WebFlux, "request parameters" map to query parameters only.
             * If the method parameter type is Map and a request parameter name is
             * specified, then the request parameter value is converted to a Map assuming
             * an appropriate conversion strategy is available.
             * If the method parameter is Map<String, String> or MultiValueMap<String, String>
             * and a parameter name is not specified, then the map parameter is populated
             * with all request parameter names and values.
             */
            @RequestParam(name = "query1") query1: String,
            /**
             * For access to request headers. Header values are converted to
             * the declared method argument type.
             * If the method parameter is Map<String, String>,
             * MultiValueMap<String, String>, or HttpHeaders then the map is
             * populated with all header names and values.
             */
            @RequestHeader("header1") header1: String,
            /**
             * Annotation which indicates that a method parameter should be bound to an HTTP cookie.
             * The method parameter may be declared as type Cookie or as cookie value type
             * (String, int, etc.).
             */
            @CookieValue("JSESSIONID") cookie: String,
            /**
             * For access to the HTTP request body. Body content is converted
             * to the declared method argument type by using HttpMessageReader
             * instances. Supports reactive types.
             */
            @RequestBody body: Simple,
            /**
             * For access to request headers and body.
             * The body is converted with HttpMessageReader instances.
             * Supports reactive types.
             * NOT WORKING. HAVE TO CHECK WHY!!
             */
            //httpEntity: RequestEntity<Simple>,
            /**
             * For access to a part in a multipart/form-data request. Supports reactive types.
             */
            //@RequestPart("multipart") filepart: File,
            /**
             * For access to errors from validation and data binding
             * for a command object, i.e. a @ModelAttribute argument.
             * An Errors, or BindingResult argument must be declared
             * immediately after the validated method argument.
             */
            //error: Errors,
            //bindingResult: BindingResult,
            /**
             * For marking form processing complete, which triggers cleanup
             * of session attributes declared through a class-level
             * @SessionAttributes annotation
             */
            sessionStatus: SessionStatus,

            /**
             * For preparing a URL relative to the current request’s
             * host, port, scheme, and path
             */
            uriComponentsBuilder: UriComponentsBuilder,
            /**
             * For access to any session attribute — in contrast to
             * model attributes stored in the session as a result
             * of a class-level @SessionAttributes declaration.
             */
            //@SessionAttribute("pet") pet: String
            /**
             * For access to request attributes
             */
            @RequestAttribute("requestAttr") requestAttr: String
    ): String {

        println("serverWebExchange $serverWebExchange")
        println("httpRequest $httpRequest")
        println("httpResponse $httpResponse")
        println("webSession $webSession")
        println("principal $principal")
        println("httpMethod $httpMethod")
        println("locale $locale")
        println("path $path")
        println("keyA $keyA")
        println("query1 $query1")
        println("header1 $header1")
        println("cookie $cookie")
        println("body $body")
        //println(httpEntity)
        //println(filepart)
        //println(error)
        //println(bindingResult)
        println("sessionStatus $sessionStatus")
        println("uriComponentsBuilder ${uriComponentsBuilder.build().toUri().toASCIIString()}")
        //println(pet)
        println("requestAttr $requestAttr")
        return "OK"
    }

    @PostMapping("/multipart", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun multipartMapping(
            @RequestPart("name") metadata: Part,
            @RequestPart("file") filepart: FilePart
    ) {
        //println(form)
        println(metadata)
        println(filepart)
        filepart.content()
                .reduce(object : InputStream() {
                    override fun read() = -1
                }) {  s: InputStream, d -> SequenceInputStream(s, d.asInputStream()) }
                .map { it.readAllBytes() }
                .subscribe {
                    File(filepart.filename())
                            .also {
                                it.createNewFile()
                            }.writeBytes(it)
                }
    }

    data class MetaData (
            val name: String
    )

    data class MyForm (
            val name: String,
            val file: MultipartFile
    )
}
