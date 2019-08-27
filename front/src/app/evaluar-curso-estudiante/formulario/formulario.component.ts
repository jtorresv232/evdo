import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudianteService } from '../../services/estudiante.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  public asignacion: any;
  public preguntas: any = [];
  public displayPreguntas: any = ['pregunta', '1'];
  public categorias: any = [];
  public pDone = false;
  public cDone = false;
  public calificaciones: any = [1, 2, 3, 4, 5];
  public respuestas = [];
  public respuesta_seleccionada: any;
  public calis: any = [];
  public cuestionario: any;
  public evaluada: any;
  public mispreguntas: any = [];
  public encuesta: any =[];
  public seleccionadas: any = [];
  public loading = false;
  public mensaje;

  public filterProgramas: any = [];
  public filterFacultades: any = [];
  public programanombre: any = '';
  public facultadnombre: any = '';
  public docente: any;
  public facultad = 0;
  public programa: any;
  public materia: any;
  public facultades: any = [];
  public programas: any = [];

  constructor(private _service: HttpService, private _route: ActivatedRoute,
    private _router: Router, private studentService: EstudianteService) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.asignacion = params;
      this._service.getEncuestaByID(params.encuesta)
        .subscribe(resp => {
          this.encuesta = resp;
          this._service.getPxC(params.encuesta)
            .subscribe( pregs => {
              this.preguntas = pregs;
              let i = 0;
              for(let pregunta of this.preguntas) {
                this.getOpciones(pregunta.numero, i);
                i++;
              }
            });
        });
      console.log(params);
    });

    this._service.getCategorias().map(response => response)
    .subscribe(res => {
      this.categorias = res;
    }, err => {
      console.log(err);
    });

    /* 
    this._route.queryParams.subscribe(params => {
      this.asignacion = JSON.parse(params['asignacion']);
      const cuestionario = { 'cuestionario': params['cuestionario'] };
      this.cuestionario = cuestionario;
      this.evaluada = params['evaluada'];
      this._service.getPxC(cuestionario).subscribe(data => {

        this.preguntas = data;
        this.pDone = true;
        console.log(this.preguntas);
      }, err => {
      });
    });

    this._service.getCalificaciones().subscribe(data => {

      this.calis = data;
      console.log(this.calis);
    }, err => {
    });

    this._service.getCategorias().map(response => response)
      .subscribe(res => {
        this.categorias = res;
        this.cDone = true;
      }, err => {
        console.log(err);
      }); */
  }

  cancelar() {
    this._router.navigate(['/evaluar']);
  }

  evaluar() {
    this.loading = true;
    const miCedula = (Math.floor((Math.random() * 10000000) + 1)).toString();
    const idEncuesta = this.encuesta.identificacion;
    console.log(this.asignacion);
    const asignacion = this.asignacion;
    const meta = asignacion.semestre + '-' + asignacion.materia + '-'
      + asignacion.grupo + '-' + asignacion.cedula;

    const relacion = {
      punto: this.encuesta.punto,
      identificacion: idEncuesta,
      identificador: miCedula,
      metadato: meta
    };

    this._service.addRelacion(relacion)
      .subscribe(resp => {
        for( let respuesta of this.respuestas) {
          const datos = {
            punto: this.encuesta.punto,
            identificacion: idEncuesta,
            cedula: miCedula,
            numero: respuesta.numero,
            respuestaOpcion: respuesta.opc,
            respuestaAbierta: 'NO',
            cual: 'NO'
          };
          this._service.addRespuesta(datos)
            .subscribe(res => {
              console.log(res);
            });
        }
        this._service.calcularResultados()
            .subscribe(x => {
              console.log(x);
              this.getCuenta();
              this.mensaje = x;
              x = this._router.navigate(['/evaluar']);
            });
      });

    console.log(meta);
    console.log(miCedula);
  }

  seleccionada(preg, opc) {
    if (this.respuestas.find(x => x.numero === preg.numero) === undefined) {
      const datos = {
        numero: preg.numero,
        opc: opc,
        tipo: preg.tipodato
      };
      this.respuestas.push(datos);
    } else {
      this.respuestas.find(x => x.numero === preg.numero)['opc'] = opc;
    }
    console.log(this.respuestas);
  }

  getOpciones(num, i) {
    this._service.getOpciones(num)
      .subscribe(resp => {
        if(i === this.preguntas.length - 1) {
          this.cDone = true;
          this.pDone = true;
          console.log(this.seleccionadas);
        }
        let datos = [];
        datos.push(resp);
        this.seleccionadas[i] = {
          numero: num,
          opciones: datos
        };
      });
  }

  getCuenta() {
    const asignacion = this.asignacion;
    const meta = asignacion.semestre + '-' + asignacion.materia + '-'
      + asignacion.grupo + '-' + asignacion.cedula;

    this._service.getCuentaEncuestados(meta)
      .subscribe(resp => {
        console.log(resp);
        const datos = {...asignacion, encuestados: resp['cuenta']};
        console.log(datos);
        this._service.editAsigPorcentaje(datos)
          .subscribe(res => {
          });
      });
  }

}
