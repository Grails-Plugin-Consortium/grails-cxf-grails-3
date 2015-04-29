package org.grails.cxf.utils

import grails.core.GrailsApplication
import grails.util.Holders
import org.grails.cxf.artefact.EndpointArtefactHandler

class GrailsCxfUtils {

    private GrailsCxfUtils() {
        // Class contains static methods only
    }

    static Integer getLoadOnStartup() {
        Holders?.config?.cxf?.servlet?.loadOnStartup as Integer
    }

    static Map<String, String> getServletsMappings() {
        Object mappings = Holders?.config?.cxf?.servlets
        assert mappings, 'There must be at least one configured servlet.'
        (mappings ?: [:]) as Map<String, String>
    }

    static String getDefaultServletName() {
        Object defaultName = Holders?.config?.cxf?.servlet?.defaultServlet
        def servletMaps = getServletsMappings()

        if(defaultName instanceof String && !defaultName.isEmpty() && servletMaps.containsKey(defaultName)) {
            return defaultName
        }

        new TreeMap<String, String>(servletMaps).firstKey()
    }

    static Boolean getDefaultSoap12Binding() {
        Holders?.config?.cxf?.endpoint?.soap12Binding as Boolean
    }

    /**
     * Endpoints in /endpoints dir and Services in /services dir
     * @return list of artefacts to wire up
     */
    static List configuredArtefacts() {
        [EndpointArtefactHandler]
    }

    static String flexibleEnumName(String name) {
        name.replaceAll(/(\s|\-)/, '_').toUpperCase()
    }
}
