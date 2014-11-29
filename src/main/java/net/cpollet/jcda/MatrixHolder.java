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

import net.sf.javaml.clustering.mcl.SparseMatrix;
import net.sf.javaml.clustering.mcl.SparseVector;

/**
 * @author Christophe Pollet
 */
public class MatrixHolder implements ChangesetListener {
	private final SparseMatrix matrix;

	public MatrixHolder(int x, int y) {
		matrix = new SparseMatrix(x, y);
		for (int i = 0; i < x; i++) {
			matrix.set(i, new SparseVector(y));
		}
	}

	@Override
	public void increment(int x, int y) {
		double newValue = matrix.get(x, y) + 1;

		matrix.add(x, y, 1);
		matrix.add(y, x, 1);
	}

	public SparseMatrix getMatrix() {
		return matrix;
	}
}
