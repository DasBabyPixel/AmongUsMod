package de.pixel.amongus.client.version;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

public class VersionChecker implements Runnable {

	private static Thread thread = null;

	private static VersionChecker instance = new VersionChecker();

	private VersionChecker() {
	}

	@Override
	public void run() {
		try {
			GitHub github = GitHub.connectAnonymously();
			GHRepository repo = github.getRepositoryById("322619834");
			PagedIterable<GHRelease> releases = repo.listReleases();
			System.out.println(releases.toList());
			GHRelease release = repo.getLatestRelease();
			System.out.println(release);
			List<GHAsset> assets = release.getAssets();
			System.out.println(assets + "");
			System.out.println(assets.get(0).getName());
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
		return thread != null && !thread.isInterrupted() && thread.isAlive();
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
		System.out.println(1);
		startCheck();
		awaitChecker();
		System.out.println(2);
	}
}
