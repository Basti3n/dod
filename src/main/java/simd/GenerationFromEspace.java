package grx.dod.demo.simd;

import java.util.List;

public class GenerationFromEspace {

	Espace espace;
	
	public GenerationFromEspace(Espace espace) {
		this.espace = espace;
	}
	
	public List<String> output() {
		return espace.formes;
	}

}
