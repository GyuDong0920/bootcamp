package tenco.com.test_01.ch01;

public class GenericPrinter3 <T extends Material>{

	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}
	@Override
	public String toString() {
		
		return material.toString();
	}  
	
	
}
