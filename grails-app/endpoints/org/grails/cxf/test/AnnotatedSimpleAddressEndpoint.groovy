package org.grails.cxf.test

import org.grails.cxf.utils.EndpointType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod

/**
 */
@GrailsCxfEndpoint(address = "/v2/#name", expose = EndpointType.SIMPLE, soap12 = true)
class AnnotatedSimpleAddressEndpoint {

    @WebMethod
    String simpleMethod(String param) {
        return param.toString()
    }
}
