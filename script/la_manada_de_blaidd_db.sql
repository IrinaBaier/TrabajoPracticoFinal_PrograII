DROP DATABASE IF EXISTS la_manada_de_blaidd;
CREATE DATABASE la_manada_de_blaidd
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE la_manada_de_blaidd;



CREATE TABLE adoptantes (
    id_adoptante INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dni INT NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    domicilio VARCHAR(150) NOT NULL
);

CREATE TABLE perros (
    id_perro INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,

    edad ENUM(
        'CACHORRO',
        'ADULTO',
        'ANCIANO'
    ) NOT NULL,

    fecha_entrada DATE NOT NULL,

    estado ENUM(
        'DISPONIBLE',
        'ADOPTADO',
        'NO_DISPONIBLE'
    ) NOT NULL,

    raza VARCHAR(60) NOT NULL,

    porte ENUM(
        'CHICO',
        'MEDIANO',
        'GRANDE'
    ) NOT NULL,

    temperamento ENUM(
        'CALMADO',
        'JUGUETON',
        'ANSIOSO',
        'AGRESIVO',
        'SOCIABLE'
    ) NOT NULL
);


CREATE TABLE solicitudes (
    id_solicitud INT NOT NULL PRIMARY KEY,

    id_adoptante INT NOT NULL,
    id_perro INT NOT NULL,

    fecha DATETIME NOT NULL,

    estado ENUM(
        'PENDIENTE',
        'APROBADA',
        'RECHAZADA'
    ) NOT NULL,

    observacionSolicitud ENUM(
        'ESPACIO_INSUFICIENTE',
        'FALTA_DE_TIEMPO',
        'INCOMPATIBILIDAD_CON_OTRAS_MASCOTAS',
        'NO_APTO_ECONOMICAMENTE',
        'CONVIVENCIA_NO_PERMITIDA',
        'OTRO'
    ),

    FOREIGN KEY (id_adoptante)
        REFERENCES adoptantes(id_adoptante)
        ON DELETE CASCADE,

    FOREIGN KEY (id_perro)
        REFERENCES perros(id_perro)
        ON DELETE CASCADE
);

CREATE TABLE adopciones (
    id_adopcion INT NOT NULL PRIMARY KEY,

    id_adoptante INT NOT NULL,
    id_perro INT NOT NULL,
    id_solicitud INT NULL UNIQUE,

    fecha DATETIME NOT NULL,

    FOREIGN KEY (id_adoptante)
        REFERENCES adoptantes(id_adoptante)
        ON DELETE CASCADE,

    FOREIGN KEY (id_perro)
        REFERENCES perros(id_perro)
        ON DELETE CASCADE,

    FOREIGN KEY (id_solicitud)
        REFERENCES solicitudes(id_solicitud)
        ON DELETE CASCADE,

    UNIQUE (id_perro)
);



CREATE TABLE observaciones (
    id_observacion INT NOT NULL PRIMARY KEY,

    id_adopcion INT NOT NULL,

    fecha DATETIME NOT NULL,

    descripcion TEXT NOT NULL,

    FOREIGN KEY (id_adopcion)
        REFERENCES adopciones(id_adopcion)
        ON DELETE CASCADE
);


INSERT INTO adoptantes
(id_adoptante, nombre, dni, telefono, email, domicilio)
VALUES
(1, 'Juan Perez', 30111222, '2235123456', 'juan@gmail.com', 'Mar del Plata'),
(2, 'Maria Gomez', 28999888, '2234987654', 'maria@gmail.com', 'Batan'),
(3, 'Carlos Lopez', 35123456, '2234556677', 'carlos@gmail.com', 'Mar del Plata'),
(4, 'Laura Fernandez', 32777888, '2234112233', 'laura@gmail.com', 'Miramar'),
(5, 'Pedro Ramirez', 25111999, '2234778899', 'pedro@gmail.com', 'Mar del Plata'),
(6, 'Sofia Martinez', 40555666, '2234667788', 'sofia@gmail.com', 'Santa Clara'),
(7, 'Diego Torres', 33999111, '2234332211', 'diego@gmail.com', 'Mar del Plata'),
(8, 'Camila Rodriguez', 42111222, '2234998877', 'camila@gmail.com', 'Batan');


INSERT INTO perros
(id_perro, nombre, edad, fecha_entrada, estado, raza, porte, temperamento)
VALUES
(1, 'Toby', 'CACHORRO', '2026-10-01', 'DISPONIBLE', 'Labrador', 'GRANDE', 'JUGUETON'),
(2, 'Luna', 'ADULTO', '2026-09-15', 'ADOPTADO', 'Mestiza', 'MEDIANO', 'CALMADO'),
(3, 'Rocky', 'ANCIANO', '2026-08-20', 'DISPONIBLE', 'Ovejero', 'GRANDE', 'SOCIABLE'),
(4, 'Mora', 'CACHORRO', '2026-11-01', 'DISPONIBLE', 'Caniche', 'CHICO', 'ANSIOSO'),
(5, 'Max', 'ADULTO', '2026-07-10', 'ADOPTADO', 'Golden Retriever', 'GRANDE', 'JUGUETON'),
(6, 'Nina', 'ADULTO', '2026-10-15', 'DISPONIBLE', 'Mestiza', 'CHICO', 'CALMADO'),
(7, 'Rocco', 'CACHORRO', '2026-11-05', 'NO_DISPONIBLE', 'Pitbull', 'GRANDE', 'SOCIABLE'),
(8, 'Simba', 'ANCIANO', '2026-06-20', 'DISPONIBLE', 'Mestizo', 'MEDIANO', 'CALMADO'),
(9, 'Kira', 'ADULTO', '2026-09-25', 'ADOPTADO', 'Border Collie', 'MEDIANO', 'SOCIABLE'),
(10, 'Bobby', 'CACHORRO', '2026-11-08', 'DISPONIBLE', 'Mestizo', 'CHICO', 'JUGUETON');


INSERT INTO solicitudes
(id_solicitud, id_adoptante, id_perro, fecha, estado, observacionSolicitud)
VALUES
(1, 1, 2, '2026-10-20 10:30:00', 'APROBADA', 'OTRO'),
(2, 2, 5, '2026-10-22 11:00:00', 'APROBADA', 'OTRO'),
(3, 3, 1, '2026-11-02 09:15:00', 'PENDIENTE', 'OTRO'),
(4, 4, 3, '2026-11-03 14:00:00', 'RECHAZADA', 'FALTA_DE_TIEMPO'),
(5, 5, 4, '2026-11-04 16:30:00', 'PENDIENTE', 'OTRO'),
(6, 6, 9, '2026-10-25 12:00:00', 'APROBADA', 'OTRO'),
(7, 7, 6, '2026-11-06 10:00:00', 'PENDIENTE', 'OTRO'),
(8, 8, 8, '2026-11-07 15:30:00', 'RECHAZADA', 'INCOMPATIBILIDAD_CON_OTRAS_MASCOTAS');


INSERT INTO adopciones
(id_adopcion, id_adoptante, id_perro, id_solicitud, fecha)
VALUES
(1, 1, 2, 1, '2026-10-21 12:00:00'),
(2, 2, 5, 2, '2026-10-23 13:30:00'),
(3, 6, 9, 6, '2026-10-26 11:00:00');



INSERT INTO observaciones
(id_observacion, id_adopcion, fecha, descripcion)
VALUES
(1, 1, '2026-10-28 10:00:00', 'Luna se adapto correctamente a su nuevo hogar.'),
(2, 1, '2026-11-05 10:30:00', 'Buena convivencia con los integrantes de la familia.'),
(3, 2, '2026-10-30 15:00:00', 'Max presenta buena alimentacion y comportamiento.'),
(4, 3, '2026-11-02 12:00:00', 'Kira se encuentra adaptada y realiza paseos diarios.');