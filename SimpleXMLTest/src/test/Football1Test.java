package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TwoPathCycleStrategy;

import foo.Football;

public class Football1Test {
	public static void main(String[] args) throws IOException {
		testFile("Foo1.xml");
		testFile("Foo2.xml");
	}

	private static void testFile(String filename) throws FileNotFoundException, IOException {
		File inFile = new File(filename);
		File outFile = new File(filename + "_out");
		outFile.delete();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
		Strategy strategy = new TwoPathCycleStrategy("MID", "MREF");
//		Strategy strategy = new CycleStrategy("MID", "MREF");
		
		Serializer serializer = new Persister(strategy);
		try {
			System.out.println("1");
			Football f = serializer.read(Football.class, in);
			in.close();
			in = new BufferedInputStream(new FileInputStream(inFile));
			f = serializer.read(Football.class, in);
			System.out.println("2");
			serializer.write(f, out);
			System.out.println("3");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}
}
