package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService

@GrailsCxfEndpoint
class LegacyCxfService implements ILegacyCxfService {

    String legacyMethod(String param) {
        return "legacy ${param}".toString()
    }
}

@WebService
interface ILegacyCxfService {

    @WebMethod
    String legacyMethod(@WebParam String param)
}
