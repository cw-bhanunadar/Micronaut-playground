package com.example.common

import com.example.event.Publisher
import com.example.util.logger
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.jsoup.nodes.Document
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.io.FileOutputStream

@Controller("/test")
class TestController {
    private val log = logger()
    @Inject
    private lateinit var templateEngine: TemplateEngine

    @Inject
    lateinit var publisher: Publisher
    @Get("/simple/{params}")
    suspend fun pathVariable(params: Long): Long? {
        publisher.emitEventNumber(params)
        log.error("This is a test error")
        return params
    }

    @Get("/explicit/{params}")
    suspend fun pathVariableExplicit(@PathVariable params: String?): String? {
        return params
    }

    @Get("/query-params")
    suspend fun queryParams(@QueryValue first: String?, @QueryValue second: String?): String? {
        return "$first $second"
    }

    @Get("/complex-query-params{?request*}")
    suspend fun complexQueryParams(request: ComplexInput): String? {
        return "${request.first} ${request.second}"
    }

    @Post("/post-body")
    suspend fun postBody(@Body request: ComplexInput): String? {
        return "${request.first} ${request.second}"
    }

    @Get("/test-sentry")
    suspend fun testSentry() {
        throw ArithmeticException("Capture in Entry")
    }

    @Get("/test-thymeleaf")
    suspend fun testThymeleaf(): String {
        val context = Context()
        var map = mutableMapOf<String, Any?>()
        map.put("message", "This is a variable text")
        context.setVariables(map)
        return templateEngine.process("hello_world", context)
    }

    @Get("/generate-pdf")
    suspend fun generatePdf() {
        var html = testThymeleaf()
        val outputPath = "/Users/bhanu/temp/your_filename.pdf"
        val document = getHtmlDocument(html)
        FileOutputStream(outputPath).use { os ->
            val builder = PdfRendererBuilder()
            builder.withUri(outputPath)
            builder.toStream(os)
            builder.withW3cDocument(W3CDom().fromJsoup(document), "/")
            builder.run()
        }
    }

    private fun getHtmlDocument(html: String): Document {
        val document: Document = Jsoup.parse(html, "UTF-8")
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        return document
    }
}
