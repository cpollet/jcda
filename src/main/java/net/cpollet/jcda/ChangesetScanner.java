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

import net.sf.javaml.matrix.Matrix;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class ChangesetScanner {
	private final WatchedFilesHolder watchedFiles;
	private MatrixHolder matrixHolder;

	public ChangesetScanner(WatchedFilesHolder watchedFiles, MatrixHolder matrixHolder) {
		this.watchedFiles = watchedFiles;
		this.matrixHolder = matrixHolder;
	}

	public List<String> run(String file) {
		List<String> classes = new LinkedList<>();

		try {
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				while (fileReader.hasNext()) {
					handle(fileReader.next());
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

	private void handle(String changesetContent) {
		List<String> filesInChangeset = Arrays.asList(changesetContent.split(" "));

		for (int i = 0; i < filesInChangeset.size(); i++) {
			for (int j = i + 1; j < filesInChangeset.size(); j++) {
				String fileA = filesInChangeset.get(i);
				String fileB = filesInChangeset.get(j);

				if (watchedFiles.isWatched(fileA) && watchedFiles.isWatched(fileB)) {
					incrementChangeCount(fileA, fileB);
				}
			}
		}
	}

	private void incrementChangeCount(String fileA, String fileB) {
		int iA = watchedFiles.indexOf(fileA);
		int iB = watchedFiles.indexOf(fileB);

		matrixHolder.increment(iA, iB);
	}
}
