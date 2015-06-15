package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult

@GrailsCxfEndpoint(address = "/i/love/turtles/v1/zombie")
class AnnotatedLongAddressService {

    @WebMethod(operationName = "simpleMethod")
    @WebResult(name = "simpleResult")
    String simpleMethod(@WebParam(name = "param") String param) {
        return param.toString()
    }
}
