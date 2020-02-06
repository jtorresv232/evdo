import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { HttpService } from '../../comun/http.service';
import { ConfirmacionComponent } from '../confirmacion/confirmacion.component';
import { MatDialogRef, MatDialog, MatSnackBar } from '@angular/material';
import { CuestionariosComponent } from '../cuestionarios/cuestionarios.component';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { filterPregPipe } from '../../comun/mypipe';


@Component({
  selector: 'app-crear-encuesta',
  templateUrl: './crear-encuesta.component.html',
  styleUrls: ['./crear-encuesta.component.css']
})
export class CrearEncuestaComponent implements OnInit {

  puntos: any = [];
  temas: any = [];
  encuestaForm: FormGroup;
  todasPreguntas: any = [];
  public dialogRef: MatDialogRef<ConfirmacionComponent>;

  constructor(private _service: HttpService, public dialog: MatDialog, public snackBar: MatSnackBar,
    private _router: Router) { }

  ngOnInit() {
    this._service.getCategorias().subscribe(data => {
      this.temas = data;
    });
    this._service.getPuntos().subscribe(data => {
      this.puntos = data;
    });
    this._service.getPreguntas().subscribe(data => {
      this.todasPreguntas = data;
    });
    this.encuestaForm = new FormBuilder().group({
      punto: ['', [Validators.required]],
      identificacion: ['', [Validators.required]],
      fechaInicio: ['', []],
      fechaFin: ['', []],
      modificable: [false, []],
      sql: ['', []],
      nombre: ['', []],
      encabezado: ['', []],
      logeo: [false, []],
      secreta: [false, []],
      estructura: ['', []],
      preguntas: new FormBuilder().array([])
    });
  }

  get preguntas(): FormArray {
    return this.encuestaForm.get('preguntas') as FormArray;
  }

  crearPreguntasFormGroup(): FormGroup {
    const form = new FormBuilder().group({
      pregunta: ['', Validators.required],
      esSeccion: [false, []],
      seccion: ['', []],
      obligatoria: [false, []]
    });
    return form;
  }

  agregarPregunta() {
    this.preguntas.push(this.crearPreguntasFormGroup());
  }

  crearEncuesta(encuesta, preguntas) {
    /* const fechaI = new DatePipe('en-US').transform(Date.now(), 'yyyy-MM-dd');
    const fechaFinal = new Date(fechaI);
    fechaFinal.setUTCFullYear(fechaFinal.getUTCFullYear() + 10);
    const fechaF = new DatePipe('en-US').transform(fechaFinal, 'yyyy-MM-dd'); */
    const datos = {
      punto: encuesta.get('punto').value,
      identificacion: encuesta.get('identificacion').value,
      fechaInicio: encuesta.get('fechaInicio').value,
      fechaTerminacion: encuesta.get('fechaFin').value,
      /* fechaInicio: fechaI,
      fechaTerminacion: fechaF, */
      modificable: (encuesta.get('modificable').value) ? 'SI' : 'NO',
      sql_personasAplica: (encuesta.get('sql').value) ? encuesta.get('sql').value : 'SQL',
      nombreEncuesta: encuesta.get('nombre').value,
      encabezado: encuesta.get('encabezado').value,
      requiereLogeo: (encuesta.get('logeo').value) ? 'SI' : 'NO',
      secreta: (encuesta.get('secreta').value) ? 'SI' : 'NO',
      estructuraMetadato: encuesta.get('estructura').value,
    };
    const dat = {
      identificacion: encuesta.get('identificacion').value,
      nombre: encuesta.get('nombre').value,
      fechaInicio: encuesta.get('fechaInicio').value,
      fechaTerminacion: encuesta.get('fechaFin').value,
      evaluacion: 'eval20191'
    };
    this._service.addEncuesta(dat).subscribe(resultado => {
      console.log(resultado);
    });
    this._service.addCuestionario(datos).subscribe(res => {
      this.agregarPreguntas(preguntas, encuesta);
      this.openSnackBar('Se ha agregado el cuestionario correctamente');
    });
  }

  getDisable(preg) {
    return (this.preguntas.getRawValue().find(x => x.pregunta === preg.numero) !== undefined ) ? true : false;
  }

  public openDialog(i) {
    this.dialogRef = this.dialog.open(ConfirmacionComponent);
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result.confirmar) {
          this.preguntas.removeAt(i);
        } else {
          console.log('no hay nada que guardar');
        }
      }
    });
  }

  agregarPreguntas(preguntas, encuesta) {
    let ord = 0;
    let i = 1;
    let calls = [];
    let pregs = [];
    let secciones = [];
    let seccs = 0;
    // let i = preguntas.length - 1; i >= 0; i--
    for (let pregunta of preguntas) {
      ord = ord + 1;
      const datos = {
        punto: encuesta.get('punto').value,
        identificacion: encuesta.get('identificacion').value,
        numero: pregunta.get('pregunta').value,
        orden: ord,
        obligatoriedad: (pregunta.get('obligatoria').value) ? 'OBLIGATORIA' : 'OPCIONAL'
      };

      if (pregunta.get('esSeccion').value) {
        seccs = seccs + 1;
        const seccionDatos = {
          texto: pregunta.get('seccion').value,
          seccion: seccs,
          punto: encuesta.get('punto').value,
          identificacion: encuesta.get('identificacion').value,
          preguntaDondeArranca: pregunta.get('pregunta').value
        };
        secciones.push(seccionDatos);
      }
      /* const datos = {
        punto: encuesta.get('punto').value,
        identificacion: encuesta.get('identificacion').value,
        numero: preguntas[i].get('pregunta').value,
        orden: ord,
        obligatoriedad: (preguntas[i].get('obligatoria').value) ? 'OBLIGATORIA' : 'OPCIONAL'
      } */
      pregs.push(datos);
      //ord = ord -1;
      /* console.log(datos);
      this._service.addPxC(datos).subscribe(resul => {
        console.log(resul);
        this.addSeccion(pregunta,encuesta, i);
        i = i + 1;
      }); */
      /* calls.push(this._service.addPxC(datos)); */
      
    }
    console.log(pregs)
    if (secciones.length > 0) {
      this._service.addPxC(pregs).map(res => res)
      .mergeMap(respuesta => this._service.addSeccion(secciones))
      .subscribe(resp => {
        console.log(resp);
      });
    } else {
      this._service.addPxC(pregs).subscribe(resp => console.log(resp));
    }
    /* console.log(calls);
    Observable.forkJoin(calls).subscribe(responses => console.log(responses)); */
  }

  openSnackBar(mensaje) {
    this.snackBar.open(mensaje, '', {
      duration: 1300
    });
    this._router.navigate(['/admin/cuestionarios']);
  }

  addSeccion(pregunta, encuesta, i) {
    if (pregunta.get('seccion') && pregunta.get('seccion').value !== '' && pregunta.get('esSeccion').value) {
      const dat = {
        punto: encuesta.get('punto').value,
        identificacion: encuesta.get('identificacion').value,
        texto: pregunta.get('seccion').value,
        preguntaDondeArranca: pregunta.get('pregunta').value,
        seccion: i
      };
      console.log(dat);
      this._service.addSeccion(dat).subscribe(res => {
        console.log(res);
      });
    }
  }

  // Para autocompletar
  filter(val) {
    return this.todasPreguntas.filter(pregunta =>
      pregunta.pregunta.toLowerCase().includes(val.toLowerCase()) === true);
  }

  displayFn(value) {
    if (value && this.todasPreguntas.length > 0) {
      return this.todasPreguntas.find(x => x.numero === value).pregunta;
    } else {
      return '';
    }
  }

}
