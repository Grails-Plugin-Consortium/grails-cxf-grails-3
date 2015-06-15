package org.grails.cxf.test

import org.grails.cxf.test.soap.Customer
import org.grails.cxf.utils.EndpointType
import org.grails.cxf.utils.GrailsCxfEndpoint
import org.springframework.beans.factory.annotation.Autowired

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService


//@WebService(name = 'CustomerServiceWsdlEndpoint',
//        targetNamespace = 'http://test.cxf.grails.org/',
//        serviceName = 'CustomerServiceWsdlEndpoint',
//        portName = 'CustomerServiceWsdlPort')
@GrailsCxfEndpoint(expose = EndpointType.JAX_WS_WSDL, wsdl = 'org/grails/cxf/test/soap/CustomerService.wsdl')
class CustomerServiceWsdlEndpoint {

    @Autowired
    CustomerServiceEndpoint customerServiceEndpoint

    @WebMethod
    List<Customer> getCustomersByName(@WebParam(name='name') final String name) {
        customerServiceEndpoint.getCustomersByName(name)
    }
}
