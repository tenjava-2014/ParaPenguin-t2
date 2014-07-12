package com.tenjava.entries.ParaPenguin.t2.player;

import com.tenjava.entries.ParaPenguin.t2.TenJava;
import com.tenjava.entries.ParaPenguin.t2.energy.EnergyStorage;
import com.tenjava.entries.ParaPenguin.t2.util.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {

	private static List<PlayerData> players = new ArrayList<>();

	private FileConfiguration config;
	private Player player;
	private EnergyStorage energy;

	private PlayerData(FileConfiguration config, Player player, EnergyStorage energy) {
		this.config = config;
		this.player = player;
		this.energy = energy;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public Player getPlayer() {
		return player;
	}

	public EnergyStorage getEnergy() {
		return energy;
	}

	public static PlayerData getData(Player player) {
		UUID uuid = player.getUniqueId();
		for(PlayerData data : players) {
			if(data.getPlayer().getUniqueId().equals(uuid)) {
				return data;
			}
		}

		return null;
	}

	public static PlayerData create(Player player, boolean force) {
		if(!force) {
			PlayerData data = getData(player);
			if(data != null) {
				return data;
			}
		}

		File file = new File(TenJava.getUsersFile(), player.getUniqueId().toString() + ".yml");
		if(!file.exists()) {
			try {
				boolean success = file.createNewFile();
				if(!success) {
					throw new IOException("Could not create file \"" + file.getName() + "\"");
				}
			} catch(IOException ex) {
				Log.log(ex);
				return null;
			}
		}

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		int value = config.getInt("energy.value", 100);
		int max = config.getInt("energy.max", 100);
		EnergyStorage energy = new EnergyStorage(value, max);

		return new PlayerData(config, player, energy);
	}

	public static PlayerData load(Player player) {
		PlayerData data = getData(player);
		if(data != null) {
			return data;
		}

		data = create(player, true);
		if(data != null) {
			players.add(data);
		}

		return data;
	}

}
