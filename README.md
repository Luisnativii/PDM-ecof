**Universidad Centroamericana José Simeón Cañas ![ref1]Departamento de electrónica e informática Programación de dispositivos móviles, ciclo 01-2024. Taller 2.** 

**Objetivos.** 

Con esta actividad el estudiante aprenderá a: 

1. Simular  una  experiencia  de  desarrollo  en  donde  se  resuelva  una  necesidad  real  y  donde  el estudiante sea capaz de tomar decisiones técnicas relacionadas con el desarrollo del proyecto. 
1. Implementar Room para la gestión de bases de datos locales, y Retrofit para consumir APIs RESTful en un entorno de trabajo Android. 
1. Desarrollar soluciones que combinan acceso a datos locales y remotos en un flujo de datos unificado. 
1. Aplicar patrones de arquitectónicos y de diseño recomendados para seguir las mejores prácticas con el fin de optimizar la eficiencia y escalabilidad de la aplicación. 

**Requerimientos de la actividad.** 

Todos los equipos deberán cumplir con los siguientes requerimientos. 

1. No se aceptan entregas individuales. El taller debe realizarse en parejas de trabajo. 
1. Cada equipo debe crear el grupo y asignar un nombre en la plataforma e-campus. 
1. A  continuación,  crear  el  repositorio  en  el  enlace  disponible  en  la  plataforma  e-campus.  El repositorio debe tener el mismo nombre del grupo creado en el punto 1.  
1. Los repositorios se configurarán como solo lectura el **sábado 8 a las 6:00am**. Luego de esta hora no podrán agregar nuevas modificaciones al proyecto. 
1. El taller debe realizarse bajo una metodología de GitFlow.  
1. En el repositorio debe incluirse el proyecto de Android y el API creada. 
1. La defensa se realizará el sábado 8 a partir de las 8:00am, pronto se compartirá un enlace para poder definir la hora de la defensa de cada equipo. 
1. En la defensa, cada equipo debe descargar frente al evaluador el proyecto Android desde Git y compilar  esa  versión.  Con  respecto  al API,  cada  grupo  debe  llevar  el  servicio API  listo  y ejecutándose en local o en línea, esto con el objetivo de agilizar la presentación. 
1. Debe existir algún método para evaluar que la información se ha almacenado en la capa de datos del servicio. (En otras palabras, debo ser capaz de ver la información en el API a través una ruta que devuelva toda la información o hacer una consulta select en la base de datos que el equipo haya seleccionado). 
1. No hay consultas habilitadas sobre cualquier tema relacionado a Android, Android Studio, Git, y errores de compilación. 
1. No seguir cualquiera de los puntos anteriores se penalizará con una nota de 0.0. 

**Contextualización.** 

El gobierno de El Salvador se encuentra trabajando en el nuevo plan de salud integral para la familia salvadoreña. Entre los objetivos planteados se encuentra aumentar el alcance de los servicios de salud, educación higiénica y educación sexual, sobre todo en familias que pertenecen a comunidades que tienen un alto grado de vulnerabilidad. 

Entendiendo el contexto nacional, las autoridades del Ministerio de Salud, han planteado una estrategia para lograr el objetivo antes descrito. Una de las grandes limitantes que se deben superar es que muchas familias viven en zonas que no disponen de un centro de salud nacional, y poder asistir a uno implica para estas familias movilizarse durante varias horas debido a la lejanía de los centros de salud y la falta de red vial o transporte público. Por esta razón se han creado los Equipos Comunitarios de Salud Familiar (ECOSF), conformados  por médicos,  personal  de enfermería y promotores de salud  y polivalentes, quienes, dentro de sus funciones, deben realizar tareas de recopilar información de variables de las familias y personas, con la finalidad de analizar el riego familiar (vulnerabilidad) y riesgo personal para su posterior seguimiento. 

La recopilación de información se lleva a cabo de forma manual para la mayoría de los ECOSF a nivel nacional. Cada uno de ellos realiza visitas de terreno en las zonas que le han sido asignadas, registra los datos ofrecidos por la cabeza de familia (padre o madre) y los transcribe en un instrumento conocido como ficha familiar. Finalmente, la información recopilada es enviada a regiones de salud en donde un grupo de digitadores ingresan los datos en el sistema diseñado por la Dirección de Tecnología de la Información y la Comunicación (DTIC) del Ministerio de Salud (MINSAL). Este método de procesar o transmitir la información  afecta  a  la  disponibilidad  e  integridad  de  los  datos,  debido  a  los  elementos  humanos involucrados en el proceso. 

Ante esta problemática, su equipo de trabajo ha sido asignado para superar las limitantes del modelo de trabajo actual, esto a través del desarrollo de una aplicación móvil que permita registrar de manera digital la información de las familias beneficiadas con el programa de seguimiento realizado por los ECOSF. La aplicación debe cumplir con los siguientes requisitos: 

**Tarea 1. Desarrollar un módulo que permita el registro de información a nivel familiar y personal de las comunidades visitadas.**  

Más adelante en este documento se define el método de almacenamiento de la información (local o remoto). Para cumplir con este requerimiento, es necesario **registrar** la información a dos niveles: a nivel familiar y a nivel personal. 

- A nivel familiar interesa saber: el cantón o comunidad en donde está ubicada la familia, el tipo de vivienda (bahareque, adobe, lamina, ladrillos, bloques de cemento, de madera, otros.), el riesgo de la familia (bajo, medio, alto, vivienda deshabitada). 
- A nivel personal interesa registrar la información de todos los integrantes de la familia que habita la vivienda del punto anterior, la información que se requiere es numero de DUI (si aplica), nombre completo, fecha de nacimiento, el grado escolar, y si sabe o no leer y escribir. 
- El equipo de trabajo es libre de definir la interfaz de usuario para este módulo. 

Otra de las limitantes que se desean superar con esta aplicación es que, según la experiencia obtenida por otros proyectos de trabajo con comunidades, muchas veces es difícil definir una referencia clara de una vivienda, esto porque en las comunidades muchas veces no existe una distribución de casas homogénea, tampoco existen calles y caminos bien distribuidos e identificados, por lo que existen muchas dificultades para llevar un seguimiento óptimo de las familias. Hasta este momento, los equipos comunitarios han tenido la obligación de memorizar la ubicación de una vivienda o utilizar mapas para marcar un punto aproximado de donde se encuentra la vivienda. Este problema puede ser superado al aprovechar las características de un dispositivo móvil, por lo tanto, se debe asegurar que: 

**Tarea 2. Implementar un mecanismo para registrar la ubicación georreferenciada de una vivienda. El equipo es libre de implementar este módulo de la forma que más crea conveniente.** 

El tercer reto que se debe considerar es que, por las limitantes de infraestructura del país, en muchas zonas rurales no es posible hacer uso de una conexión a Internet, por lo que acceder a recursos en línea o almacenar información en un servidor remoto se vuelve una tarea imposible de realizar. Los equipos han tenido la obligación de escribir la información a mano y luego digitalizarla. Se propone resolver esta situación a través del siguiente requerimiento en la aplicación móvil: 

**Tarea 3. Agregar a la aplicación un mecanismo que permita almacenar la información de forma local, y que, cuando el equipo de trabajo verifique que tiene acceso a Internet, pueda subir la información a un servidor remoto ubicado en las instalaciones del MINSAL.** 

Con este requerimiento, el equipo de trabajo debe asegurar que la aplicación siempre almacene por defecto la información en una base de datos local. La aplicación debe contener un módulo que permita migrar la información desde la base local a una base de datos remota cuando el usuario lo decida, se sugiere seguir un desarrollo de arquitectura en capas, lo que implica el desarrollo de un API de pruebas para poder gestionar la migración de la información. Este módulo tiene algunos requerimientos secundarios: 

- Se debe asegurar que la información de una familia no se duplique cuando se realice a la migración de la información. El equipo debe proponer un método para asegurar este requerimiento. 
- En la migración debe incluirse toda la información de la familia y de todos sus integrantes obtenida en la tarea 1, además de la información georreferenciada obtenida en la tarea 2. 
- El equipo de trabajo es libre de diseñar los modelos de datos a conveniencia, sin embargo, se exige seguir las buenas prácticas necesarias en modelos relacionales y no relacionales. 
- El equipo de trabajo es libre de definir la interfaz de usuario para este módulo. 

Finalmente, este proyecto se visualiza una aplicación con muchas funcionalidades más, por lo que es probable que se integren otros desarrolladores, por esta razón, se requiere: 

**Tarea 4. El proyecto debe seguir alguna arquitectura recomendada para el desarrollo de aplicación para dispositivos móviles. Se sugiere utilizar un patrón arquitectónico MVVM**. 

**Métrica de evaluación** 

**Universidad Centroamericana José Simeón Cañas ![ref1]Departamento de electrónica e informática Programación de dispositivos móviles, ciclo 01-2024. Taller 2.** 

**Métrica.** 



|**Punto que se evalúa** |**Porcentaje de la nota** |**Nota obtenida** |**Comentario** |
| - | - | :- | - |
|**Cumplimiento de requerimientos (80%)** ||||
|Tarea 1. registro de información |20% |||
|Tarea 2. Registro de ubicación georreferenciada |20% |||
|Tarea 3. Migración de datos a base de datos remota  |30% |||
|Tarea 4. Implementación de patrón MVVM |10% |||
|**características del producto (20%)** ||||
|Se evalúa consistencia, diseño visual, documentación y ayuda, navegación intuitiva y desarrollo basado en gitflow |20% ||<p>- Diseño visual: la aplicación esta alineada con la institución (MINSAL) </p><p>- Documentación y ayuda: la aplicación informa del éxito o error de los procesos realizados, por ejemplo, almacenar la información o migrar la información. </p><p>- Navegación intuitiva: el usuario puede acceder de forma intuitiva a cada funcionalidad de la aplicación </p><p>- GitFlow: el repositorio tiene una línea de avance lógico en el desarrollo. </p>|
|**Penalizaciones** ||||
|Lista de penalizaciones |0% |||
|**Total** |100% |0% ||
**Sobre el autor de este caso de estudio.** 

**Autor:** Erick Varela-Guzmán 

Departamento de Electrónica e Informática, Universidad Centroamericana José Simeón Cañas, La Libertad, El Salvador. 

**Contacto:** evarela@uca.edu.sv 

**Versión de este documento:** 1, junio 2024. 

**Aclaración:** Este es un escenario ficticio creado con el único objetivo de ser un caso de estudio para el curso de programación de dispositivos móviles. 

![](Aspose.Words.78c2357a-46d5-401f-a603-55aa4753c459.002.png)

This work is licensed under a[ Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License ](http://creativecommons.org/licenses/by-nc-sa/4.0/)

[ref1]: Aspose.Words.78c2357a-46d5-401f-a603-55aa4753c459.001.png
