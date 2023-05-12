DROP TABLE IF EXISTS taux_turno CASCADE;
DROP TABLE IF EXISTS taux_turno_asignado CASCADE;

CREATE TABLE taux_turno(
   taux_turno_id DATE NOT NULL PRIMARY KEY
);

CREATE TABLE taux_turno_asignado (
  taux_asignacion_id SERIAL PRIMARY KEY,
  taux_auxiliar_id SERIAL,
  taux_turno_id DATE,
  FOREIGN KEY (taux_auxiliar_id) REFERENCES taux_docente_auxiliar (taux_auxiliar_id),
  FOREIGN KEY (taux_turno_id) REFERENCES taux_turno (taux_turno_id)
);