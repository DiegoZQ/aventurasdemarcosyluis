# Aventuras de Marcos y Luis
Este programa contiene las carpetas necesarias para hacer
funcionar el juego llamado "Las Flipantes 
Aventuras de Marcos & Luis". Entre las características que componen el juego, destacan, un sistema de subida de niveles, 
dos tipos de ataques para los jugadores y uno para los enemigos,
ambos con sus respectivos stats, instanciación de Jugadores y Enemigos, un sistema de rondas, con turnos incluidos,
para que los jugadores y los enemigos peleen, dando la oportunidad de que los jugadores puedan elegir
entre varias alternativas qué hacer en cada turno, mientras que los enemigos solo atacan. Todo esto
haciendo uso de un controlador, unas clases auxiliares del controlador y unos prints para ir viendo más detalladamente cómo
se desenvuelven los personajes en batalla (dichos prints hacen el papel de una interfaz gráfica no implementada).

La carpeta animantia contiene todo lo que vendrían a ser
los objetos "vivos" capaces de interactuar con el resto
de objetos, dentro de ella, tenemos la superclase abstracta
AbstractAnimatia la cual es encargada de crear objetos
vivos con características en común para todos los seres vivos;
dentro de esta, tenemos las subclases AbstractPlayer y AbstractEnemy, que
se diferencian principalmente en que AbstractPlayer tiene un cofre
compartido entre todas sus subclases (Marcos y Luis) en el cual hay
2 items inicializados (RedMushroom y HoneySyrup) con cantidad 0,
que podrán ir consiguiéndose y utilizándose a lo largo del juego
de forma limitada. Además de esto, posee un campo
llamado FP usado para limitar la cantidad de ataques
que puede usar los jugadores por ronda, y un mecanismo de subida de niveles cada 
vez que se avanza a una nueva ronda;
mientras que en la clase AbstractEnemy tenemos la variable estática power
que es usada para determinar el nivel y estadísticas
que tendrá el siguiente enemigo generado en alguna de las subclases de
AbstractEnemy (Goomba, Boo o Spiny); esto con la finalidad de que el nivel de los
enemigos vaya incrementándose a la par del nivel de los jugadores,
lo que hará que el juego nunca sea, o demasiado fácil o demasiado
difícil para el usuario conforme avance el juego.
Por último, tenemos un Enum llamado PlayerAttackType usado para definir
los diferentes tipos de ataque que puede emplear un jugador a la hora de 
atacar a un enemigo, cada uno con diferentes constantes de ataque (ponderador del daño
en la fórmula de ataque), una precisión que determina la probabilidad de acertar el ataque y
el gasto que dicho ataque genera al jugador.
Dentro de AbstractPlayer tenemos a dos personajes principales, Marcos y Luis representados
mediante subclases de AbstractPlayer. Marcos es el jugador
centrado en defensa y salud, además de ser el único capaz de atacar a todos los enemigos; por otro lado
Luis es el jugador centrado en ataque, capaz de atacar a todos los enemigos a excepción de Boo.
Dentro de AbstractEnemy tenemos a tres enemigos principales, Goomba, Spiny y Boo, los cuales son representados
mediante subclases de AbstractEnemy. Goomba es el enemigo básico por defecto pues no se caracteriza en nada,
ataca tanto a Marcos como a Boo de manera aleatoria y puede ser herido por ambos y con cualquier ataque,
mientras que Spiny, un enemigo centrado en la defensa, puede también atacar a ambos jugadores, pero
este solo puede ser herido por un ataque tipo MARTILLO, independiente del jugador que efectúe el ataque; 
finalmente Boo es el enemigo centrado en ataque, el cual solo puede ser herido por Marcos, y solo ataca
a Luis.

La carpeta game contiene todo lo que vendría a ser el controlador del juego y clases auxiliares, las cuales
lo ayudan a distribuirse las tareas. El controlador es el encargado de manejar un juego (con 5 rondas incluidas)
entre jugadores y enemigos, con sus respectivas particularidades a la hora de atacar o ser atacado dependiendo
del jugador o enemigo en cuestión, este tiene implementado un sistema de turnos que se maneja con una lista
de todos los personajes con vida y un índice que lo recorre, además de esto hay una serie de fases
definidas para cada personaje en particular, excepto goomba y spiny, que fueron incluidos en la misma fase,
puesto que sus métodos para interactuar en combate son exactamente los mismos, en cada fase se procede a tomar
un personaje de una lista genérica que contiene ta todos los personajes y obtener su tipo correspondiente,
usando un campo de phaseTurn y listas de la fase en cuestión, esto con el fin de obtener el turnOwner
evitando hacer uso de casting. El juego contiene un total de 5 rondas jugables, con dificultad ascendente, y
se detiene automáticamente cuando ambos jugadores quedan KO, lo que resulta una derrota para los jugadores, o en su
defecto, los jugadores llegan a la ronda 5 y la ganan, concluyendo así el juego.
Entre las acciones que pueden efectuar los jugadores en el combate, tenemos el pasar de turno (no hace nada),
usar un objeto (una REDMUSHROOM para recuperar hp o HONEYSYRUP para recuperar fp) en sí mismo o
su aliado, o decidir atacar a un enemigo dando el índice del enemigo en cuestión en una lista que
se genera en el momento de todos los enemigos que puede atacar el personaje que es dueño del turno,
además de poder elegir qué tipo de ataque realizar. Para el caso de los enemigos es un poco más simple,
pues los enemigos atacan a un personaje aleatorio dentro de la lista de personajes disponibles para
atacar, o se le puede designar un personaje en específico para no dejar las pruebas en manos del azar
(esto aplica solo para goomba y spiny, pues Boo solo podrá atacar única y exclusivamente a Luis).
Finalmente, la clase GameRunner permite correr un juego con todo lo especificado anteriormente.

La carpeta interfaces contiene 6 interfaces, de las cuales 2 (las tienen como
prefijo Attackable) son usadas para restringir a qué enemigos pueden atacar
los distintos tipos de jugadores y viceversa, luego tenemos la interfaz
CanMove usada para caracterizar a todos los objetos que pueden ser
atacados a la hora de querer realizar un ataque, después está la interfaz
Consumable usada para caracterizar los métodos y comportamientos que tendrán
todos los items del juego, además de servir para instanciar objetos de este tipo
en vez de tener que hacerlo con la superclase abstracta de los items. Después está,
está la interfaz IPlayer, usada para determinar los efectos que tendrán los items
al ser consumidos por los jugadores, además de servir para instanciar de forma genérica
a los jugadores que quieran utilizar estos items sin tener que recurrir a
instanciarlos como AbstractPlayer y finalmente está la interfaz IEnemy usada para caracterizar
a los enemigos con el hecho de que todos pueden ser atacados por Marcos.

Finalmente, tenemos la carpeta items, la cual contiene una superclase abstracta 
denominada AbstractItem que implementa la interfaz Consumable, y es utilizada
para designar el comportamiento principal que tienen los items, como ser
utilizados por algún jugador, gastarse y dar efectos específicos dependiendo
el item en particular. Luego de eso, se tienen 3 subclases de items,
cada una entregándole un efecto particular al jugador que las usa.
Y por último está ItemEnum, que es básicamente un Enum utilizado
para llamar al índice de uno de los tres ítems instanciados en el
inventario del Jugador de forma interna, y así interactuar con él
de manera directa para consumirlo y darle efectos al jugador, o guardarlo
así aumentar la cantidad del item específico en un determinado valor.