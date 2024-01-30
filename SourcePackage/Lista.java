package SourcePackage;

class Lista {
    //variables
    Nodo inicio;
    int[] valores; //almacenar los valores en un array
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
		int[] nuevoArray = new int[valores.length + 1]; 
		for (int i = 0; i < valores.length; i++) {
			nuevoArray[i] = valores[i]; //copiar los elementos del array 
		}
		nuevoArray[nuevoArray.length - 1] = n; 
		valores = nuevoArray;
	}

    public int getValues(int posicion) {
        if (posicion < 0 || posicion >= valores.length) {
            throw new IndexOutOfBoundsException("La posicón no existe"); 
        }
        return valores[posicion];
    } 
    
    void clearList () {
        Nodo temp = inicio;
        while (temp != null) {
            temp.remove();
            temp = temp.sig;
        }
        valores = new int[]{};
    }

    public int getSize() {
        return valores.length;
    }

    void print () {
        inicio.print();
    }
}
///Clase Nodo///
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
    void remove() {
        if (this.sig != null) {
            Nodo temp = this.sig;
            this.x = temp.x;
            this.sig = temp.sig;
        }
    }
    int value () {
        return x;
    }
    @Override
    public String toString() {
        return "nodo: " + x;
    }
}