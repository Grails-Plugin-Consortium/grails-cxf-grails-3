package org.grails.cxf.test

import org.grails.cxf.test.soap.Customer
import org.grails.cxf.utils.EndpointType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService


//@WebService(name = 'CustomerServiceWsdlEndpoint',
//        targetNamespace = 'http://test.cxf.grails.org/',
//        serviceName = 'CustomerServiceWsdlEndpoint',
//        portName = 'CustomerServiceWsdlPort')
//@GrailsCxfEndpoint(wsdl = 'org/grails/cxf/test/soap/CustomerService.wsdl', expose = EndpointType.JAX_WS_WSDL)
class AnnotatedCustomerServiceWsdlEndpoint {

    CustomerServiceEndpoint customerServiceEndpoint

//    @WebMethod
    List<Customer> getCustomersByName2(@WebParam final String name)
    {
        customerServiceEndpoint.getCustomersByName(name)
    }
}