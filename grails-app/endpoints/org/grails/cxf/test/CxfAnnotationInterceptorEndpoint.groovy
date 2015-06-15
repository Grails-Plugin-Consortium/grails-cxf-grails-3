package org.grails.cxf.test

import org.apache.cxf.interceptor.InInterceptors
import org.grails.cxf.test.soap.interceptor.CustomLoggingInInterceptor
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod

@InInterceptors(classes = [CustomLoggingInInterceptor])
@GrailsCxfEndpoint(soap12 = true)
class CxfAnnotationInterceptorEndpoint {

    @WebMethod
    String simpleMethod(String param) {
        return param.toString()
    }
}
