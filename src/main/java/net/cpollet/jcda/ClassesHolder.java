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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class ClassesHolder {
	List<String> classes;

	public ClassesHolder(String file) {
		classes = loadClasses(file);
	}

	public boolean isWatched(String className) {
		return classes.contains(className);
	}

	public int getIndexOf(String className) {
		return classes.indexOf(className);
	}

	public String getClassAt(int index) {
		return classes.get(index);
	}

	public int size() {
		return classes.size();
	}

	private List<String> loadClasses(String file) {
		List<String> classes = new LinkedList<>();

		try {
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				while (fileReader.hasNext()) {
					classes.add(fileReader.next());
				}
			}
			finally {
				if (fileReader != null) {
					fileReader.close();
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		return classes;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < classes.size(); i++) {
			sb.append(i).append(" ").append(classes.get(i)).append("\n");
		}

		return sb.toString();
	}
}
