cc7-lab8
--------
## Requisitos ##

 - Un puente donde una persona a la vez puede cruzar, durante un lapso de 1-3 segundos.
 - El las orillas del puente puede haber un máximo de 4 personas, dichas personas deben ir en la misma dirección.
 - Sí el puente está vacío, la primera persona que llegue puede cruzar sin inconvenientes.
 - Dichas personas pueden usar el puente para entrar o salir del edificio.
 - Las personas van llegando con una lapso aleatorio de: [0,3]
 - Evitar starvation.

## Trivia ##

 **- ¿Podría existir starvation?**
Sí, suponiendo el caso en que lleguen muchas personas que van a una misma dirección, y exista una que desee ir en la dirección opuesta. 

Este último tendría que esperar que el resto termine de pasar para poder intentarlo.

 **- De ser así ¿Cómo podría solucionarse?**
Verificar después de que hayan cruzado 4 o menos personas, si existen personas que deseen cruzar en sentido opuesto; de ser así se dejaría pasar a no más de 4 personas. 

Esto permitirá alternar el traslado de 4 personas en ambas direcciones, al realizar la misma verificación indefinidamente.

 


----------

**Galileo University**
**2016**

