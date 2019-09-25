import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { DerivadasService } from '../../services/derivadas.service';
import { filterPregPipe } from '../../comun/mypipe';

@Component({
  selector: 'app-derivadas',
  templateUrl: './derivadas.component.html',
  styleUrls: ['./derivadas.component.css']
})
export class DerivadasComponent implements OnInit {
  @Input() todasPreguntas;
  filterPreguntas;
  derivadasForm: FormGroup;
  @Input() visible;
  @Input() orden;
  @Output() getDerivadas = new EventEmitter();

  constructor(private _service: HttpService, private _derivadaService: DerivadasService) { }

  ngOnInit() {
    this.filterPreguntas = this.todasPreguntas;
    this.derivadasForm = new FormBuilder().group({
      preguntas: new FormBuilder().array([])
    });
  }

  get preguntas(): FormArray {
    return this.derivadasForm.get('preguntas') as FormArray;
  }

  crearPreguntasFormGroup(): FormGroup {
    const form = new FormBuilder().group({
      pregunta: ['', Validators.required]
    });
    //form.get('pregunta').valueChanges.subscribe(val => this.change(val));
    return form;
  }

  agregarPregunta() {
    this.preguntas.push(this.crearPreguntasFormGroup());
  }

  getDisable(preg) {
    const first = this.preguntas.getRawValue().find(x => x.pregunta === preg.numero);
    const second = this._derivadaService.getLista().find(x => x.numero === preg.numero);
    let firstControl = false;
    let secondControl = false;
    if(first !== undefined) { firstControl = true}
    if(second !== undefined) {secondControl = true}
    return firstControl || secondControl;
    //return (this.preguntas.getRawValue().find(x => x.pregunta === preg.numero) !== undefined ) ? true : false;
  }

  getPreguntasNumeros() {
    return this.preguntas.value.map(pregunta => pregunta.pregunta);
  }

  select(orden) {
    this._derivadaService.add({numero: this.preguntas.get((orden-1).toString()).value.pregunta, opcion: this.orden, orden: orden});
    console.log(this._derivadaService.getLista());
  }

  borrarPregunta(preg, i) {
    this.preguntas.removeAt(i);
    let pregunta = preg.value;
    this._derivadaService.delete(pregunta);
  }


  // Para autocompletar

  filter(val) {
    return this.todasPreguntas.filter(pregunta =>
      pregunta.pregunta.toLowerCase().includes(val.toLowerCase()) === true);
  }

  displayFn(value) {
    if (value) {
      return this.todasPreguntas.find(x => x.numero === value).pregunta;
    } else {
      return '';
    }
  }
}
