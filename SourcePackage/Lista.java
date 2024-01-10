package SourcePackage;

class Lista {
	//variables
	Nodo inicio;
	//constructores
	Lista (Nodo n) {
		this.inicio = n;
	}   
	Lista () {
		this.inicio = null;
	}

	//métodos
	void addNodo (Nodo n) {
		if (inicio != null) {
			inicio.addFinal(n);			
		} else {
			inicio = n;
		}
	}
	void print () {
		inicio.print();
	}

}
