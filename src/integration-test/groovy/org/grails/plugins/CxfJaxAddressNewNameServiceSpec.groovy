package org.grails.plugins

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Unroll
import wslite.soap.SOAPClient
import wslite.soap.SOAPFaultException
import wslite.soap.SOAPResponse
import wslite.soap.SOAPVersion


class CxfJaxAddressNewNameServiceSpec extends GebSpec {

    SOAPClient client = new SOAPClient("http://localhost:${System.getProperty("server.port", "8080")}/services/override/v1/ninjas")

    @Unroll
    def "invoke the exposed method on the legacy cxf jax service param=#legacyParam"() {
        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            body {
                'test:legacyMethod' {
                    param(legacyParam)
                }
            }
        }
        def methodResponse = response.body.legacyMethodResponse.legacyResult

        then:
        200 == response.httpResponse.statusCode
        SOAPVersion.V1_1 == response.soapVersion
        "legacy ${legacyParam}" == methodResponse.text()

        where:
        legacyParam << ['hello', 'foo', 'world']
    }

    def "invoke the hidden method on the legacy cxf jax service"() {
        when:
        client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            body {
                'test:hiddenMethod' {
                    param('hello')
                }
            }
        }

        then:
        thrown(SOAPFaultException)
    }
}
