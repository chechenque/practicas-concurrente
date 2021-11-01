[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6130287&assignment_repo_type=AssignmentRepo)
<h1 style="text-align: center"> Computación Concurrente - Práctica 02 </h1>

<h2 style="text-align: center"> Coordinación entre hilos </h2>

<h3 style="text-align: center"> Fecha de límite entrega: 04 de Noviembre de 2021 a las 23:59 </h3>

<div style="text-align: justify">

### Equipo de enseñanza
* Manuel Alcántara Juárez <manuelalcantara52@ciencias.unam.mx>
* Ricchy Alaín Pérez Chevanier <alain.chevanier@ciencias.unam.mx>
* Emmanuel Cruz Hérnandez <emmanuel_cruzh@ciencias.unam.mx>

### Objetivo

Analizar e implementar mecanismos de coordinación entre hilos. También mediante ejemplos
aplicarás un par de conceptos para lograr consistencia de memoria cuando más de un hilo
escribe y lee en un espacio compartido de memoria.

### Introducción

Es importante considerar el factor _happens-before_ si se están desarrollando aplicaciones multiproceso en Java para comprender cómo es que se manejan las variables compartidas dentro de la memoria de Java.

Uno de los conceptos con los que se debe estar familiarizado es la **visibilidad**. Hoy en día, la mayoría de las computadoras cuentan con múltiples núcleos dentro de sus procesadores y cada uno es capaz de manejar múltiples hilos de ejecución. De tal forma que para cada núcleo existen varios niveles de caché tal como se muestra en la siguiente figura

![caches](https://miro.medium.com/max/553/0*iqPb69ZRrNgy_-5b.jpg)

Dicho lo anterior, la visibilidad de las operaciones de escritura en variables compartidas puede causar problemas durante los retrasos que ocurren al escribir en la memoria principal debido al almacenamiento en caché en cada núcleo. Esto puede resultar en que otro hilo lea un valor obsoleto (no el último valor actualizado) de la variable.

#### Relación Happens-before

Si existe una relación _happens-before_ entre una operación de escritura y lectura, se garantiza que los resultados de una escritura de un hilo serán visibles para una lectura de otro hilo. Por lo tanto, seremos capaces de mantener la consistencia de la memoria si podemos tener la relación _happens-before_ entre las acciones.

La sincronización se utiliza ampliamente para lograr exclusión mutua entre hilos, que en Java se define con la palabra reservada `synchronized`, con la cual se da la capacidad de limitar el acceso a un bloque de código en particular o a un método a un solo hilo. Cada hilo fuera del bloque sincronizado, debe esperar hasta que el hilo dentro del bloque termine de ejecutarlo y lo libere.

Otra propiedad importante de `syncrhonized` es que ayuda a lograr una relación _happens-before_ entre bloques de código o métodos. Si hay dos bloques sincronizados que tienen el mismo bloqueo, existe una relación _happens-before_ entre las acciones dentro de los bloques de sincronización. Esto se debe al hecho de que un desbloqueo en un bloqueo de objeto ocurre antes de cada adquisición posterior del mismo bloqueo de objeto.

Los errores de consistencia de memoria ocurren cuando diferentes hilos tienen vistas inconsistentes de lo que deberían ser los mismos datos. Para evitar estos errores, es importante considerar y entender la relación _happens-before_ en esta práctica.

#### Campos volátiles

Otra forma de lograr una relación _happens-before_ es con la escritura de una variable con el modificador volátil, ya que cada escritura a una variable con este modificador ocurre antes de cada lectura posterior del mismo, incluso si suceden en distintos hilos.

En Java se utiliza la palabra reservada **volatile** para crear una variable volátil. Es importante considerar que la palabra reservada **volatile** no reemplaza los bloques o métodos sincronizados. Este será útil únicamente cuando se quiera lograr visibilidad para las variables compartidas que son leídas y escritas por diferentes hilos.

Lo anterior implica que aún será necesario utilizar la sincronización cuando se requiera lograr exclusión mutua entre hilos.

Además de la sincronización y la volatilidad, Java define varias reglas para lograr una relación _happens-before_, que puedes consultar en [Oracle Docs](https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html#jls-17.4.5).

### Desarrollo
En esta práctica trabajarás con una base de código construida con Java 11 y Maven Wrapper, también proveemos pruebas unitarias escritas con la biblioteca **Junit 5.7.2** que te darán retrospectiva inmediatamente sobre el correcto funcionamiento de tu implementación.

Para ejecutar las pruebas unitarias necesitas ejecutar el siguiente comando:

```
$ ./mvnw test
```

Para ejecutar las pruebas unitarias contenidas en una única clase de pruebas, utiliza un comando como el siguiente:

```
$ ./mvnw -Dtest=MyClassTest test
```

En el código que recibirás la clase **App** tiene un método __main__ que puedes ejecutar como cualquier programa escrito en __Java__. Para eso primero tienes que empaquetar la aplicación y finalmente ejecutar el jar generado. Utiliza un comando como el que sigue:

```
$ ./mvnw package
... o saltando las pruebas unitarias
$ ./mvnw package -DskipTests
...
...
$ ./mvnw exec:java 
```

### Configuración de los git hooks para formatear el código

Antes de empezar a realizar commits que contengna tu solución tienes cque configurar un módulo de git que te ayudará a formatear tu código.


```
./mvnw git-code-format:install-hooks
```

### Configuración con CircleCI

Configura este repositorio para que ejecute las pruebas unitarias por medio de **CircleCI**. Ya incluimos el archivo de configuración de circleci, por lo que solo tienes que crear una cuenta en circleci, conectarla con tu cuenta de github y hacer que sobre este proyecto se ejecuten los workflows/jobs de circleci cada vez que un Pull Request es creado o actualizado.

```
.circleci/config.yml
```

### Entrega

Por omisión Github Classroom genera un PR con el título feedback por ti, lo único que tienes que hacer es dejar todo tu trabajo en la rama de master para que ese PR se actualice. Es en este PR donde vamos a colocar todo los comentarios referentes a tu entrega.
Las pruebas unitarias también se ejecutan con ayuda de github classroom dentro del PR. 

Para verificar que tu código cumple con las validaciones mínimas de entrega, en tu pull request debes de pasar las dos validaciones, la que hace `CircleCI` sobre de code formatting, y la que hace github classroom al ejecutar las pruebas unitarias.

Además no olvides marcar en classroom la tarea como entregada y en ella incluir el enlace hacia el `pull request` que contiene tu solución. 

La fecha de entrega de tu práctica va a ser el máximo entre la fecha en la que abriste el PR y la fecha en la que hiciste el último push al repositorio con tu solución.

### Problemas


#### [PROBLEMA 1] Algoritmo de Peterson

Para esta actividad tienes que implementar el algoritmo de Peterson visto en clase para 
generar un `candado` que solucione el problema de la exclusión mutua para dos hilos.

Debes de tener cuidado de que exista consistencia de memoria asegurando que suceda la 
relación happens-before cuando un hilo leerá lo que otro escribió. En particular cuando
un hilo marca que él es la víctima que debe de esperar.

Argumenta con comentarios en el código por qué tu solución es correcta.

##### Especificación del programa

En el código fuente que acompaña a este documento encontrarás la clase `PetersonLock` que 
implementa la interfaz `Lock`, tu tarea es completar la implementación de esta clase. 
Para validar que tu implementación es correcta tienes que pasar todas las pruebas 
unitarias que se encuentran en la clase `PetersonLockTest`.

#### [PROBLEMA 2] Modificar un contador

En clase vimos que si intentamos incrementar un contador usando múltiples hilos, es posible que en ocasiones no nos arroje el resultado esperado, debido principalmente a que la operación de incremento y decremento no son atómicas, ocasionando una condición de carrera.

El objetivo de este ejercicio es que obligues a las ejecuciones para producir un resultado específico usando _sleeps_ para retrasar a los hilos.

Agrega instrucciones del tipo:
```java
if(id == # && iteration == #) Thread.sleep(#)

if(iteration == #) Thread.sleep(#)

if(iteration == # && totalRounds == #) Thread.sleep(#)
```

Para retrasar a los hilos dentro, usa su identificador y la ronda que ejecuta. Como los Sleep no garantizan nada, debes de tener una tasa de éxito de al menos 50%. Es decir si se ejecutan 10 veces tu código, el test debe de producir el valor esperado al menos 5 veces.

##### Especificación del programa

Para esta actividad tienes que implementar la clase `TwoValueCounter` y `FixedValueCounter`, que contiene los métodos `getAndIncrement`, `getAndDecrement`, `getValue`. Para validar que tu implementación sea correcta tienes que pasar todas las pruebas unitarias que se encuentran en la clase **TwoValueCounterTest** y **FiveValueCounterTest**.

#### [PROBLEMA 3] Policía y Ladornes

1. Vamos a suponer que tenemos un vault con cierto dinero, y dos tipos de hilos, los ladrones quieren hackear el sistema para robarse el dinero y un policia que podría detener a esos ladrones.
2. El objetivo de los ladrones es tratar de encontrar la contraseña que abre un vault, para ellos cada uno explora un distinto espacio de búsqueda.
  * El ladrón debe de verifica si el vault ya está abierto, esto puede suceder porque el otro ladrón lo abrió mientras tú intentabas una clave incorrecta.
  * En cada iteración se trata de abrir el vault
  * Si acerta a la contraseña deja de buscar la contraseña.
  * Si es capturado por el policía se informa sobre su captura y se deja de buscar la contraseña. El ladrón es capturado cuando el hilo en el que corre es interrumpido `t.interrupted() -> true`.
  * Si no suceden los casos anteriores, se aumenta la cantidad de intentos y se vuelve a probar con una nueva propuesta de contraseña.
  * Una vez que el vault fue abierto, ya no se debe de poder volver a cerrar, incluso si un ladrón intenta introducir una contraseña incorrecta.
3. Si el policía llega pero los ladrones aún no han salido con el dinero, entonces los arresta, mandando interrupciones sobre el hilo correspondiente al ladrón `thiefThread.interrupt()`.

**Nota:** Los hilos ladrones tratan concurrentemente de encontrar la contraseña para abrir
el vault, por lo que tienes que utilizar el `Lock` que implementaste en el problema 1 para
marcar cuando el vault ya fue abierto, en otro caso tendrás una condición de carrera entre
marcar el vault como abierto o cerrado si más de un ladrón está actuando sobre él al mismo 
tiempo.

##### Especificación del programa

Para esta actividad tienes que implementar en la clase `PoliceThiefGameSimulation` la simulación del juego de los ladrones y los policías. Para ello, es necesario que completes implementación de las siguientes clases y sus respectivos métodos:

* `ThiefImpl`
  * `tryToFindPassword()`
  * `getId()`
  * `getTries()`
* `VaultImpl`
  * `isPassword(int guess)`
  * `isPasswordFound()`

  Considera que en la simulación se debe cumplir la propiedad de que no siempre gana el policía ni siempre ganan los ladrones. En la prueba unitaria se verifica que ambos hayan ganado al menos una vez.

#### [PROBLEMA EXTRA] Exclusión Mutua para `N` hilos. Algoritmo del Filtro.

**El algoritmo de Peterson** es un algoritmo para programación concurrente para resolver el problema de exclusión mútua, 
que permite a dos o más procesos compartir un único recurso compartido sin conflictos, usando memoria compartida para 
comunicarse. El algoritmo fue formulado por Gary L. Peterson en 1981 para dos procesos, sin embargo, se ha generalizado 
para más de dos.

El algoritmo del filtro generaliza el algoritmo de Peterson para n>2 procesos. En lugar de una bandera o identificador 
booleano, se requiere un entero por cada uno de los procesos, almacenado en un único registro atómico de un solo 
lector/escritor. Los registros pueden ser representados en pseudocódigo como arreglos.

```
level : array of N integers
last_to_enter : array of N−1 integers
```

```
i ← ProcessNo
for ℓ from 0 to N−1 exclusive
    level[i] ← ℓ
    last_to_enter[ℓ] ← i
    while last_to_enter[ℓ] = i and there exists k ≠ i, such that level[k] ≥ ℓ
        wait
```

##### Especificación del programa

Para esta actividad tienes que implementar el algoritmo de Peterson visto en clase para 
generar un candado que solucione el problema de exclusión mutua para `n` hilos.

Crea la clase `FilterAlgorithmLock` que implementa la interfaz `Lock`. 
Para validar que tu implementación sea correcta tienes implementar pruebas unitarias,
para ello crea la clase `FilterAlgorithmLockTest`.

Para que el punto extra cuente, debes escribir las pruebas unitarias de este ejercicio para 
comprobar que está bien implementado. 
Puedes basarte en las pruebas hechas para el Algoritmo de Peterson definidas en `PetersonLockTest`.

</div>