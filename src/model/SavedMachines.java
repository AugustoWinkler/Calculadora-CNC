package model;

import java.util.ArrayList;
import java.util.List;

public class SavedMachines {
	private static SavedMachines instance;
	private ArrayList<Machine> machines = new ArrayList<>();

	
	
	public SavedMachines() {
		machines = new ArrayList<>();
		Machine mDefault = new Machine("Máquina Padrão",2500,10,0,1500,10000);
		machines.add(mDefault);
		
	}

	public static SavedMachines getInstance() {
		if (instance == null) {
			instance = new SavedMachines();
		}
		return instance;
	}

	public void addMachine(Machine machine) {
		machines.add(machine);
	}

	public List<Machine> getMachines() {
		return new ArrayList<>(machines);
	}
}
