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

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Christophe Pollet
 */
public class FileReader {
	private BufferedReader br;
	private String next;

	public FileReader(String file) throws IOException {
		br = new BufferedReader(new java.io.FileReader(file));
		next();
	}

	public String next() throws IOException {
		String retVal = next;

		next = br.readLine();

		return retVal;
	}

	public boolean hasNext() {
		return next != null;
	}

	public void close() throws IOException {
		br.close();
	}
}
