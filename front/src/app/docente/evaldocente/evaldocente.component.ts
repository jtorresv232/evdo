import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';

// tslint:disable-next-line:class-name
export interface totalPreg {
  numero: number;
  media: number;
  desviacion: number;
  coeficienteDesv: number;
  semestre: number;
  materia: number;
  grupo: number;
  cedula: string;
}

// tslint:disable-next-line:class-name
export interface totalTem {
  codigoTema: number;
  media: number;
  desviacion: number;
  coeficienteDesv: number;
  semestre: number;
  materia: number;
  grupo: number;
  cedula: string;
  tema: string;
}

@Component({
  selector: 'app-evaldocente',
  templateUrl: './evaldocente.component.html',
  styleUrls: ['./evaldocente.component.css']
})

export class EvaldocenteComponent implements OnInit {

  public aDone = false;
  public asignaciones: any = [];
  public cedula = '';
  public totalpreguntas: totalPreg[];
  public totaltemas: totalTem[];
  public myArray: any = [{key: 'key1', value: 1}, {key: 'key2', value: 1},
  {key: 'key2', value: 1}, {key: 'key1', value: 1}, {key: 'key1', value: 1}, {key: 'key1', value: 1},
  {key: 'key3', value: 7}];
  public totalpreguntasAsig: totalPreg[] = [];
  public totaltemasAsig: totalTem[] = [];
  public preguntas: any = [];
  public mostrar = false;
  public semestre: any;
  public tipo: any;

  constructor(private _service: HttpService) { }

  ngOnInit() {
      this._service.getFiltroUser()
      .mergeMap(sesionUser => this._service.getAsignacionesFiltered(1, 100, 0, { programa: 0, materia: 0, cedula: sesionUser.ccid }, this.semestre? this.semestre: 0, this.tipo? this.tipo : 'ninguno'))
      .subscribe(res => {
        this.asignaciones = res;
        this.aDone = true;
      });

      this._service.getTotalPreguntas().subscribe(resp => {
        this.totalpreguntas = resp;
      });
      this._service.getTotalTemas().subscribe(respuesta => {
        this.totaltemas = respuesta;
      });

      let totals = this.myArray.reduce(function(acc, val) {
        let cuenta = 1;
        const o = acc.filter(function(obj) {
            return obj.key === val.key;
        }).pop() || {key: val.key, value: 0};
        cuenta = acc.reduce(function(total, x) {return x.key === val.key ? total + 1 : total; }, 1);
        o.value += val.value;
        o['cuenta'] = cuenta;
        console.log(o);
        acc.push(o);
        return acc;
    }, []);

    totals = totals.filter(function(itm, i, a) {
      return i === a.indexOf(itm);
  });
    console.log(totals);

    this._service.getPreguntas().map(response => response)
    .subscribe(res => {
      this.preguntas = res;
    }, err => {
      console.log(err);
    });
  }

  public getTotales(asig) {
    this.totalpreguntasAsig = this.totalpreguntas.filter(x =>
      x.semestre === asig.semestre &&
      x.materia === asig.materia &&
      x.grupo === asig.grupo &&
      x.cedula === asig.cedula
    );
    console.log(this.totalpreguntas);

    this.totaltemasAsig = this.totaltemas.filter(x =>
      x.semestre === asig.semestre &&
      x.materia === asig.materia &&
      x.grupo === asig.grupo &&
      x.cedula === asig.cedula
    );
  }

  getnombre(numero) {
    return this.preguntas.find(x => x.numero === numero).pregunta;
  }

  public getTexto(asig) {
    return ('[' + asig.nombreMateria + '-' + asig.grupo.toString() + '] '
      + ' con: ' + asig.nombreDocente);
  }

  public notShow() {
    this.mostrar = false;
    console.log(this.mostrar);
  }
}
