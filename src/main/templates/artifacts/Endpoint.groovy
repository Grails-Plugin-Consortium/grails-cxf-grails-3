@artifact.package@

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod

@GrailsCxfEndpoint
class @artifact.name@ {

    @WebMethod
    String serviceMethod(String s) {
        return s
    }
}
