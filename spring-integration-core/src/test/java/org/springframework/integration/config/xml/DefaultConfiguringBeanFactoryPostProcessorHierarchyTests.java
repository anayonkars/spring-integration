/*
 * Copyright 2002-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.config.xml;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.context.IntegrationContextUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mark Fisher
 * @author Artem Bilan
 */
public class DefaultConfiguringBeanFactoryPostProcessorHierarchyTests {

	@Test
	public void verifySinglePostProcessor() {
		ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext(
				"hierarchyTests-parent.xml", this.getClass());
		parent.refresh();
		GenericApplicationContext child = new GenericApplicationContext();
		child.setParent(parent);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions(new ClassPathResource("hierarchyTests-child.xml", this.getClass()));
		child.refresh();
		assertThat(child.getBean(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME))
				.isSameAs(parent.getBean(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME));
		assertThat(child.getBean(IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME))
				.isSameAs(parent.getBean(IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME));
		assertThat(child.getBean(IntegrationContextUtils.TASK_SCHEDULER_BEAN_NAME))
				.isSameAs(parent.getBean(IntegrationContextUtils.TASK_SCHEDULER_BEAN_NAME));

		child.close();
		parent.close();
	}

}
