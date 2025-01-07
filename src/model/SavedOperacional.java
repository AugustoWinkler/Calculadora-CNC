package model;

import java.util.ArrayList;
import java.util.List;



public class SavedOperacional {
	private static SavedOperacional instance;
	private ArrayList<Operacional> operacional = new ArrayList<>();

	
	
	public SavedOperacional() {
		operacional = new ArrayList<>();
		Operacional novoOperacional =  new Operacional("12x24", 50 , 50 ,50 ,50);
		operacional.add(novoOperacional);

	}

	public static SavedOperacional getInstance() {
		if (instance == null) {
			instance = new SavedOperacional();
		}
		return instance;
	}

	public void addOperacional(Operacional operacional) {
		this.operacional.add(operacional);
	}

	public List<Operacional> getOperacional() {
		return new ArrayList<>(operacional);
	}
}
