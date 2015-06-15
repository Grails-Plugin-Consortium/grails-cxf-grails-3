package org.grails.cxf.test

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.grails.cxf.test.soap.Customer
import org.grails.cxf.test.soap.CustomerService
import org.grails.cxf.test.soap.CustomerType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.xml.datatype.DatatypeConstants
import org.grails.cxf.utils.EndpointType

import javax.xml.ws.RequestWrapper
import javax.xml.ws.ResponseWrapper

@GrailsCxfEndpoint
class CustomerServiceEndpoint {

    static List<Customer> CUSTOMERS = [
            new Customer(name: 'Frank', address: ['1234 Awesomeness St.', 'Chochokacho, Toast'], numOrders: 5,
                         revenue: 630.00d, test: new BigDecimal("50.123"), type: CustomerType.PRIVATE,
                         birthDate: XMLGregorianCalendarImpl.createDate(2012, 12, 07, DatatypeConstants.FIELD_UNDEFINED)),
            new Customer(name: 'Super Duper', address: ['999 Numbers Ave.', 'Double, Toast'], numOrders: 1,
                         revenue: 10.00d, test: new BigDecimal("12"), type: CustomerType.BUSINESS,
                         birthDate: XMLGregorianCalendarImpl.createDate(2012, 12, 07, DatatypeConstants.FIELD_UNDEFINED)),

    ]

    @WebMethod
    List<Customer> getCustomersByName(@WebParam final String name) {
        CUSTOMERS.findAll { it.name == name }
    }

}