package model;

import java.util.ArrayList;
import java.util.List;



public class SavedMaterials {
	private static SavedMaterials instance;
	private ArrayList<Material> materials = new ArrayList<>();

	
	
	public SavedMaterials() {
		materials = new ArrayList<>();
		Material novoMaterial =  new Material("Teste", 50);
		materials.add(novoMaterial);

	}

	public static SavedMaterials getInstance() {
		if (instance == null) {
			instance = new SavedMaterials();
		}
		return instance;
	}

	public void addMaterial(Material material) {
		materials.add(material);
	}

	public List<Material> getMaterial() {
		return new ArrayList<>(materials);
	}
}
