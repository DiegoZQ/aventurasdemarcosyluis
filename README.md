# AventurasdeMarcosyLuis
Este programa contiene las carpetas necesarias para hacer
funcionar ciertas características del juego "Las Flipantes 
Aventuras de Marcos & Luis". Entre las cuales destacan, un sistema
de dificultad, subida de niveles, ataques e instanciación de Jugadores y
Enemigos, que posteriormente podrán ser utilizados en el juego.

La carpeta animantia contiene todo lo que vendrían a ser
los objetos "vivos" capaces de interactuar con el resto
de objetos, dentro de ella tenemos la superclase abstracta
AbstractAnimatia, que es la encargada de crear objetos
vivos con características en común para todos los seres vivos;
dentro de esta, tenemos las subclases Player y Enemy, que
se diferencian principalmente en que Player tiene un inventario
compartido entre todos los objetos de tipo Player en el cual
se pueden utilizar 3 items (Star, RedMushroom y HoneySyrup) 
de forma limitada, además de poseer un campo
llamada FP usada para limitar la cantidad de ataques
que puede usar por ronda (no implementada todavía), 
y un mecanismo de obtención de experiencia y subida de niveles 
durante el combate (no implementado); mientras que en la clase Enemy
tenemos un par de variables estáticas difficulty y power
que son usadas para determinar el nivel y estadísticas
que tendrá el siguiente enemigo generado, esto con la finalidad
de que el nivel de los enemigos vaya incrementándose a la par
del nivel de los jugadores, lo que hará que el juego nunca sea,
o demasiado fácil o demasiado difícil.
Finalmente tenemos otra clase, independiente
de las últimas tres mencionadas, AttackTable usada para definir
únicamente dos matrices booleanas constantes y estáticas, que son
utilizadas para determinar si un cierto tipo de Player puede
atacar a cierto tipo de Enemy y viceversa.

La carpeta items contiene una superclase abstracta denominada
AbstractItem, la cual es utilizada para designar
el comportamiento principal que tienen los items, como ser
utilizados por algún jugador, gastarse y dar efectos específicos
dependiendo el item en particular. Luego de eso, se tienen 3 subclases
de items, cada una entregándole un efecto particular al jugador
que las usa. Y por último está Items, que es
básicamente un Enum utilizado para llamar al índice de uno de los
tres ítems instanciados en el inventario del Jugador de forma interna, y
así interactuar con él de manera directa para consumirlo y
darle efectos al jugador o guardarlo y así aumentar la cantidad del item específico en 1.

La carpeta types contiene 4 Enums usados para guardar información
acerca de todos los tipos de Player, Enemy y los ataques que pueden
utilizar cada uno de ellos. Específicamente, tanto PlayerType
como EnemyType contienen la información de los tipos de jugadores
y enemigos con sus estadísticas respectivas a nivel uno, como también
un índice utilizado para determinar la posibilidad ataque
en las matrices definidas en AttackTable de carpeta Animantia; además
de eso, en EnemyType también se definen métodos específicos
para determinar el resultado de la interacción de que un
Player use un ataque específico para un EnemyType particular.