package org.grails.plugins

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import wslite.soap.SOAPClient
import wslite.soap.SOAPResponse
import wslite.soap.SOAPVersion

@Integration
@Rollback
class AnnotatedSimpleEndpointSpec extends GebSpec {

    SOAPClient client = new SOAPClient("http://localhost:${System.getProperty("server.port", "8080")}/services/v2/renamed")

    def "invoke a method on the service using soap 1.2"() {
        given:
        def legacyParam = 'hello'

        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            version SOAPVersion.V1_2
            body {
                'test:simpleMethod' {
                    'test:param'(legacyParam)
                }
            }
        }
        def methodResponse = response.body.simpleMethodResponse.return

        then:
        200 == response.httpResponse.statusCode
        SOAPVersion.V1_2 == response.soapVersion
        legacyParam == methodResponse.text()
    }

    def "invoking a soap 1.2 service with 1.1 should be backwards compatible"() {
        given:
        def legacyParam = 'hello'

        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            version SOAPVersion.V1_1
            body {
                'test:simpleMethod'{
                    'test:param'(legacyParam)
                }
            }
        }
        def methodResponse = response.body.simpleMethodResponse.return

        then:
        200 == response.httpResponse.statusCode
        SOAPVersion.V1_1 == response.soapVersion
        legacyParam == methodResponse.text()
    }
}
