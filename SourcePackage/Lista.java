package SourcePackage;


class Lista {
    //variables
    Nodo inicio;
    int[] valores; // almacenamos los valores en un array
    //constructores
    Lista (int n) {
        this.inicio = new Nodo(n);
        this.valores = new int[]{n};
    }   
    Lista () {
        this.inicio = null;
        this.valores = new int[]{};
    }

    //métodos
	void addNodo (int n) {
		if (inicio != null) {
			inicio.addFinal(new Nodo(n));   
		} else {
			inicio = new Nodo(n);
		}
		int[] nuevoArray = new int[valores.length + 1]; // creamos un nuevo array
		for (int i = 0; i < valores.length; i++) {
			nuevoArray[i] = valores[i]; // copiamos los elementos del array existente al nuevo array
		}
		nuevoArray[nuevoArray.length - 1] = n; // agregamos el nuevo valor al final del nuevo array
		valores = nuevoArray; // actualizamos la referencia al array
	}

    public int getValues(int posicion) {
        if (posicion < 0 || posicion >= valores.length) {
            throw new IndexOutOfBoundsException("La posicón no existe"); //excepción
        }
        return valores[posicion];
    } 
    boolean isHere(int n) {
        for (int i : valores) {
            if (i == n)
                return true;
        }
        return false;
    }
    public int getSize() {
        return valores.length;
    }

    void print () {
        inicio.print();
    }
}