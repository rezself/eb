package com.eb.eb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

@SpringBootApplication
@RestController
public class EbApplication {

	@GetMapping("/chasis")
	public String get(){
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long start = System.nanoTime();
		Runtime runtime = Runtime.getRuntime();
		int cpus = runtime.availableProcessors();
		long mmax = runtime.maxMemory() / 1024 / 1024;
		long mtotal = runtime.totalMemory() / 1024 / 1024;
		long mfree = runtime.freeMemory() / 1024 / 1024;
		double elapseTime = (System.nanoTime() - start) / 1_000_000_000; /// *1.0e-9;
		File cDrive = new File("C:");
		String lineone = "CPUS:" + Integer.toString(cpus);
		String linetwo = "Memoria Maxima:" + Long.toString(mmax);
		String linethree = "Memoria total:" + Long.toString(mtotal);
		String linefour = "Memoria Libre:" + Long.toString(mfree);
		String linefive = "Tiempo sec:" + Double.toString(elapseTime);
		String linesix = String.format("Espacio total c: %.2f GB", (double) cDrive.getTotalSpace() / 1073741824);
		String lineseven = String.format("Espacio libre c: %.2f GB", (double) cDrive.getFreeSpace() / 1073741824);

		return "Datos de JVM \n" + lineone + linetwo + linethree + linefour + linefive + linesix + lineseven;

		//System.out.println("CPUS:" + Integer.toString(cpus));
		//System.out.println("Memoria Maxima:" + Long.toString(mmax));
		//System.out.println("Memoria total:" + Long.toString(mtotal));
		//System.out.println("Memoria Libre:" + Long.toString(mfree));
		// 1 s = 1,000 ms = 1,000,000 Âµs = 1,000,000,000 ns
		//System.out.println("Tiempo sec:" + Double.toString(elapseTime));
		//System.out.println(String.format("Espacio total c: %.2f GB", (double) cDrive.getTotalSpace() / 1073741824));
		//System.out.println(String.format("Espacio libre c: %.2f GB", (double) cDrive.getFreeSpace() / 1073741824));
	}

	public static void main(String[] args) {
		SpringApplication.run(EbApplication.class, args);
	}

}
