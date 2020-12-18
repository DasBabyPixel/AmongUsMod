package de.pixel.amongus.client.version;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class VersionChecker implements Runnable {

	private static Thread thread = null;

	private static VersionChecker instance = new VersionChecker();

	private VersionChecker() {
	}

	@Override
	public void run() {
		try {
			GitHub github = GitHub.connectAnonymously();
			GHUser user = github.getUser("hub4j");
			GHRepository repo = user.getRepository("github-api");
			System.out.println(repo.getDescription());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		thread.interrupt();
		thread = null;
	}

	public static void awaitChecker() {
		while (isRunning()) {
			try {
				thread.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean isRunning() {
		return thread != null && !thread.isInterrupted();
	}

	public static boolean startCheck() {
		if (!isRunning()) {
			thread = new Thread(instance);
			thread.setName("AmongUs-VersionChecker");
			thread.setDaemon(true);
			thread.start();
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		startCheck();
		awaitChecker();
	}
}
