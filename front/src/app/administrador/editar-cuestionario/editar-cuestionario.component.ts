import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../../comun/http.service';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ConfirmacionComponent } from '../confirmacion/confirmacion.component';
import { DatePipe } from '@angular/common';
import { filterPregs } from '../../comun/mypipe';
import { filterPregPipe } from '../../comun/mypipe';


@Component({
  selector: 'app-editar-cuestionario',
  templateUrl: './editar-cuestionario.component.html',
  styleUrls: ['./editar-cuestionario.component.css']
})
export class EditarCuestionarioComponent implements OnInit {

  constructor(private route: ActivatedRoute, private _router: Router, private _service: HttpService, public dialog: MatDialog) { }

  public identificacion;
  public cuestionario;
  public preguntasEncuesta: any = [];
  public encuestaForm: FormGroup;
  public ploaded = false;
  puntos: any = [];
  temas: any = [];
  todasPreguntas: any = [];
  cload = false;
  public dialogRef: MatDialogRef<ConfirmacionComponent>;
  public pregunta: any;
  selecteds: any = [];
  public done = false;
  public loaded = false;

  ngOnInit() {
    this._service.getCategorias().subscribe(data => {
      this.temas = data;
    });
    this._service.getPuntos().subscribe(data => {
      this.puntos = data;
    });
    this._service.getPreguntas().subscribe(data => {
      this.todasPreguntas = data;
      this.loaded = true;
    });
    this.identificacion = this.route.params.subscribe(params => {
      this.identificacion = params['id'];
      this._service.getEncuestaByID(this.identificacion).map(response => response)
      .subscribe(res => {
        this.cuestionario = res;
        this.getPreguntas(this.cuestionario.identificacion);
        this.crearForm(this.cuestionario);
        this.cload = true;
      });
    });
  }

  crearForm(cuest) {
    this.encuestaForm = new FormBuilder().group({
      punto: [cuest['punto'], [Validators.required]],
      identificacion: [cuest['identificacion'], [Validators.required]],
      fechaInicio: [cuest['fechaInicio'], []],
      fechaFin: [cuest['fechaTerminacion'], []],
      modificable: [(cuest['modificable'] === 'SI')? true : false, []],
      sql: [cuest['sql_personasAplica'], []],
      nombre: [cuest['nombreEncuesta'], []],
      encabezado: [cuest['encabezado'], []],
      logeo: [(cuest['requiereLogeo'] === 'SI')? true : false, []],
      secreta: [(cuest['secreta'] === 'S')? true: false, []],
      estructura: [cuest['estructuraMetadato'], []],
      preguntas: new FormBuilder().array([])
    });

    console.log(this.encuestaForm);
  }

  getPreguntas(id) {
    this._service.getPxC(id).map(response => response)
        .subscribe(resp => {
          this.preguntasEncuesta = resp;
          this.preguntasEncuesta.forEach(element => {
            this.agregarPregunta(element);
            this.done = true
          });
          console.log(this.preguntas);
        });
  }

  get preguntas(): FormArray {
    return this.encuestaForm.get('preguntas') as FormArray;
  }

  getDisable(preg) {
    return (this.preguntas.getRawValue().find(x => x.pregunta === preg.numero) !== undefined )? true : false;
  }

  crearPreguntasFormGroup(preg): FormGroup {
    const form = new FormBuilder().group({
      pregunta: [preg['numero'], Validators.required],
      esSeccion: [(preg.textoSeccion)? true : false, []],
      seccion: [preg['textoSeccion'], []],
      obligatoria: [(preg['obligatoriedad'] === 'OPCIONAL')? false : true, []]
    });
    return form;
  }

  agregarPregunta(preg) {
    console.log(preg);
    this.preguntas.push(this.crearPreguntasFormGroup(preg));
    this.selecteds.push(preg);
  }

  opened(event) {
    this.pregunta = this.todasPreguntas.find(x => x.numero === event);
    const index = this.todasPreguntas.indexOf(this.pregunta);
    console.log(this.selecteds);
    console.log(this.todasPreguntas);
  }

  agregarPreguntaNueva() {
    this.preguntas.push(this.crearPreguntasFormGroupNuevo());
  }

  crearPreguntasFormGroupNuevo(): FormGroup {
    const form = new FormBuilder().group({
      pregunta: ['', Validators.required],
      esSeccion: [false, []],
      seccion: ['', []],
      obligatoria: [false, []]
    });
    return form;
  }

  actualizarEncuesta(encuesta, preguntas) {
    const fechaI = new DatePipe('en-US').transform(Date.now(), 'yyyy-MM-dd');
    const datos = {
      punto: encuesta.get('punto').value,
      identificacion: encuesta.get('identificacion').value,
      /* fechaInicio: encuesta.get('fechaInicio').value,
      fechaTerminacion: encuesta.get('fechaFin').value, */
      fechaInicio: fechaI,
      fechaTerminacion: fechaI,
      modificable: (encuesta.get('modificable').value) ? 'SI' : 'NO',
      sql_personasAplica: (encuesta.get('sql').value) ? encuesta.get('sql').value : 'SQL',
      nombreEncuesta: encuesta.get('nombre').value,
      encabezado: encuesta.get('encabezado').value,
      requiereLogeo: (encuesta.get('logeo').value) ? 'SI' : 'NO',
      secreta: (encuesta.get('secreta').value) ? 'S' : 'N',
      estructuraMetadato: encuesta.get('estructura').value,
    };
    console.log(datos);
    const dat = {
      identificacion: encuesta.get('identificacion').value,
      nombre: encuesta.get('nombre').value,
      fechaInicio: fechaI,
      fechaTerminacion: fechaI,
      evaluacion: 'eval20162'
    };
    this._service.updEncuesta(datos).subscribe(res => {
      console.log(res);
      //////////////////////////////////7
      this.deletePreguntas(encuesta, preguntas);
      //////////////////////////////////7
    });
    this._router.navigate(['/admin/cuestionarios']);
  }

  deletePreguntas(encuesta, preguntas) {
    console.log(encuesta);
    this._service.deletePXC(encuesta.get('identificacion').value).map(response => response)
    .subscribe(resp => {
      console.log('Se eliminaron las preguntas');
      this.agregarPreguntas(preguntas, encuesta);
    });
  }

  public openDialog(i) {
    this.dialogRef = this.dialog.open(ConfirmacionComponent);
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result.confirmar) {
          console.log(this.preguntas);
          this.preguntas.removeAt(i);
          console.log(this.preguntas);
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

  addSeccion(pregunta, encuesta, i) {
    if (pregunta.get('seccion') && pregunta.get('seccion').value !== '' && pregunta.get('esSeccion').value) {
      console.log('Entreeeeeeeeeeeeeeeeee');
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
