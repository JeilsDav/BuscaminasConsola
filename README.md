# Buscaminas

## Descripción del Proyecto
Este proyecto es una implementación del clásico juego **Buscaminas**, desarrollado como parte de un trabajo universitario en equipo. El juego se ejecuta en la consola y permite al usuario interactuar mediante la introducción de coordenadas para descubrir casillas o marcarlas con una bandera.

El desarrollo se realizó utilizando **Java**, aplicando principios de programación orientada a objetos y buenas prácticas de diseño.

## Estructura del Proyecto
El proyecto está organizado en múltiples paquetes y clases, cada uno con responsabilidades específicas:

### **Package: `modelo`**
- **Clase `Tablero`**: Administra la lógica del tablero del juego, como la generación y manipulación de casillas.
- **Clase `Casilla`**: Representa las casillas del tablero, incluyendo su estado (descubierta, marcada, etc.).
- **Clase `Jugador`**: Gestiona las estadísticas del jugador (partidas ganadas, perdidas y casillas descubiertas).

### **Package: `vista`**
- **Clase `ConsolaVista`**: Controla la interacción del usuario con el programa mediante la consola.

### **Package: `controlador`**
- **Clase `JuegoControlador`**: Maneja la lógica del juego, conectando la vista y el modelo.

### **Package: `utilidades`**
- **Clase `ArchivoUtils`**: Gestiona la lectura y escritura de archivos para persistir datos.
- **Clase `ValidacionUtils`**: Contiene métodos de validación para entradas del usuario y otros elementos.

### **Package: `excepciones`**
- **Clase `CasillaYaDescubiertaException`**: Excepción personalizada para evitar descubrir casillas ya descubiertas.
- **Clase `InputInvalidoException`**: Excepción personalizada para manejar entradas no válidas.

### **Package: `pruebas`**
- **Clase `TableroTest`**: Pruebas unitarias para la clase `Tablero`.
- **Clase `CasillaTest`**: Pruebas unitarias para la clase `Casilla`.
- **Clase `JuegoControladorTest`**: Pruebas unitarias para la clase `JuegoControlador`.
- **Clase `ArchivoUtilsTest`**: Pruebas unitarias para la clase `ArchivoUtils`.
- **Clase `ExcepcionesTest`**: Pruebas unitarias para las clases de excepciones.

### **Package: `principal`**
- **Clase `Principal`**: Punto de entrada principal del programa. Permite ejecutar el juego desde la consola.

## Ejecución del Proyecto
El juego debe ejecutarse desde la clase `Principal`. El usuario interactúa con el programa introduciendo coordenadas para descubrir casillas o marcarlas con una bandera.




## Orgnizacion del proyectio en equipo
El desarrollo del programa se subio mediante ramas:
### Estructura de las Ramas
- **`david`**, **`david_2`**, **`david_3`**: Ramas en la que David subio sus actualizaciones. 
-  **`daniel`**, **`daniel_2`**: Ramas en la que Daniel subio sus actualizaciones. 
-  **`joe`**, **`joe_2`**: Ramas en la que Joe subio sus actualizaciones. 
-  **`kleber`**, **`kleber_2`**: Ramas en la que Kleber subio sus actualizaciones.

### Rama Final
Una vez que todas las contribuciones estuvieron listas y probadas, se integraron en la rama final:  
**`Buscaminas_Terminado`**
