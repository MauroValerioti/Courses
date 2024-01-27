Link Github: https://github.com/Sofiamondello04/ArquitecturasWeb

Resolucion Ejercicio Integrador

a) dar de alta un estudiante: POST http://localhost:8080/estudiantes/

Request ejemplo:

{
"dni": 38531711,
"nombre": "Sofia",
"apellido": "Mondello",
"ciudadResidencia": "tandil",
"edad": 28,
"genero": "Female",
"numLibretaUniversitaria": 540540
}

b) matricular un estudiante en una carrera: POST http://localhost:8080/inscripciones/matricular

Request ejemplo:


{
"idEstudiante": 38531711,
"idCarrera" : 15
}

c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple: GET http://localhost:8080/estudiantes/ByNombreAsc

d) recuperar un estudiante, en base a su número de libreta universitaria: GET http://localhost:8080/estudiantes/ByNumLibretaUniversitaria/{numLibretaUniversitaria}

e) recuperar todos los estudiantes, en base a su género:  GET http://localhost:8080/estudiantes/ByGenero/{genero}

f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos. GET http://localhost:8080/inscripciones/carrerasOrderByInscriptos

g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. GET http://localhost:8080/inscripciones/estudiantesCarrerayPorCiudad/{carrera}/{ciudadResidencia}

h) generar un reporte de las carreras, que para cada carrera incluya información de los
inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
presentar los años de manera cronológica.  GET http://localhost:8080/inscripciones/reporteCarreras
