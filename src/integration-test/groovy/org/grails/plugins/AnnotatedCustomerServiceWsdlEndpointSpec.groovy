package org.grails.plugins

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.grails.cxf.test.soap.CustomerType
import spock.lang.Ignore
import wslite.soap.SOAPClient
import wslite.soap.SOAPResponse
import wslite.soap.SOAPVersion

@Integration
class AnnotatedCustomerServiceWsdlEndpointSpec extends GebSpec {

    void setup() {
        browser.config.reportsDir = new File('target/geb_reports')
    }

    SOAPClient client = new SOAPClient("http://localhost:${System.getProperty("server.port", "8080")}/services/annotatedCustomerServiceWsdl")

    def "invoke getCustomersByName on annotated service with a wsdl"() {
        given:
        def customerName = 'Frank'
        def customerType = CustomerType.PRIVATE.name()

        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": "http://test.cxf.grails.org/"
            body {
                'test:getCustomersByName' {
                    name customerName
                }
            }
        }
        def methodResponse = response.body.getCustomersByNameResponse.return

        then:
        response.httpResponse.statusCode == 200
        response.soapVersion == SOAPVersion.V1_1
        customerName == methodResponse.name.text()
        customerType == methodResponse.type.text()
    }
}
