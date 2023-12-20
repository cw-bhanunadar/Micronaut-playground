package com.example.common

import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import jakarta.inject.Singleton

@Singleton
@InterceptorBean(NotNull::class)
open class NotNullInterceptor() : MethodInterceptor<Any, Any> {
    override fun intercept(context: MethodInvocationContext<Any, Any>?): Any? {
        var result: Any? = context?.proceed() ?: throw java.lang.Exception("Null value returned, kindly check")
        return result
    }
}
