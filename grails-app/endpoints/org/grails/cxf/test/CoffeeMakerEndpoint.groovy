package org.grails.cxf.test

import org.grails.cxf.adapter.GrailsCxfMapAdapter
import org.grails.cxf.test.soap.simple.CoffeeType
import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.util.concurrent.atomic.AtomicBoolean

/**
 * An example of a Simple Cxf SOAP Service.
 * <p>
 * Simple Cxf frontends are generally not recommended since they use reflection to determine the service
 * definition. More details can be found in the Cxf documentation.
 *
 * @see http://cxf.apache.org/docs/simple-frontend.html
 */
@GrailsCxfEndpoint
class CoffeeMakerEndpoint {

    AtomicBoolean makerOn = new AtomicBoolean(false)

    @WebMethod(operationName = 'turnOn')
    Boolean turnOn() {
        return !makerOn.getAndSet(true)
    }

    @WebMethod(operationName = 'turnOff')
    Boolean turnOff() {
        return !makerOn.getAndSet(false)
    }

    @WebMethod(operationName = 'makeCoffee')
    String makeCoffee(@WebParam(name = 'coffeeType') CoffeeType coffeeType) {
        assert makerOn.get(), " The Coffee Maker is not turned on."
        return "Making coffee with $coffeeType beans."
    }

    @WebMethod
    CoffeeType findCoffeeTypeByName(@WebParam(name = 'name') String name) {
        return CoffeeType.values().find() { it.name() == name }
    }

    @WebMethod
    List<CoffeeType> listCoffeeTypes() {
        return CoffeeType.values()
    }

    @WebMethod(operationName = 'mapCoffeeLocations')
    @XmlJavaTypeAdapter(GrailsCxfMapAdapter.class)
    @WebResult(name = 'entires')
    Map<String, CoffeeType> mapCoffeeLocations() {
        return ['Colombia': CoffeeType.Colombian, 'Ethiopia': CoffeeType.Ethiopian]
    }

}
