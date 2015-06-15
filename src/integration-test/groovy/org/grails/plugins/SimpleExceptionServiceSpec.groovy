package org.grails.plugins

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import wslite.soap.SOAPClient
import wslite.soap.SOAPResponse
import wslite.soap.SOAPVersion

@Integration
@Rollback
class SimpleExceptionServiceSpec extends GebSpec {

    SOAPClient client = new SOAPClient("http://localhost:${System.getProperty("server.port", "8080")}/services/simpleException")

    def "invoke the pass method"() {
        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            body {
                'test:pass' {}
            }
        }
        def methodResponse = response.body.passResponse.return

        then:
        200 == response.httpResponse.statusCode
        "true" == methodResponse.text()
    }

    def "invoke method that returns list of exception stacktrace"() {
        when:
        SOAPResponse response = client.send {
            envelopeAttributes "xmlns:test": 'http://test.cxf.grails.org/'
            body {
                'test:fail'{
                }
            }
        }
        def methodResponse = response.body.failResponse.exceptions

        then:
        200 == response.httpResponse.statusCode
        SOAPVersion.V1_1 == response.soapVersion
        methodResponse.size() > 0
        methodResponse.stackTrace.size() > 0

    }
}
