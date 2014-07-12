package com.tenjava.entries.ParaPenguin.t2.energy;

public class EnergyStorage {

	private int energy;
	private int maxEnergy;

	public EnergyStorage(int energy, int maxEnergy) {
		this.energy = energy;
		this.maxEnergy = maxEnergy;
	}

	public int getEnergy() {
		return energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public double getPercentage() {
		return (double) maxEnergy / energy * 100;
	}

}
