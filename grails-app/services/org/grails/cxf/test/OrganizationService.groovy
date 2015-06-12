package org.grails.cxf.test

import org.grails.cxf.utils.GrailsCxfEndpoint
import org.springframework.beans.factory.annotation.Autowired

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService
import javax.jws.soap.SOAPBinding

//@WebService(portName = "OrganizationPort", serviceName = "OrganizationService", name = "OrganizationService")
@GrailsCxfEndpoint(name = 'OrganizationService')
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, use = SOAPBinding.Use.LITERAL, style = SOAPBinding.Style.DOCUMENT)
class OrganizationService {

    @Autowired
    private BoatService boatService
    @Autowired
    private CarService carService

    @WebMethod
    String goFish(@WebParam(partName = "KurumSorgulamaTalep", name = "KurumSorgulamaTalep", targetNamespace = "http://akum.compugroup.com") String kurumKodu) {
        boatService.fish()
    }

    @WebMethod
    String goHonk(@WebParam(partName = "KurumSorgulamaTalep", name = "KurumSorgulamaTalep", targetNamespace = "http://akum.compugroup.com") String kurumKodu) {
        carService.honkHorn()
    }
}