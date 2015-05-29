package org.grails.cxf.test

import org.grails.cxf.utils.EndpointType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebService

@GrailsCxfEndpoint(expose = EndpointType.JAX_WS, excludes = ['dontHonk'])
class AnnotatedExcludesMixedCarService implements IAnnotatedExcludesMixedCarService {

    String honkHorn() {
        'HONK'
    }

    String dontHonk() {
        'BEEP'
    }

    String stop() {
        'BRAKES'
    }

    String start() {
        'GAS'
    }
}

@WebService
interface IAnnotatedExcludesMixedCarService {

    @WebMethod
    String honkHorn()

    @WebMethod
    String dontHonk()

    @WebMethod
    String stop()

    @WebMethod
    String start()
}
