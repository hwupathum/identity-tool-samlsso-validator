/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.tools.saml.validator.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.core.SAMLSSOServiceProviderManager;

/**
 * Service component class for the SAML Validator.
 */
@Component(
        name = "identity.tools.saml.validator",
        immediate = true)
public class SAMLValidatorServiceComponent {

    private static final Log log = LogFactory.getLog(SAMLValidatorServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("SAMLValidatorServiceComponent is activated.");
        }
    }

    @Deactivate
    protected void deactivate() {

        if (log.isDebugEnabled()) {
            log.debug("SAMLValidatorServiceComponent is deactivated.");
        }
    }

    /**
     * This method is used to set SAMLSSOServiceProviderManager instance
     *
     * @param samlSSOServiceProviderManager SAMLSSOServiceProviderManager instance
     */
    @Reference(
            name = "saml.sso.service.provider.manager",
            service = SAMLSSOServiceProviderManager.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetSAMLSSOServiceProviderManager")
    protected void setSAMLSSOServiceProviderManager(SAMLSSOServiceProviderManager samlSSOServiceProviderManager) {

        SAMLValidatorServiceComponentHolder.getInstance().setSAMLSSOServiceProviderManager(samlSSOServiceProviderManager);
        if (log.isDebugEnabled()) {
            log.debug("SAMLSSOServiceProviderManager set in to bundle");
        }
    }

    /**
     * This method is used to un-set SAMLSSOServiceProviderManager instance
     *
     * @param samlSSOServiceProviderManager SAMLSSOServiceProviderManager instance
     */
    protected void unsetSAMLSSOServiceProviderManager(SAMLSSOServiceProviderManager samlSSOServiceProviderManager) {

        SAMLValidatorServiceComponentHolder.getInstance().setSAMLSSOServiceProviderManager(null);
        if (log.isDebugEnabled()) {
            log.debug("SAMLSSOServiceProviderManager unset in to bundle");
        }
    }

}
