package org.grails.cxf.artefact

import grails.core.ArtefactHandlerAdapter
import grails.core.GrailsClass


class EndpointArtefactHandler extends ArtefactHandlerAdapter {

    public static final String TYPE = "Endpoint"

    EndpointArtefactHandler() {
        super(TYPE, GrailsClass.class, DefaultGrailsEndpointClass.class, TYPE, false)
    }
}

