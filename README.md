# PotionCraft Clone - Tienda de Pociones

## Descripción
Este es un proyecto desarrollado en **Java puro** siguiendo los principios de la **Programación Orientada a Objetos (POO)**. Se trata de un clon simplificado del juego *PotionCraft*, centrado en la compra y venta de pociones, así como la administración del inventario y el historial de vendedores.

## Características
- **Compra de pociones** a diferentes comerciantes.
- **Compra de plantas** para elaboração o reventa.
- **Venta de pociones y plantas** para obtener ganancias.
- **Sistema de inventario** para gestionar los productos adquiridos.
- **Historial de vendedores** para consultar interacciones pasadas.
- **Guardado de estado**, permitiendo retomar la sesión en otro momento.

## Tecnologías Utilizadas
- **Java** (POO)
- **Serialización de objetos** para el guardado del estado
- **Estructuras de datos** como listas y mapas para el manejo del inventario y vendedores

## Instalación y Ejecución
1. Clona este repositorio:
   ```bash
   git clone https://github.com/zkj0n/Potioncraft.git
   ```
2. Compila el proyecto:
   ```bash
   javac -d bin src/*.java
   ```
3. Ejecuta el juego:
   ```bash
   java -cp bin Main
   ```

## Uso
- **Comprar**: Selecciona un comerciante y adquiere pociones o ingredientes.
- **Vender**: Escoge objetos de tu inventario y ofrécelos a los comerciantes.
- **Ver Inventario**: Consulta los productos que posees.
- **Historial de Vendedores**: Revisa con qué comerciantes has interactuado y qué transacciones realizaste.
- **Guardar y Cargar**: Guarda el progreso y retómalo cuando quieras.

## Contribución
Si deseas mejorar este proyecto:
1. Realiza un fork del repositorio.
2. Crea una rama con tu mejora:
   ```bash
   git checkout -b mi-mejora
   ```
3. Realiza los cambios y haz commit:
   ```bash
   git commit -m "Mejora en la funcionalidad X"
   ```
4. Sube los cambios:
   ```bash
   git push origin mi-mejora
   ```
5. Crea un Pull Request en GitHub.

## Licencia
Este proyecto se distribuye bajo la licencia MIT. Ver [LICENSE](LICENSE) para más detalles.

## Autor
Desarrollado por **[zkjon]**.

