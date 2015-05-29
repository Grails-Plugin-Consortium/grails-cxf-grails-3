package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebService

@GrailsCxfEndpoint(soap12 = true)
class PlaneService implements IPlaneService {

    Boolean canFly() {
        true
    }

    Boolean canFloat() {
        false
    }

    String ignoreMe(Boolean bool) {
        bool.toString()
    }
}

@WebService
interface IPlaneService {
    @WebMethod
    Boolean canFly()

    @WebMethod
    Boolean canFloat()
}
