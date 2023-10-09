package com.example.common

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Context
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.nio.charset.StandardCharsets

@Context
class ThymeleafTemplateConfig {

    @Bean
    fun thymeleafTemplateEngine(): TemplateEngine {
        val templateEngine = TemplateEngine()
        templateEngine.addTemplateResolver(htmlTemplateResolver())
        return templateEngine
    }

    @Bean
    fun htmlTemplateResolver(): ClassLoaderTemplateResolver {
        val pdfTemplateResolver = ClassLoaderTemplateResolver()
        pdfTemplateResolver.prefix = "classpath:/templates/"
        pdfTemplateResolver.suffix = ".html"
        pdfTemplateResolver.templateMode = TemplateMode.HTML
        pdfTemplateResolver.characterEncoding = StandardCharsets.UTF_8.name()
        return pdfTemplateResolver
    }
}
