/*
 * Copyright 2014 Christophe Pollet
 *
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

package net.cpollet.jcda;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Christophe Pollet
 */
public class Cluster {
	private Set<String> elements;

	public Cluster(String element) {
		elements = new HashSet<>();
		elements.add(element);
	}

	public boolean contains(String element) {
		return elements.contains(element);
	}

	public boolean merge(Cluster other) {
		for (String otherElement : other.elements) {
			for (String element : elements) {
				if (otherElement.equals(element)) {
					elements.addAll(other.elements);
					return true;
				}
			}
		}

		return false;
	}

	public void add(String element) {
		if (!contains(element)) {
			elements.add(element);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Cluster:");

		for (String element : elements) {
			sb.append(" ").append(element);
		}

		return sb.toString();
	}
}
