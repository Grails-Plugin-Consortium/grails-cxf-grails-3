package org.grails.cxf.test

import org.grails.cxf.test.soap.Customer
import org.grails.cxf.utils.EndpointType

import javax.jws.WebMethod
import javax.jws.WebService


class CustomerServiceWsdlEndpoint implements ICustomerServiceWsdlEndpoint {

    static expose = EndpointType.JAX_WS_WSDL
    static wsdl = 'org/grails/cxf/test/soap/CustomerService.wsdl'

    CustomerServiceEndpoint customerServiceEndpoint

    List<Customer> getCustomersByName(final String name) {
        customerServiceEndpoint.getCustomersByName(name)
    }
}

@WebService(name = 'CustomerServiceWsdlEndpoint',
        targetNamespace = 'http://test.cxf.grails.org/',
        serviceName = 'CustomerServiceWsdlEndpoint',
        portName = 'CustomerServiceWsdlPort')
interface ICustomerServiceWsdlEndpoint {
    @WebMethod
    List<Customer> getCustomersByName(String name)
}
