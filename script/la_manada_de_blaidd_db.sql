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

