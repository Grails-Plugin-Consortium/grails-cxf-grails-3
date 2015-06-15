package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult
import org.grails.cxf.utils.EndpointType

@GrailsCxfEndpoint(address = "/v1.4/#name")
class CxfJaxAddressService {

    @WebResult(name='legacyResult')
    @WebMethod(operationName='legacyMethod')
    String legacyMethod(@WebParam(name='param') String param) {
        //cxf doesn't like GStringImpl so make sure to convert to java.lang.String
        return "legacy ${param}".toString()
    }

    String hiddenMethod(String param){
        return "hidden ${param}".toString()
    }
}
