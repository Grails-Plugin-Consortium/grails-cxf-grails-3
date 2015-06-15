package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam

@GrailsCxfEndpoint(soap12 = true)
class SimpleOneTwoEndpoint {

    @WebMethod
    String simpleMethod(@WebParam(name='param') String param) {
        return param.toString()
    }
}
