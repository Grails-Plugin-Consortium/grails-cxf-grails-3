package org.grails.cxf.test

import org.grails.cxf.utils.EndpointType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod

@GrailsCxfEndpoint()
class AnnotatedExcludesCarService {

    @WebMethod
    String honkHorn() {
        'HONK'
    }

    String dontHonk(){
        'BEEP'
    }

    @WebMethod
    String stop(){
        'BRAKES'
    }

    @WebMethod
    String start(){
        'GAS'
    }
}
