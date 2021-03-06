/*
 * Copyright (c) 2015 Hewlett Packard Enterprise Development Company, L.P. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.nic.graph.impl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.opendaylight.nic.mapping.api.IntentMappingService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.powermock.api.mockito.PowerMockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GraphMapImplTest {
    protected IntentMappingService intentMappingService;

    private GraphMapImpl service;

    @Before
    public void setUp() throws Exception {
        this.intentMappingService = mock(IntentMappingService.class);
        service = new GraphMapImpl(intentMappingService);
    }

    //create a simple tree and test that each one is initialized properly with the right parent/children pairs.
    @Test
    public final void testCreateGraph() throws Exception {
        boolean actualResult, expectedResult;
        service = spy(service);
        PowerMockito.mockStatic(FrameworkUtil.class);
        BundleContext mockBundleContext = mock(BundleContext.class);
        ServiceRegistration<GraphMapImpl> intentServiceMock = mock(ServiceRegistration.class);

        when(mockBundleContext.registerService(GraphMapImpl.class, service, null))
                .thenReturn(intentServiceMock);
        expectedResult = true;
        actualResult = service.addLabelChild("apps", "Tnt", "app1");
        assertEquals(expectedResult, actualResult);
        actualResult = service.addLabelChildren("Tnt", "pga_label_tree", new String[]{"Dpts", "apps"}); //make null first
        assertEquals(expectedResult, actualResult);
    }
}
