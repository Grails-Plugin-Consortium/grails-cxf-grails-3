package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam

@GrailsCxfEndpoint
class LegacyCxfService {

    @WebMethod
    String legacyMethod(@WebParam String param) {
        return "legacy ${param}".toString()
    }
}