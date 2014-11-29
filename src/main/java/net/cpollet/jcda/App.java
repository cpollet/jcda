package net.cpollet.jcda;

import net.sf.javaml.clustering.mcl.MarkovClustering;
import net.sf.javaml.clustering.mcl.SparseMatrix;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws IOException {
		ClassesHolder classesHolder = new ClassesHolder(args[0]);
		MatrixHolder matrixHolder = new MatrixHolder(classesHolder.size(), classesHolder.size());

		new ChangesetScanner(args[1], classesHolder, Arrays.asList((ChangesetListener) matrixHolder));

		SparseMatrix sm = matrixHolder.getMatrix();

		MarkovClustering mc = new MarkovClustering();

		System.out.println(classesHolder);
		System.out.println(sm.toStringDense());
		System.out.println();
		SparseMatrix sm2 = mc.run(sm, 0.00001, 2, 2, 0.01);
		System.out.println(sm2);
		System.out.println();

		List<Cluster> clusters = new LinkedList<>();

		for (int i = 0; i < classesHolder.size(); i++) {
			double[] row = sm2.get(i).getDense();

			Cluster cluster = new Cluster(classesHolder.getClassAt(i));

			for (int j = 0; j < row.length; j++) {
				if (row[j] > 0) {
					cluster.add(classesHolder.getClassAt(j));
				}
			}
			// System.out.println(cluster);
			merge(clusters, cluster);
		}

		for (Cluster cluster : clusters) {
			System.out.println(cluster);
		}
	}

	private static void merge(List<Cluster> clusters, Cluster cluster) {
		for (Cluster cluster1 : clusters) {
			if (cluster1.merge(cluster)) {
				return;
			}
		}

		clusters.add(cluster);
	}


}
