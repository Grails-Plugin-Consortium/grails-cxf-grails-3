package org.grails.cxf.test

import org.apache.cxf.interceptor.InInterceptors
import org.grails.cxf.utils.EndpointType
import org.grails.cxf.test.soap.interceptor.CustomLoggingInInterceptor

@InInterceptors (classes = [CustomLoggingInInterceptor])
class CxfAnnotationInterceptorEndpoint {

    static expose = [EndpointType.SIMPLE]
    static soap12 = true

    String simpleMethod(String param) {
        return param.toString()
    }
}
