/*
 * Copyright 2016-present the original author or authors.
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

package org.springframework.integration.handler;

import java.util.List;

import org.springframework.messaging.MessageHandler;

/**
 * Classes implementing this interface delegate to a list of handlers.
 *
 * @author Gary Russell
 * @since 4.3
 *
 */
public interface CompositeMessageHandler extends MessageHandler {

	/**
	 * Return an unmodifiable list of handlers.
	 * @return the handlers.
	 */
	List<MessageHandler> getHandlers();

}
