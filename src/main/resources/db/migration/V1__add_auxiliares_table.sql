DROP TABLE IF EXISTS taux_docente_auxiliar CASCADE;

create table taux_docente_auxiliar(
   taux_auxiliar_id SERIAL NOT NULL PRIMARY KEY,
   taux_cedula VARCHAR(35) NOT NULL UNIQUE,
   taux_nombre VARCHAR(250) NOT NULL,
   taux_cargo VARCHAR(60) NOT NULL,
   taux_celular VARCHAR(10) NOT NULL,
   taux_sede VARCHAR(40) NOT NULL
);
