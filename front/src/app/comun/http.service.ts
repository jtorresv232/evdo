import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { share } from 'rxjs/operator/share';

@Injectable()
export class HttpService {

  private url = 'http://wastest.udea.edu.co:9085/encuestas-admin/webapi/';
  private url2 = 'http://wastest.udea.edu.co:9085/evaluacion/services/';
  /* private url = 'http://localhost:8084/encuesta/webapi/';
  private url2 = 'http://localhost:8084/evaluacion/services/'; */
  /* private url = 'http://wasquality.udea.edu.co:29000/encuesta/webapi/';
  private url2 = 'http://wasquality.udea.edu.co:29000/evaluacion/services/'; */
  /* private url = 'https://asone.udea.edu.co/encuestas-admin/webapi/';
  private url2 = 'https://asone.udea.edu.co/evaluacion/services/'; */

  constructor(private http: HttpClient) { }

  getUrlNotas(semestre, materia, grupo): Observable<any> {
    return this.http.get(this.url2 + 'url/' + semestre + '/' + materia + '/' + grupo);
  }

  getAsignaciones(): Observable<any> {
    return this.http.get(this.url2 + 'asignaciones/');
  }

  calcularResultados(): Observable<any> {
    return this.http.get(this.url2 + 'totalTemas/calcular')
    .timeout(5000 * 60);
  }

  getFiltroUser(): Observable<any> {
    return this.http.get(this.url2 + 'filtro');
  }

  getUsuarios(): Observable<any> {
    return this.http.get(this.url2 + 'usuarios');
  }

  addUsuario(data) {
    return this.http.post(this.url2 + 'usuarios', data);
  }

  findUsuario(cedula) {
    return this.http.get(this.url2 + 'usuarios/buscar/' + cedula);
  }

  addRelacion(relacion) {
    return this.http.post(this.url + 'relaciones', relacion).share();
  }

  addRespuesta(respuesta) {
    return this.http.post(this.url + 'respuestas', respuesta).share();
  }

  deleteUsuario(cedula) {
    return this.http.delete(this.url2 + 'usuarios/' + cedula);
  }

  getProgramaById(id) {
    return this.http.get(this.url2 + 'programas/' + id);
  }

  getTotalPreguntaPrograma (programa): Observable<any> {
    return this.http.get(this.url2 + 'totalPreguntas/por-programa/' + programa);
  }

  getTotalTemaPrograma (programa): Observable<any> {
    return this.http.get(this.url2 + 'totalTemas/por-programa/' + programa);
  }

  getTotalPreguntaDocente (cedula): Observable<any> {
    return this.http.get(this.url2 + 'totalPreguntas/por-docente/' + cedula);
  }

  getTotalTemaDocente (cedula): Observable<any> {
    return this.http.get(this.url2 + 'totalTemas/por-docente/' + cedula);
  }

  getEncuestaByID(id) {
    return this.http.get(this.url + 'encuestas/' + id);
  }

  getAsignacionesFiltered(page, size, facultad, data, semestre, tipo): Observable<any> {
    return this.http.post(this.url2 + 'asignaciones?page=' + page + '&size=' + size + '&facultad=' + facultad +
    '&semestre=' + semestre + '&tipo=' + tipo, data);
  }

  updAllAsigs(facultad, data) {
    return this.http.post(this.url2 + 'asignaciones/updAllAsigs?facultad=' + facultad, data);
  }

  updAllAsigsProf(facultad, data) {
    return this.http.post(this.url2 + 'asignaciones/updAllAsigsProf?facultad=' + facultad, data);
  }

  getAsigTotal(facultad, data, semestre, tipo) {
    return this.http.post(this.url2 + 'asignaciones/total?facultad=' + facultad + '&semestre=' + semestre + '&tipo=' + tipo, data);
  }

  notificarResultado(data) {
    return this.http.post(this.url2 + 'comentarios/notificar', data);
  }

  getTotalPreguntas(): Observable<any> {
    return this.http.get(this.url2 + 'totalPreguntas');
  }

  getTotalTemas(): Observable<any> {
    return this.http.get(this.url2 + 'totalTemas');
  }

  getCalificaciones(): Observable<any> {
    return this.http.get(this.url + 'calificaciones/');
  }

  getRXT(): Observable<any> {
    return this.http.get(this.url + 'rxt');
  }

  getRXP(): Observable<any> {
    return this.http.get(this.url + 'rxp');
  }

  getAsigData(): Observable<any> {
    return this.http.get(this.url2 + 'asignaciones/');
  }

  getDocenteByCedula(cedula): Observable<any> {
    return this.http.get(this.url + 'docentes/' + cedula.toString() + '/');
  }

  getMateriaByCodigo(codigo): Observable<any> {
    return this.http.get(this.url + 'materias/' + codigo.toString() + '/');
  }

  getDocentes(): Observable<any> {
    return this.http.get(this.url + 'docentes/');
  }

  getTemas_x_rol(rol) {
    return this.http.get(this.url + 'temas/temas_x_rol/' + rol);
  }

  setTema_x_rol(tema, rol) {
    return this.http.get(this.url + 'temas/asignar/tema/' + tema + '/rol/' + rol);
  }

  addTema(tema) {
    return this.http.post(this.url + 'temas', tema);
  }

  getMaterias(): Observable<any> {
    return this.http.get(this.url + 'materias/');
  }

  getEvaluadas(): Observable<any> {
    return this.http.get(this.url + 'materias_evaluadas/');
  }

  getPxC(data: any) {
    return this.http.get(this.url + 'preguntaXEncuesta?identificacion=' + data);
  }

  deletePXC(data: any) {
    console.log(data);
    return this.http.delete(this.url + 'preguntaXEncuesta/' + data );
  }

  updEncuesta(data: any) {
    return this.http.put(this.url + 'encuestas/upd/1', data);
  }

  setEncuesta(data: any) {
    return this.http.post(this.url + 'setEncuestas/', data);
  }

  getEncuesta(data: any) {
    return this.http.post(this.url + 'encuestaxasig/', data);
  }

  addComentario(data: any) {
    return this.http.post(this.url2 + 'comentarios/', data);
  }

  getCuestionarios(): Observable<any> {
    return this.http.get(this.url + 'encuestas/');
  }

  getEncuestas(): Observable<any> {
    return this.http.get(this.url2 + 'encuestas/');
  }
  getPreguntas(): Observable<any> {
    return this.http.get(this.url + 'preguntas/');
  }

  getCategorias(): Observable<any> {
    return this.http.get(this.url + 'temas/');
  }

  getPuntos(): Observable<any> {
    return this.http.get(this.url + 'puntos/');
  }

  getProgramas(): Observable<any> {
    return this.http.get(this.url2 + 'programas/');
  }

  getProgramaNumeros(data: any): Observable<any> {
    return this.http.get(this.url2 + 'programas/numeros?programa=' + data);
  }

  getFacultades(): Observable<any> {
    return this.http.get(this.url2 + 'facultades/');
  }

  addPxC(data: any) {
    return this.http.post(this.url + 'preguntaXEncuesta/', data);
  }

  addSeccion(data: any) {
    return this.http.post(this.url + 'secciones/', data);
  }

  addPregunta(data: any) {
    return this.http.post(this.url + 'preguntas/', data);
  }

  addPreguntaEv(data: any) {
    return this.http.post(this.url2 + 'preguntas/', data);
  }

  addOpciones(data: any) {
    return this.http.post(this.url + 'opciones/', data);
  }

  getOpciones(data: any) {
    return this.http.get(this.url + 'opciones?opcion=' + data);
  }

  addCuestionario(data: any) {
    return this.http.post(this.url + 'encuestas/', data);
  }

  getCuentaEncuestados(data: any) {
    return this.http.get(this.url + 'relaciones?metadato=' + data);
  }

  getComentariosEncuesta(data: any) {
    return this.http.post(this.url2 + 'comentarios/obtener', data);
  }

  addEncuesta(data: any) {
    return this.http.post(this.url2 + 'encuestas/', data);
  }

  editAsignacion(data: any) {
    return this.http.put(this.url2 + 'asignaciones/1', data);
  }

  editAsignacionProf(data: any) {
    return this.http.put(this.url2 + 'asignaciones/updateProf/1', data);
  }

  editFacultad(data: any) {
    return this.http.put(this.url2 + 'facultades/' + data.codigo, data);
  }

  editAsigPorcentaje(data: any) {
    return this.http.put(this.url2 + 'asignaciones/porcentaje', data);
  }

  editEvaluadas(data: any): Observable<any> {
    return this.http.put(this.url + 'setEvaluada/', data);
  }

  deletePregunta(elem) {
    return this.http.delete(this.url + 'preguntas/' + elem + '/');
  }

  getEstudiante(email, password) {
    return this.http.get(this.url + 'studs/' + email + '/' + password + '/');
  }

  getDocente(email, password) {
    return this.http.get(this.url + 'docentes/' + email + '/' + password + '/');
  }

  getCoordinador(email, password) {
    return this.http.get(this.url + 'coordinadores/' + email + '/' + password + '/');
  }

  getAsigxEstudiante(cedula) {
    return this.http.get(this.url + 'asignaciones/' + cedula.toString() + '/');
  }

  getAsigxDocente(cedula) {
    return this.http.get(this.url + 'asignacionesD/' + cedula.toString() + '/');
  }

  getMaxDerivada() {
    return this.http.get(this.url + 'derivadas/max');
  }

  addDerivadas(derivada) {
    return this.http.post(this.url + 'derivadas', derivada);
  }

  addSeveralOptions(options) {
    return this.http.post(this.url + 'opciones/several', options);
  }

  addSeveralDerivadas(derivadas) {
    return this.http.post(this.url + 'derivadas/several', derivadas);
  }

  getDerivadas(numero) {
    return this.http.get(this.url + 'derivadas/' + numero);
  }

  getEvaluacion(semestre) {
    return this.http.get(this.url2 + 'evaluacion/' + semestre);
  }

  getEvaluaciones() {
    return this.http.get(this.url2 + 'evaluacion/');
  }

  addEvaluacion(evaluacion) {
    return this.http.post(this.url2 + 'evaluacion/', evaluacion);
  }

  updateEvaluacion(evaluacion) {
    return this.http.put(this.url2 + 'evaluacion/' + evaluacion.codigo, evaluacion);
  }

  poblarCursos(semestre) {
    return this.http.get(this.url2 + 'asignaciones/poblar?semestre=' + semestre);
  }
}
