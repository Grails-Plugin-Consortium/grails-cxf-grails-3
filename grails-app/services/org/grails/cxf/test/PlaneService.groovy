package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebService

@GrailsCxfEndpoint(soap12 = true)
class PlaneService {

    @WebMethod
    Boolean canFly() {
        true
    }

    @WebMethod
    Boolean canFloat() {
        false
    }

    String ignoreMe(Boolean bool) {
        bool.toString()
    }
}
