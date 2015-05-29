package org.grails.cxf.test

import org.springframework.beans.factory.annotation.Autowired

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult
import javax.jws.WebService
import javax.jws.soap.SOAPBinding


class OrganizationService implements IOrganizationService {

    @Autowired
    private BoatService boatService
    @Autowired
    private CarService carService

    String goFish(String kurumKodu) {
        boatService.fish()
    }

    String goHonk(String kurumKodu) {
        carService.honkHorn()
    }
}

@WebService(portName = "OrganizationPort", serviceName = "OrganizationService", name = "OrganizationService", targetNamespace = "http://akum.compugroup.com")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, use = SOAPBinding.Use.LITERAL, style = SOAPBinding.Style.DOCUMENT)
interface IOrganizationService {

    @WebMethod
    @WebResult
    String goFish(@WebParam(partName = "KurumSorgulamaTalep", name = "KurumSorgulamaTalep", targetNamespace = "http://akum.compugroup.com") String kurumKodu)

    @WebMethod
    @WebResult
    String goHonk(@WebParam(partName = "KurumSorgulamaTalep", name = "KurumSorgulamaTalep", targetNamespace = "http://akum.compugroup.com") String kurumKodu)
}