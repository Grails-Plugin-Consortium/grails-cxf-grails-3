package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebResult
import javax.jws.WebService

@GrailsCxfEndpoint
class CarService {

    @WebMethod
    @WebResult(name = 'honk')
    String honkHorn() {
        'HONK'
    }

    String dontHonk() {
        'BEEP'
    }

    @WebMethod
    @WebResult(name = 'stop')
    String stop() {
        'BRAKES'
    }

    @WebMethod
    @WebResult(name = 'start')
    String start() {
        'GAS'
    }
}
