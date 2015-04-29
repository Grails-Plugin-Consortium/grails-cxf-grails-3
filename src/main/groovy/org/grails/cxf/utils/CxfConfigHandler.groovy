package org.grails.cxf.utils

import grails.core.GrailsApplication
import grails.util.Holders
import groovy.transform.Synchronized
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

/**
 * Handles the Plugins Configuration
 */
class CxfConfigHandler {

    private static final Log log = LogFactory.getLog(CxfConfigHandler)

    private ConfigObject config

    /**
     * @return The groovy ConfigObject representation of this plugins configuration.
     */
    @Synchronized
    ConfigObject getCxfConfig(GrailsApplication grailsApplication = Holders.grailsApplication) {
        log.info "loading config"

        if (config == null) {
            config = grailsApplication.config.cxf
        }

        log.info "loaded config ${config.flatten()}"

        config
    }

    // Below lies the Singleton implementation
    // In future updates use the @Singleton transformation.

    private static volatile CxfConfigHandler instance

    private CxfConfigHandler() {
        // Singleton
        // Upgrading to Groovy 1.8 will allow us to use the @Singleton AST Transformation
    }

    static CxfConfigHandler getInstance() {
        if (instance) {
            return instance
        }

        synchronized (CxfConfigHandler) {
            if (instance) {
                return instance
            }

            instance = new CxfConfigHandler()
            return instance
        }
    }
}