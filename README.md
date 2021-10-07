# Aventuras de Marcos y Luis
Este programa contiene las carpetas necesarias para hacer
funcionar ciertas características del juego "Las Flipantes 
Aventuras de Marcos & Luis". Entre las cuales destacan, un sistema
de dificultad, subida de niveles, ataques e instanciación de Jugadores y
Enemigos, que posteriormente podrán ser utilizados dentro del juego.

La carpeta animantia contiene todo lo que vendrían a ser
los objetos "vivos" capaces de interactuar con el resto
de objetos, dentro de ella, tenemos la superclase abstracta
AbstractAnimatia la cual es encargada de crear objetos
vivos con características en común para todos los seres vivos;
dentro de esta, tenemos las subclases AbstractPlayer y AbstractEnemy, que
se diferencian principalmente en que AbstractPlayer tiene un inventario
compartido entre todas sus subclases (Marcos y Luis) en el cual hay
3 items inicializados (Star, RedMushroom y HoneySyrup) con cantidad 0,
que podrán ir consiguiéndose y utilizándose a lo largo del juego
de forma limitada. Además de esto, posee un campo
llamado FP usado para limitar la cantidad de ataques
que puede usar los jugadores por ronda (no implementada todavía), 
y un mecanismo de obtención de experiencia y subida de niveles 
durante el combate (no implementado); mientras que en la clase
AbstractEnemy tenemos un par de variables estáticas (difficulty y power)
que son usadas para determinar el nivel y estadísticas
que tendrá el siguiente enemigo generado en alguna de las subclases de
AbstractEnemy; esto con la finalidad de que el nivel de los
enemigos vaya incrementándose a la par del nivel de los jugadores,
lo que hará que el juego nunca sea, o demasiado fácil o demasiado
difícil para el usuario.
Por último, tenemos un Enum llamado PlayerAttackType usado para definir
los diferentes tipos de ataque que puede emplear un jugador a la hora de 
atacar a un enemigo.

La carpeta interfaces contiene 7 interfaces, de las cuales 4 (las tienen como
prefijo Attackable) son usadas para restringir a qué enemigos pueden atacar
los distintos tipos de jugadores y viceversa, luego tenemos la interfaz
CanBeAttacked usada para caracterizar a todos los objetos que pueden ser
atacados a la hora de querer realizar un ataque, después está la interfaz
Consumable usada para caracterizar los métodos y comportamientos que tendrán
todos los items del juego, además de servir para instanciar objetos de este tipo
en vez de tener que hacerlo con la superclase abstracta de los items. Finalmente,
está la interfaz IPlayer, usada para determinar los efectos que tendrán los items
al ser consumidos por los jugadores, además de servir para instanciar de forma genérica
genérica a los jugadores que quieran utilizar estos items sin tener que recurrir a
instanciarlos como AbstractPlayer.

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