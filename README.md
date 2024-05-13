# Examen Unidad 3
# Sistema para un Banco
# Integrantes
Atziri Estefanía Mancilla Cruz

Jafet Santoyo Benítez

Andrea Durán Martínez

El proyecto está diseñado de tal forma que el sistema cuenta con 2 sucursales
que no comparten la información de sus usuarios entre sí, de ningún tipo.

El sistema cuenta con los 6 roles siguientes.

- Clientes
- Inversionistas
- Empleados de 3 tipos: 
    - Gerente de sucursal
    - Ejecutivos de cuenta
    - Capturista

Todos los roles comparten información en común como nombre,apellidos, ciudad,estado,curp, direccion, año de Nacimiento,fechaNacimiento, rol, rfc, nombre de Usuario, y contrasena.

# Clientes
Se crean mediante Ejecutivos de cuenta y gerentes. Se le asigna una tarjeta de débito con la información principal de una tarjeta cuando se crea. 
# Empleados:
Información adicional: fecha de inicio de trabajo. 
# Gerente
hace todas operaciones con las que cuente el sistema.
# Capturista
 Hace registro o modificación únicamente a los ejecutivos de cuenta.
 # Ejecutivo de cuenta
 Hace registro, modificaciones, eliminaciones, búsquedas de cuentas de cliente.
 Autoriza tarjetas de crédito. 
 # Inversionistas
Son registrados, modificados, eliminados, etc. únicamente por el gerente, y para cualquier operación que se desee hacer con los inversionistas, el sistema tendrá que solicitar una contraseña.

# Tarjetas
Un cliente puede tener máximo 1 tarjeta de débito y hasta 3 tarjetas de crédito.
Las tarjetas de crédito se dividen en 3, simplicity, platino y oro.
El cliente podrá solicitar las tarjetas una vez que tenga en la tarjeta de débito o una cantidad mayor a $50,000 pesos, $100,000 pesos y $200,000 pesos, respectivamente.

- **Tarjeta simplicity** - Crédito máximo de $60,000 MXN
- **Tarjeta platino** - Crédito máximo de $150,000 MXN
- **Tarjeta oro** - Crédito máximo de $400,000 MXN

-  El sistema detecta si el cliente tiene la cantidad permitida para solicitar alguna tarjeta y se habilita una opción para solicitar
-  Según su su saldo puede solicitar la tarjeta si no es suficiente, no le aparece la opción de solicitud.
  # Solicitudes de tarjeta
  Los empleados con el rol de ejecutivo de cuenta (y gerente)son los únicos que pueden autorizar o rechazar la solicitud.
En caso de aceptarla, se genera una tarjeta y se le asocia al cliente.
