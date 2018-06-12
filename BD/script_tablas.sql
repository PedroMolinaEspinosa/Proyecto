PRAGMA foreign_keys = ON;


DROP TABLE IF EXISTS persona;
CREATE TABLE persona(
        id INT NOT NULL,
        nombre TEXT,
        apellidos TEXT,
        email TEXT,
        genero TEXT,
PRIMARY KEY(id)
);
