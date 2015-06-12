package org.grails.cxf.test.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://customerservice.example.com/", name = "CustomerService")
@XmlSeeAlso({ObjectFactory.class})
public interface CustomerService {

	@WebMethod
	@WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getCustomersByName2", targetNamespace = "http://customerservice.example.com/", className = "org.grails.cxf.soap.GetCustomersByName")
    @ResponseWrapper(localName = "getCustomersByNameResponse2", targetNamespace = "http://customerservice.example.com/", className = "org.grails.cxf.soap.GetCustomersByNameResponse")
    java.util.List<org.grails.cxf.test.soap.Customer> getCustomersByName(
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    ) throws NoSuchCustomerException;
}
