package SourcePackage;

class Lista {
	//variables
	Nodo inicio;
	//constructores
	Lista (int n) {
		this.inicio = new Nodo(n);
	}   
	Lista () {
		this.inicio = null;
	}

	//métodos
	void addNodo (int n) {
		if (inicio != null) {
			inicio.addFinal(new Nodo(n));			
		} else {
			inicio = new Nodo(n);
		}
	}
	public int getValues(int posicion) {
		Nodo temp = inicio;
        int contador = 0;

        while (temp != null) {
            if (contador == posicion) {
                return temp.x;
            }
            temp = temp.sig;
            contador++;
        }
		throw new IndexOutOfBoundsException("La posición está fuera de rango"); //excepción
	} 
	void print () {
		inicio.print();
	}

	

}
