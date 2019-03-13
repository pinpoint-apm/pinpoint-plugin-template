/**
 * Copyright 2016 NAVER Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.plugin.template;

import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyProvider;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifierHolder;
import com.navercorp.pinpoint.test.plugin.PinpointAgent;
import com.navercorp.pinpoint.test.plugin.PinpointPluginTestSuite;

@RunWith(PinpointPluginTestSuite.class)
@PinpointAgent(TestConstants.AGENT_PATH)
public class MyPluginIT {

    @Test
    public void test() {
        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache();
        verifier.verifyTraceCount(0);
    }

    @Test
    public void verifyTraceMeta() {
        // see META-INF/pinpoint/type-provider.yml
        final ServiceType serviceType = ServiceTypeProvider.getByCode(999);
        Assert.assertNotNull(serviceType);
        Assert.assertSame(serviceType, ServiceTypeProvider.getByName("NAME"));

        final AnnotationKey annotationKey = AnnotationKeyProvider.getByCode(9999);
        Assert.assertNotNull(annotationKey);
    }
}
