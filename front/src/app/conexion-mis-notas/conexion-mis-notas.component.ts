import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../comun/http.service';

@Component({
  selector: 'app-conexion-mis-notas',
  templateUrl: './conexion-mis-notas.component.html',
  styleUrls: ['./conexion-mis-notas.component.css']
})
export class ConexionMisNotasComponent implements OnInit {

  public semestre;
  public materia;
  public grupo;
  public cedula;
  public cargado = false;
  public datos;

  constructor(private route: ActivatedRoute,  private _service: HttpService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.semestre = params['semestre'];
      this.materia = params['materia'];
      this.grupo = params['grupo'];

      this._service.getUrlNotas(this.semestre, this.materia, this.grupo)
      .subscribe(resp => {
        this.datos = resp;
        this.datos = this.aTextoPlano(this.datos);
        console.log(this.datos);
        this.cargado = true;
      },
      error => {
        console.log(error);
        this.datos = error['error']['message'];
      });
    });
  }

  aTextoPlano(datos) {
    let texto = '[';
    for (const dato of datos) {
      texto += '{';
      texto = texto + 'cedula:' + dato['cedula'] + ',';
      texto = texto + 'encuesta:' + dato['encuesta'] + ',';
      texto = texto + 'fechaInicio:' + dato['fechaInicio'] + ',';
      texto = texto + 'fechaTerminacion:' + dato['fechaTerminacion'] + '},';
    }
    texto = texto.slice(0, -1);
    texto += ']';
    console.log(texto);
    return texto;
  }

}
