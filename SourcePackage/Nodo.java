package SourcePackage;

class Nodo {
	//variables
	Nodo sig;
	int x;
	//constructores
	Nodo (int x) {
		this.x = x;
		this.sig = null;
	}
	Nodo (int x, Nodo sig) {
		this.x = x;
		this.sig = sig;
	}
	//métodos
	void addFinal(Nodo s) {
		if(this.sig == null) {
			this.sig = s;
		} else {
			this.sig.addFinal(s);
		}
	}
	void print () {
		System.out.print(this);
		if (this.sig != null)
			sig.print();
	}

	int value () {
		return x;
	}
	@Override
	public String toString() {
		return "nodo: " + x;
	}
}