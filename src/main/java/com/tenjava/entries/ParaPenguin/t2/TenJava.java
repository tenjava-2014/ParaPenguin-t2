package com.tenjava.entries.ParaPenguin.t2;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class TenJava extends JavaPlugin {

	private static TenJava instance;
	private static File usersFile;

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		usersFile = new File(getDataFolder(), "users");
		if(!usersFile.exists() || !usersFile.isDirectory()) {
			boolean success = usersFile.mkdirs();
			if(!success) {
				getLogger().info("Could not load plugin because no Users folder could be created");
				setEnabled(false);
				return;
			}
		}
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static TenJava get() {
		return instance;
	}

	public static File getUsersFile() {
		return usersFile;
	}

}
