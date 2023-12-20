package com.example.common

import io.micronaut.aop.Around
import java.lang.annotation.Documented

@Documented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Around
annotation class NotNull
